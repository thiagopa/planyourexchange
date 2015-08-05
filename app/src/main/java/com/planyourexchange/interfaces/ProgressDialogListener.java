package com.planyourexchange.interfaces;

import android.content.DialogInterface;

/**
 * Created by thiago on 05/08/15.
 */
public interface ProgressDialogListener {
    void onTaskStarted(DialogInterface.OnCancelListener onCancelListener);
    void onTaskFinished();
}
