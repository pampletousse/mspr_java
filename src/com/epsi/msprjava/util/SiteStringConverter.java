package com.epsi.msprjava.util;

import com.epsi.msprjava.model.Site;
import com.epsi.msprjava.model.TypeDechet;
import javafx.util.StringConverter;

public class SiteStringConverter extends StringConverter<Site> {

    public Site fromString(String string) {
        // convert from a string to a myClass instance
        return null;
    }

    public String toString(Site td) {
        // convert a myClass instance to the text displayed in the choice box
        return td.getNomsite();
    }

}
