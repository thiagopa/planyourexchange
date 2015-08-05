package com.planyourexchange.fragments.schoolcourse;

import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.planyourexchange.R;
import com.planyourexchange.app.PlanYourExchangeContext;
import com.planyourexchange.fragments.base.AbstractBaseFragment;
import com.planyourexchange.rest.model.SchoolCourseValue;
import com.planyourexchange.rest.model.SchoolCourseValueKey;

import java.util.List;

/**
 * Created by thiago on 02/08/15.
 */
public class SchoolCourseValueFragment extends AbstractBaseFragment<SchoolCourseValueKey,SchoolCourseValue> {

    public SchoolCourseValueFragment() {
        super("Course Value By School",R.layout.school_course_value_fragment, R.id.school_course_value_table_layout);
    }

    @Override
    public List<SchoolCourseValue> callService(SchoolCourseValueKey schoolCourseValueKey) {
        return PlanYourExchangeContext.getInstance().serverService.getServerApi().findCourseSchoolValue(schoolCourseValueKey);
    }

    @Override
    public void drawList(List<SchoolCourseValue> schoolCourseValues, Context context, ViewGroup viewGroup) {
        TableRow.LayoutParams rowParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        SchoolCourseValueKey key = (SchoolCourseValueKey) getArguments().getSerializable(KEY_ID);

        for (SchoolCourseValue schoolCourseValue : schoolCourseValues) {

            TableRow row = new TableRow(context);
            row.setLayoutParams(rowParams);

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

            ImageView icon = new ImageView(context);
            ImageLoader.getInstance().displayImage(iconUrl, icon);
            row.addView(icon);

            TextView nameView = new TextView(context);
            nameView.setText(name);
            nameView.setTextColor(Color.BLACK);
            row.addView(nameView);

            TextView valueView = new TextView(context);
            valueView.setText( schoolCourseValue.getWeekPriceCurrency() + " " + schoolCourseValue.getWeekPrice() );
            valueView.setTextColor(Color.BLACK);
            row.addView(valueView);

            viewGroup.addView(row);
        }
    }
}