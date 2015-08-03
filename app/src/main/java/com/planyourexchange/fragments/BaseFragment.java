package com.planyourexchange.fragments;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.planyourexchange.R;
import com.planyourexchange.rest.model.BaseModel;
import com.planyourexchange.rest.model.City;
import com.planyourexchange.tasks.RestLoaderTask;
import com.planyourexchange.views.ModelView;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.planyourexchange.utils.Constants.CITY_ID;

/**
 * Created by thiago on 02/08/15.
 */
// -- Base model for handling information between fragments that share enormous similarities
public abstract class BaseFragment<Key extends Serializable, Model extends BaseModel> extends AbstractBaseFragment<Key, Model> {

    private final Fragment nextScreen;

    // -- Need to be called by overriding class
    protected BaseFragment(final int inflateLayout, final int drawLayout, final Fragment nextScreen) {
        super(inflateLayout,drawLayout);
        this.nextScreen = nextScreen;
    }

    @Override
    public void drawList(List<Model> modelList, Context context, ViewGroup viewGroup) {
        for (final Model model : modelList) {
            TextView textView = new TextView(context);
            textView.setText(model.getName());
            textView.setTextColor(Color.BLACK);
            viewGroup.addView(textView);

            ImageView imageView = new ImageView(context);
            ImageLoader.getInstance().displayImage(model.getIcon(), imageView);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(KEY_ID, createNextKey(model));
                    nextScreen.setArguments(bundle);
                    // -- Creating transaction and adding to back stack navigation
                    getFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, nextScreen)
                            .addToBackStack(null)
                            .commit();
                }
            });

            viewGroup.addView(imageView);
        }
    }
    // -- Default is the model id as key
    protected Serializable createNextKey(Model model) {
        return model.getId();
    }
}
