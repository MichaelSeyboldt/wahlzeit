package org.wahlzeit.annotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface DesignPattern {
    public String name() default "";

    public String[] participants() default {};

    public String description() default "" ;

}
