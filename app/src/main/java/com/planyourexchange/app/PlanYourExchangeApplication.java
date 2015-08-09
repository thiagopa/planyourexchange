package com.planyourexchange.app;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

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
public class PlanYourExchangeApplication extends Application {

    private PlanYourExchangeComponent planYourExchangeComponent;

    // -- Method used to initialize all components the app needs
    public void onCreate() {
        super.onCreate();

        // Create global configuration and initialize ImageLoader with this config
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);

        // -- Inject the application dependency through Generated Dagger
        planYourExchangeComponent = DaggerPlanYourExchangeComponent.builder()
                .planYourExchangeModule(new PlanYourExchangeModule(this))
                .build();

        planYourExchangeComponent.inject(this);
    }

    public static PlanYourExchangeComponent getPlanYourExchangeComponent(@NonNull Context context) {
        return ((PlanYourExchangeApplication) context.getApplicationContext()).planYourExchangeComponent;
    }


}
