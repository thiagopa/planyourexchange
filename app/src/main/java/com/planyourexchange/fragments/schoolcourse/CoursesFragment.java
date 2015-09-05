package com.planyourexchange.fragments.schoolcourse;

import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.planyourexchange.R;
import com.planyourexchange.fragments.base.BaseModelListViewFragment;
import com.planyourexchange.fragments.base.ListViewFragment;
import com.planyourexchange.pageflow.PageFlow;
import com.planyourexchange.rest.model.Course;
import com.planyourexchange.rest.model.SchoolCourseValue;
import com.planyourexchange.rest.model.SchoolCourseValueKey;
import com.planyourexchange.utils.MoneyUtils;

import org.parceler.Parcels;

import java.io.Serializable;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.planyourexchange.utils.Constants.KEY_ID;

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
public class CoursesFragment extends ListViewFragment<Integer,Course> {

    public CoursesFragment() {
        super(R.string.courses_title,R.string.choose_course, R.layout.course_list, PageFlow.SCHOOLS);
    }

    @Override
    public void callService(Integer modelId) {
        serverApi.listCourses(modelId, this);
    }

    @Override
    protected Object createNextKey(Course model) {
        SchoolCourseValueKey key = new SchoolCourseValueKey();
        key.setCityId((Integer) Parcels.unwrap(getArguments().getParcelable(KEY_ID)));
        key.setCourseId(model.getId());
        return key;
    }

    @Override
    protected void saveOption(Course course) {
        pageFlowContext.setCourse(course);
    }

    static class ViewHolder {
        @Bind(R.id.course_icon) ImageView icon;
        @Bind(R.id.course_name) TextView name;
        @Bind(R.id.course_duration) TextView duration;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    @Override
    protected void renderSingleModel(Course course, View rowView) {
        ViewHolder viewHolder = new ViewHolder(rowView);

        ImageLoader.getInstance().displayImage(course.getIcon(), viewHolder.icon);
        viewHolder.name.setText(course.getName());
        viewHolder.duration.setText(course.getWeekDuration().toString());
    }
}
