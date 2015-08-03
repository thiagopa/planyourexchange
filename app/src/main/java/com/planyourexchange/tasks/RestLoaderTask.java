package com.planyourexchange.tasks;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.view.ViewGroup;

import com.planyourexchange.app.PlanYourExchangeContext;
import com.planyourexchange.rest.model.BaseModel;
import com.planyourexchange.rest.model.City;
import com.planyourexchange.rest.model.Country;
import com.planyourexchange.views.ModelView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thiago on 01/08/15.
 */
public class RestLoaderTask<Key,Model extends BaseModel> extends AsyncTask<Key, Void, List<Model>> {

    private Context context;
    private ViewGroup viewGroup;
    private ModelView<Key,Model> modelView;

    public RestLoaderTask(Context context, ViewGroup viewGroup, ModelView<Key,Model> modelView) {
        this.context = context;
        this.viewGroup = viewGroup;
        this.modelView = modelView;
    }

    @Override
    protected List<Model> doInBackground(Key... params) {
        Key argument = null;

        // -- Some cases, argument may be null
        if(params!=null && params.length > 0) {
            argument = params[0];
        }

        List<Model> result = new ArrayList<>(0);
        try {
            result = modelView.callService(argument);
        } catch(Exception e) {
            // -- Show an error dialog in case some pesky little network or whatever error happens
            // -- TODO This should be better handled
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage(e.getMessage())
                    .setTitle("Error")
                    .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    }).create().show();
        }
        return result;
    }

    @Override
    protected void onPostExecute(List<Model> list) {
        if(!list.isEmpty()) {
            modelView.addCachedData(list);
            modelView.drawList(list,context,viewGroup);
        }
    }
}
