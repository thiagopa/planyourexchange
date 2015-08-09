package com.planyourexchange.utils;

import android.content.Context;
import android.content.res.Resources;

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
