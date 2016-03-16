package com.rhythmdiao.injection;

import com.google.common.base.Strings;
import com.rhythmdiao.annotation.Describer;
import com.rhythmdiao.constant.LoggerName;
import com.rhythmdiao.TypeConverter;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;

public abstract class AbstractInjector implements Injector {
    private static final Logger LOG = LoggerFactory.getLogger(LoggerName.INJECTOR);

    private Class<? extends Annotation> annotation;

    protected AbstractInjector(Class<? extends Annotation> annotation) {
        this.annotation = annotation;
    }

    public Class<? extends Annotation> getAnnotation() {
        return this.annotation;
    }

    protected abstract Pair<String, String> extract(Field field, HttpServletRequest request);

    public void injectField(Field field, HttpServletRequest request, Map<String, Object> fieldMap) {
        Pair<String, String> source = extract(field, request);
        if (!Strings.isNullOrEmpty(source.getValue())) {
            describeParam(field, source.getValue());
            Object value = convertParam(field, source.getValue());
            if (value != null) {
                fieldMap.put(source.getKey(), value);
            }
        }
    }

    public void describeParam(Field field, String param) {
        if (field.getAnnotation(Describer.class) != null && LOG.isDebugEnabled()) {
            LOG.debug("{}:{}={}", field.getAnnotation(Describer.class).comment(), field.getName(), param);
        }
    }

    public Object convertParam(Field field, String param) {
        if (field.getAnnotation(DateTimeFormat.class) != null) {
            return TypeConverter.convert(param, field.getType(), field.getAnnotation(DateTimeFormat.class).pattern());
        } else {
            return TypeConverter.convert(param, field.getType());
        }
    }
}
