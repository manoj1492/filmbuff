package com.mycompany.filmbuff.util.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.mycompany.filmbuff.util.validators.EnumValidator;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = EnumValidator.class)
public @interface ValidEnumValue {

    String message() default "{com.mycompany.filmbuff.constraints.validenumvalue}";

    Class<?> enumClass();

    // The below two methods are needed to be declared to avoid exception
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
}
