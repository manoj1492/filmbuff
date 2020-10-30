package com.mycompany.filmbuff.util.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.mycompany.filmbuff.util.annotations.ValidEnumValue;

public class EnumValidator implements ConstraintValidator<ValidEnumValue, String>{

    Object[] constants;

    @Override
    public void initialize(ValidEnumValue constraintAnnotation) {

        Class<?> enumClass = constraintAnnotation.enumClass();
        constants = enumClass.getEnumConstants();
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        for(Object obj: constants){
            if(value.equals(obj.toString()))
                return true;
        }
        return false;
    }
    
}
