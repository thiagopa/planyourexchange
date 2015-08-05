package com.planyourexchange.fragments.base;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.planyourexchange.app.PlanYourExchangeContext;
import com.planyourexchange.fragments.schoolcourse.FragmentName;
import com.planyourexchange.tasks.RestLoaderTask;
import com.planyourexchange.views.ModelView;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by thiago on 02/08/15.
 */
// -- Base model for handling information between fragments that share enormous similarities
public abstract class AbstractBaseFragment<Key extends Serializable, Model> extends ProgressDialogFragment implements ModelView<Key, Model>, FragmentName {

    // -- Cache of information
    private final Map<Key, List<Model>> CACHE = new HashMap<Key, List<Model>>();

    protected static final String KEY_ID = "keyId";

    // -- Base properties
    private final int inflateLayout;
    private final int drawLayout;
    private final String titleName;

    // -- Need to be called by overriding class
    protected AbstractBaseFragment(final String titleName, final int inflateLayout, final int drawLayout) {
        this.titleName = titleName;
        this.inflateLayout = inflateLayout;
        this.drawLayout = drawLayout;
    }

    @Override
    public void onStart() {
        super.onStart();
        // -- Send tracking information to Google Analytics so I know which screen users are browsing
        Tracker tracker = PlanYourExchangeContext.getInstance().tracker;
        tracker.setScreenName(getName());
        tracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(this.inflateLayout, container, false);

        Context context = container.getContext();
        ViewGroup viewGroup = (ViewGroup) view.findViewById(this.drawLayout);
        Key key = (Key) getArguments().getSerializable(KEY_ID);

        // -- Dispatch task to load resources if not cached
        if (CACHE.containsKey(key)) {
            drawList(CACHE.get(key), context, viewGroup);
        } else {
            new RestLoaderTask<Key,Model>(context, viewGroup, this, this).execute(key);
        }

        return view;
    }


    @Override
    public void addCachedData(List<Model> modelList) {
        CACHE.put((Key)getArguments().get(KEY_ID),modelList);
    }

    @Override
    public String getName() {
        return titleName;
    }
}
