package com.planyourexchange.fragments.costofliving;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.planyourexchange.R;

/**
 * Created by thiago on 06/08/15.
 */
public class CostOfLivingFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cost_of_living_fragment, container, false);
        return view;
    }
}
