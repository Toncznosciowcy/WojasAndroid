package com.toncznosciowcy.wojas.data.model;

/**
 * Created by Grzesiek on 2015-03-15.
 */
/* Default model for Database classes */
public abstract class AbstractModel {

    public abstract String getCreateSQL();
    public abstract String getDropSQL ();
}
