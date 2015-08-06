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
        String translated = null;
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier(format(text),"string",context.getPackageName());
        if(resourceId!=0) {
            try {
                translated = resources.getString(resourceId);
            } catch(Resources.NotFoundException nfe) {
              // Returns text if can't translate it
            }
        }
        return translated!=null?translated:text;
    }

    // -- Strips spaces for key lookup
    private static String format(String text) {
        return text.replaceAll(" ", "_");
    }
}
