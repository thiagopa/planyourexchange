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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.planyourexchange.utils.Constants.CITY_ID;

/**
 * Created by thiago on 02/08/15.
 */
// -- Base model for handling information between fragments that share enormous similarities
public abstract class BaseFragment<Key, Model extends BaseModel> extends Fragment implements ModelView<Key, Model> {

    // -- Cache of information
    private final Map<Key, List<Model>> CACHE = new HashMap<Key, List<Model>>();

    protected static final String KEY_ID = "keyId";

    // -- Base properties
    private final int inflateLayout;
    private final int drawLayout;
    private final Fragment nextScreen;

    // -- Need to be called by overriding class
    protected BaseFragment(final int inflateLayout, final int drawLayout,final Fragment nextScreen) {
        this.inflateLayout = inflateLayout;
        this.drawLayout = drawLayout;
        this.nextScreen = nextScreen;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(this.inflateLayout, container, false);

        Context context = container.getContext();
        ViewGroup viewGroup = (ViewGroup) view.findViewById(this.drawLayout);
        Key key = (Key) getArguments().get(KEY_ID);

        // -- Dispatch task to load resources if not cached
        if (CACHE.containsKey(key)) {
            drawList(CACHE.get(key), context, viewGroup);
        } else {
            new RestLoaderTask<Key,Model>(context, viewGroup, this).execute(key);
        }

        return view;
    }


    @Override
    public void addCachedData(List<Model> modelList) {
        CACHE.put((Key)getArguments().get(KEY_ID),modelList);
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
                    // -- Passing the model Id
                    Bundle bundle = new Bundle();
                    bundle.putInt(KEY_ID, model.getId());
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
}
