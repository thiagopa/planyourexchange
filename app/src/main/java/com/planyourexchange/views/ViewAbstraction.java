package com.planyourexchange.views;

import android.content.Context;
import android.text.Layout;
import android.view.ViewGroup;

/**
 * Created by thiago on 31/07/15.
 */
public interface ViewAbstraction {
    // -- Used to retrieve the activity context
    Context getViewContext();
    // -- Used to retrieve the activity main Layout
    ViewGroup getViewLayout();
}
