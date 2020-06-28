package com.epsi.msprjava.viewmodel;

import com.epsi.msprjava.model.TypeDechet;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DechetsEnleves {

    private Map<TypeDechet, Long> mapDechetsEnleves;

    public DechetsEnleves() {
        this.mapDechetsEnleves = new HashMap<TypeDechet, Long>();
    }

    public Map<TypeDechet, Long> getMapDechetsEnleves() {
        return mapDechetsEnleves;
    }

    public void setMapDechetsEnleves(Map<TypeDechet, Long> mapDechetsEnleves) {
        this.mapDechetsEnleves = mapDechetsEnleves;
    }
}
