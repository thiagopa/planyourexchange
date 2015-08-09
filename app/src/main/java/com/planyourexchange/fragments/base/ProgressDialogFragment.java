package com.planyourexchange.fragments.base;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;

import com.planyourexchange.R;

/**
 * Copyright (C) 2015, Thiago Pagonha,
 * Plan Your Exchange, easy exchange to fit your budget
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
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
