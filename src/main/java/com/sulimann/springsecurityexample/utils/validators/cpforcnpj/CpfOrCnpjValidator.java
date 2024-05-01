package com.sulimann.springsecurityexample.utils.validators.cpforcnpj;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
import org.springframework.util.Assert;

public class CpfOrCnpjValidator implements ConstraintValidator<CpfOrCnpj, String>{

    @Override
    public boolean isValid(String documento, ConstraintValidatorContext context) {
        Assert.hasLength(documento, "Documento precisa ser preenchido");
        return isCpfValido(documento) || isCnpjValido(documento);
    }

    private boolean isCpfValido(String documento) {
        CPFValidator cpfValidator = new CPFValidator();
        cpfValidator.initialize(null);
        return cpfValidator.isValid(documento, null);
    }

    private boolean isCnpjValido(String documento) {
        CNPJValidator cnpjValidator = new CNPJValidator();
        cnpjValidator.initialize(null);
        return cnpjValidator.isValid(documento, null);
    }

}
