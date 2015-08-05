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

    @Override
    public void onTaskStarted(DialogInterface.OnCancelListener onCancelListener) {
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
