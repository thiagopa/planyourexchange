package com.planyourexchange.utils;

import android.content.Context;
import android.content.res.Resources;

/**
 * Created by thiago on 05/08/15.
 */
// -- Internationalization for Country/City names that comes from Rest Server
public final class InternationalNames {
    private InternationalNames() {
    }

    public static String getInternationalName(Context context, String text) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier(format(text),"string",context.getPackageName());
        if(resourceId!=0) {
            return resources.getString(resourceId);
        }
        return text;
    }

    // -- Strips spaces for key lookup
    private static String format(String text) {
        return text.replaceAll(" ","_");
    }
}
