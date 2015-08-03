package com.planyourexchange.views;

import android.content.Context;
import android.view.ViewGroup;

import com.planyourexchange.rest.model.BaseModel;
import com.planyourexchange.rest.model.Country;

import java.util.List;

/**
 * Created by thiago on 01/08/15.
 */
public interface ModelView<Key, Model extends BaseModel> {

    // -- Store cached data for model list
    void addCachedData(List<Model> modelList);

    // -- Draw cached data with the given context to the given view
    void drawList(List<Model> modelList, Context context, ViewGroup viewGroup);

    // -- Which method to call for the given key
    List<Model> callService(Key key);
}
