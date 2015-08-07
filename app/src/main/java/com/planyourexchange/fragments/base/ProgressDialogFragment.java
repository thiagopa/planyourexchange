package com.planyourexchange.fragments.base;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;

import com.planyourexchange.R;

/**
 * Created by thiago on 05/08/15.
 */
// -- Used by all fragments that need a progress dialog for rest calls
public abstract class ProgressDialogFragment extends Fragment {

    private ProgressDialog progressDialog;

    protected void onTaskStarted() {
        progressDialog = ProgressDialog.show(getActivity(),
                getResources().getString(R.string.loading_title),
                getResources().getString(R.string.loading_dialog));
    }

    protected void onTaskFinished() {
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
