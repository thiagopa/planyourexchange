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

package com.planyourexchange.interfaces;

import android.os.Bundle;

import com.planyourexchange.pageflow.PageFlow;

/**
 * @author Thiago Pagonha
 * @version 17/08/15.
 */
public interface ViewPagerControl {
    // -- Goes to specific screen with bundle
    void nextScreen(PageFlow pageFlow, Bundle bundle);
    // -- Goes back one screen in case of failures, also remove associated bundles
    void previousScreen();
}