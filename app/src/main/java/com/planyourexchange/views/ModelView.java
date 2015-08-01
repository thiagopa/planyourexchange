package com.planyourexchange.views;

import android.content.Context;
import android.view.ViewGroup;

import com.planyourexchange.rest.model.Country;

import java.util.List;

/**
 * Created by thiago on 01/08/15.
 */
public interface ModelView<Model> {

    // -- Store cached data
    void setCachedData(List<Model> modelList);

    // -- Draw cached data with the given context to the given view
    void drawList(List<Model> modelList, Context context, ViewGroup viewGroup);

    // -- Which method to call
    List<Model> callService(Integer modelId);
}
