package com.rhythmdiao.injection;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.Map;

public interface Injector {
    void injectField(Field filed, HttpServletRequest request, Map<String, Object> fieldMap);
}
