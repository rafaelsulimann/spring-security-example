package com.sulimann.springsecurityexample.utils.validators.validenum;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidEnumValidator implements ConstraintValidator<ValidEnum, CharSequence> {

  private Class<? extends Enum<?>> klass;
  private boolean ignoreCase;

  @Override
  public void initialize(ValidEnum annotation) {
    this.klass = annotation.enumClass();
    this.ignoreCase = annotation.ignoreCase();
  }

  @Override
  public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
    if (value == null) {
      return true;
    }

    Enum<?>[] enumConstants = this.klass.getEnumConstants();
    for (Enum<?> enumConstant : enumConstants) {
      if (this.ignoreCase ? enumConstant.name().equalsIgnoreCase(value.toString())
          : enumConstant.name().equals(value.toString())) {
        return true;
      }
    }

    return false;
  }
}
