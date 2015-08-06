package com.planyourexchange.tasks;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.view.ViewGroup;
import android.widget.ListView;

import com.planyourexchange.R;
import com.planyourexchange.interfaces.ProgressDialogListener;
import com.planyourexchange.interfaces.ModelView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thiago on 01/08/15.
 */
public class RestLoaderTask<Key extends Serializable ,Model> extends AsyncTask<Key, Void, List<Model>> implements DialogInterface.OnCancelListener {

    private final Context context;
    private final ListView listView;
    private final ModelView<Key,Model> modelView;
    private final ProgressDialogListener progressDialogListener;
    private Exception errorDuringExecution;

    public RestLoaderTask(Context context, ListView listView, ModelView<Key,Model> modelView, ProgressDialogListener progressDialogListener) {
        this.context = context;
        this.listView = listView;
        this.modelView = modelView;
        this.progressDialogListener = progressDialogListener;
    }

    @Override
    protected void onPreExecute() {
        // -- Opens progress dialog
        progressDialogListener.onTaskStarted(this);
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
            errorDuringExecution = e;
        }
        return result;
    }

    @Override
    protected void onPostExecute(List<Model> list) {
        // -- Closes progress dialog
        progressDialogListener.onTaskFinished();
        // -- Starts view creation if not empty or show error message
        if(!list.isEmpty()) {
            modelView.addCachedData(list);
            modelView.drawList(list,context,listView);
        } else {
            // -- Show an error dialog in case some pesky little network or whatever error happens
            // -- TODO This should be better handled
            String message = errorDuringExecution!=null? errorDuringExecution.getMessage(): context.getString(R.string.no_data_server);

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage(message)
                    .setTitle(R.string.error)
                    .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    }).create().show();
        }
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        // -- Cancel this task
        this.cancel(true);
    }
}
