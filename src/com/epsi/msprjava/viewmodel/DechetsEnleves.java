package com.epsi.msprjava.viewmodel;

import com.epsi.msprjava.model.TypeDechet;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class DechetsEnleves {

    public DechetsEnleves() {
        this.mapDechetsEnleves = new Map<TypeDechet, Long>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean containsKey(Object key) {
                return false;
            }

            @Override
            public boolean containsValue(Object value) {
                return false;
            }

            @Override
            public Long get(Object key) {
                return null;
            }

            @Override
            public Long put(TypeDechet key, Long value) {
                return null;
            }

            @Override
            public Long remove(Object key) {
                return null;
            }

            @Override
            public void putAll(Map<? extends TypeDechet, ? extends Long> m) {

            }

            @Override
            public void clear() {

            }

            @Override
            public Set<TypeDechet> keySet() {
                return null;
            }

            @Override
            public Collection<Long> values() {
                return null;
            }

            @Override
            public Set<Entry<TypeDechet, Long>> entrySet() {
                return null;
            }
        };
    }

    private Map<TypeDechet, Long> mapDechetsEnleves;

    public Map<TypeDechet, Long> getMapDechetsEnleves() {
        return mapDechetsEnleves;
    }

    public void setMapDechetsEnleves(Map<TypeDechet, Long> mapDechetsEnleves) {
        this.mapDechetsEnleves = mapDechetsEnleves;
    }
}
