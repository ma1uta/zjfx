package org.zjfx;

import javafx.util.StringConverter;
import javafx.util.converter.DefaultStringConverter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface Attribute {
    String modelAttribute() default "";
    String controlProperty() default "text";
    Class<? extends StringConverter> converter() default DefaultStringConverter.class;
}
