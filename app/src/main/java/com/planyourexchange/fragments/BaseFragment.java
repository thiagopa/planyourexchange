package com.planyourexchange.fragments;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.planyourexchange.R;
import com.planyourexchange.app.PlanYourExchangeContext;
import com.planyourexchange.rest.model.BaseModel;
import com.planyourexchange.utils.Constants;

import java.io.Serializable;
import java.util.List;

/**
 * Created by thiago on 02/08/15.
 */
// -- Base model for handling information between fragments that share enormous similarities
public abstract class BaseFragment<Key extends Serializable, Model extends BaseModel> extends AbstractBaseFragment<Key, Model> {

    private final Fragment nextScreen;

    // -- Need to be called by overriding class
    protected BaseFragment(final String titleName, final int inflateLayout, final int drawLayout, final Fragment nextScreen) {
        super(titleName,inflateLayout,drawLayout);
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
                    // -- Analytics click event for model
                    Tracker tracker = PlanYourExchangeContext.getInstance().tracker;
                    tracker.send(new HitBuilders.EventBuilder()
                            .setCategory(Constants.CATEGORY_NAVIGATION)
                            .setAction(Constants.ACTION_CLICK_ON_MODEL)
                            .setLabel(model.getName())
                            .build());

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
