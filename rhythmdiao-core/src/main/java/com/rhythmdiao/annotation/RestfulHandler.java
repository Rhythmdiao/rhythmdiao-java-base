package com.rhythmdiao.annotation;

import org.eclipse.jetty.http.HttpMethods;

import java.lang.annotation.*;

/**
 * request handler annotation
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RestfulHandler {
    String target();

    String method() default HttpMethods.GET;

    String identification() default "";

    String description();

    int cache() default 0;
}
