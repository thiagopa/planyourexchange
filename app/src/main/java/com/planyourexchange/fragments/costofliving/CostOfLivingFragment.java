package com.planyourexchange.fragments.costofliving;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.planyourexchange.R;
import com.planyourexchange.fragments.base.AbstractBaseFragment;
import com.planyourexchange.fragments.base.GenericFragment;
import com.planyourexchange.interfaces.OnChangeListener;
import com.planyourexchange.rest.model.CostOfLiving;

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
public class CostOfLivingFragment extends AbstractBaseFragment<Integer,CostOfLiving,LinearLayout> {

    public CostOfLivingFragment() {
        super(R.string.cost_of_living_title,R.layout.cost_of_living_fragment,R.id.cost_of_living_linear_layout);
    }

    @Override
    protected void callService(Integer integer) {
        serverApi.getCostOfLiving(integer,this);
    }

    @Override
    protected void drawModel(CostOfLiving modelList, LinearLayout linearLayout) {
        linearLayout.addView();
    }
}
