package com.planyourexchange.fragments.schoolcourse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.planyourexchange.R;
import com.planyourexchange.fragments.base.AbstractBaseFragment;
import com.planyourexchange.rest.model.SchoolCourseValue;
import com.planyourexchange.rest.model.SchoolCourseValueKey;

import java.util.List;

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
public class SchoolCourseValueFragment extends AbstractBaseFragment<SchoolCourseValueKey,List<SchoolCourseValue>,ListView> {

    public SchoolCourseValueFragment() {
        super(R.string.school_course_value_title,R.layout.school_course_value_fragment, R.id.school_course_value_list_view);
    }

    @Override
    public void callService(SchoolCourseValueKey schoolCourseValueKey) {
        serverApi.findCourseSchoolValue(schoolCourseValueKey, this);
    }

    @Override
    public void drawModel(final List<SchoolCourseValue> schoolCourseValues, ListView listView) {
        final SchoolCourseValueKey key = (SchoolCourseValueKey) getArguments().getSerializable(KEY_ID);

        // -- Handle Model rendering
        listView.setAdapter(new ArrayAdapter<SchoolCourseValue>(getActivity(),R.layout.school_course_value_list,schoolCourseValues) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View rowView = inflater.inflate(R.layout.school_course_value_list,null,true);

                SchoolCourseValue schoolCourseValue = schoolCourseValues.get(position);

                String iconUrl = null;
                String name = null;

                // -- If course is empty, list all courses
                if(key.getCourseId()==null) {
                    iconUrl = schoolCourseValue.getCourse().getIcon();
                    name = schoolCourseValue.getCourse().getName();
                    // -- On the other hand if school is empty, list all schools
                } else if(key.getSchoolId()==null) {
                    iconUrl = schoolCourseValue.getSchool().getIcon();
                    name = schoolCourseValue.getSchool().getName();
                }

                ImageView imageView = (ImageView) rowView.findViewById(R.id.model_list_icon);
                TextView nameView = (TextView) rowView.findViewById(R.id.model_list_name);
                TextView valueView = (TextView) rowView.findViewById(R.id.model_list_value);

                ImageLoader.getInstance().displayImage(iconUrl, imageView);

                nameView.setText(name);
                valueView.setText(schoolCourseValue.getWeekPrice().toString());

                return rowView;
            }
        });

        // -- Notify that new data has arrived
        listView.deferNotifyDataSetChanged();
    }
}