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

package com.planyourexchange.app;

import com.planyourexchange.activities.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Pairing between module and injection targets
 * @author Thiago Pagonha
 * @version 09/08/15.
 */
@Singleton
@Component(modules = PlanYourExchangeModule.class)
public interface PlanYourExchangeComponent {
    void inject(PlanYourExchangeApplication planYourExchangeApplication);
    void inject(MainActivity mainActivity);
}
