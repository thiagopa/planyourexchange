package com.planyourexchange.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.view.ViewGroup;

import com.planyourexchange.app.PlanYourExchangeContext;
import com.planyourexchange.rest.model.City;
import com.planyourexchange.rest.model.Country;
import com.planyourexchange.views.ModelView;

import java.util.List;

/**
 * Created by thiago on 01/08/15.
 */
public class RestLoaderTask<Model> extends AsyncTask<Integer, Void, List<Model>> {

    private Context context;
    private ViewGroup viewGroup;
    private ModelView<Model> modelView;

    public RestLoaderTask(Context context, ViewGroup viewGroup, ModelView<Model> modelView) {
        this.context = context;
        this.viewGroup = viewGroup;
        this.modelView = modelView;
    }

    @Override
    protected List<Model> doInBackground(Integer... params) {
        return modelView.callService(params[0]);
    }

    @Override
    protected void onPostExecute(List<Model> list) {
        modelView.setCachedData(list);
        modelView.drawList(list,context,viewGroup);
    }
}
