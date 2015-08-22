package com.planyourexchange.rest.model;

import com.planyourexchange.interfaces.GenericModel;

import java.io.Serializable;

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
public class BaseModel implements Comparable<BaseModel>, GenericModel, Serializable {

    private String icon;
    private String name;
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public boolean equals(Object o) {

        if(o!=null && o instanceof BaseModel) {
            return this.id.equals(((BaseModel) o).id);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return this.id;
    }

    @Override
    public int compareTo(BaseModel another) {
        return name.compareTo(another.name);
    }

    @Override
    public String toString() {
        return name;
    }
}
