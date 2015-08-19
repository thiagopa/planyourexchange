package com.planyourexchange.fragments.schoolcourse;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.planyourexchange.R;
import com.planyourexchange.fragments.base.ListViewFragment;
import com.planyourexchange.pageflow.PageFlow;
import com.planyourexchange.rest.model.SchoolCourseValue;
import com.planyourexchange.rest.model.SchoolCourseValueKey;
import com.planyourexchange.utils.MoneyUtils;

import java.io.Serializable;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (C) 2015, Thiago Pagonha,
 * Plan Your Exchange, easy exchange to fit your budget
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
public class SchoolCourseValueFragment extends ListViewFragment<SchoolCourseValueKey,SchoolCourseValue> {

    public SchoolCourseValueFragment() {
        super(R.string.school_course_value_title, R.string.school_course_value, R.layout.school_course_value_list, PageFlow.HEALTH_INSURANCE);
    }

    @Override
    public void callService(SchoolCourseValueKey schoolCourseValueKey) {
        serverApi.findCourseSchoolValue(schoolCourseValueKey, this);
    }

    @Override
    protected Serializable createNextKey(SchoolCourseValue schoolCourseValue) {
        return schoolCourseValue.getSchool().getCity().getCountry().getId();
    }

    static class ViewHolder {
        @Bind(R.id.school_course_icon) ImageView icon;
        @Bind(R.id.school_course_name) TextView name;
        @Bind(R.id.school_course_price) TextView price;
        @Bind(R.id.school_course_duration) TextView duration;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    @Override
    protected void renderSingleModel(SchoolCourseValue schoolCourseValue, View rowView) {
        ViewHolder viewHolder = new ViewHolder(rowView);

        ImageLoader.getInstance().displayImage(schoolCourseValue.getSchool().getIcon(), viewHolder.icon);

        viewHolder.name.setText(schoolCourseValue.getSchool().getName());
        viewHolder.price.setText(MoneyUtils.newPrice(
                        schoolCourseValue.getSchool().getCity().getCountry().getDefaultCurrency(),
                        schoolCourseValue.getWeekPrice()
                )
        );
        viewHolder.duration.setText(schoolCourseValue.getCourse().getWeekDuration());
    }

    @Override
    protected void saveOption(SchoolCourseValue schoolCourseValue) {
       pageFlowContext.setSchoolCourseValue(schoolCourseValue);
    }
}