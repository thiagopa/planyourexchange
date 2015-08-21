/*
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

package com.planyourexchange.locator;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;
import com.planyourexchange.rest.api.ServerApi;
import com.planyourexchange.rest.model.UserLocation;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * @author Thiago Pagonha
 * @version 20/08/15.
 */
public class LocationService implements ConnectionCallbacks, OnConnectionFailedListener, Callback<String[]> {

    private static final String TAG = "LocationService";

    private final GoogleApiClient googleApiClient;
    private final ServerApi serverApi;
    private Location location;
    private String[] airports;

    public LocationService(Context context, ServerApi serverApi) {
        this.serverApi = serverApi;
        this.googleApiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        googleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        serverApi.findNearbyAirports(new UserLocation(location), this);
        googleApiClient.disconnect();
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.e(TAG,"Connection Failed, Error code is " + connectionResult.getErrorCode());
    }

    @Override
    public void success(String[] airports, Response response) {
        this.airports = airports;
    }

    @Override
    public void failure(RetrofitError error) {
        Log.e(TAG,"Couldn't find origin airport");
    }

    public String[] getAirports() {
        return airports;
    }
}
