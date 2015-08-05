package com.planyourexchange.fragments.base;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        setRetainInstance(true);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // -- Was this screen create WHILE a previous task is still running ?
        if(isTaskRunning) {
            createDialog();
        }
    }

    private void createDialog() {
        progressDialog = ProgressDialog.show(getActivity(),"Loading","Please wait a moment");
    }

    @Override
    public void onTaskStarted() {
        isTaskRunning = true;
        createDialog();
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
