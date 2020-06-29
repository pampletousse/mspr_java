package com.epsi.msprjava.util;

import com.epsi.msprjava.model.TypeDechet;
import javafx.util.StringConverter;

public class TypeDechetStringConverter extends StringConverter<TypeDechet> {

    public TypeDechet fromString(String string) {
        // convert from a string to a myClass instance
        return null;
    }

    public String toString(TypeDechet td) {
        // convert a myClass instance to the text displayed in the choice box
        return td.getNom() + " " + td.getNivDanger();
    }
}
