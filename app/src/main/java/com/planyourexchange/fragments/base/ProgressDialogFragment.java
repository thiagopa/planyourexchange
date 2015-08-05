package com.planyourexchange.fragments.base;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by thiago on 05/08/15.
 */
// -- Used by all fragments that need a progress dialog for rest calls
public abstract class ProgressDialogFragment extends Fragment implements ProgressDialogListener {

    private ProgressDialog progressDialog;
    private boolean isTaskRunning = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // -- Trying to handle tasks effectively
        // setRetainInstance(true);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // -- Was this screen create WHILE a previous task is still running ?
        if(isTaskRunning) {
            progressDialog = ProgressDialog.show(getActivity(),
                    "Loading",
                    "Task still in progress");
        }
    }

    @Override
    public void onTaskStarted(DialogInterface.OnCancelListener onCancelListener) {
        isTaskRunning = true;
        progressDialog = ProgressDialog.show(getActivity(),
                "Loading",
                "Please wait a moment",
                true,
                true,
                onCancelListener);
    }

    @Override
    public void onTaskFinished() {
        if(progressDialog!=null) {
            progressDialog.dismiss();
        }
        isTaskRunning = false;
    }

    @Override
    public void onDetach() {
        // -- In case the task never finishes...
        if(progressDialog!=null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        super.onDetach();
    }
}
