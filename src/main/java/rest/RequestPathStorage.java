package rest;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;

public enum RequestPathStorage {
    instance;

    private Table<String, String, Object> pathTable;

    RequestPathStorage() {
        pathTable = HashBasedTable.create();
    }

    public void setPathMap(String method, String uri, Object handler) {
        this.pathTable.put(method, uri, handler);
    }

    public static ImmutableTable<String, String, Object> getPathMap() {
        return ImmutableTable.copyOf(getInstance().pathTable);
    }

    public static RequestPathStorage getInstance() {
        return instance;
    }
}
