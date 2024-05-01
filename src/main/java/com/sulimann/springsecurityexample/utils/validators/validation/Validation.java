package com.sulimann.springsecurityexample.utils.validators.validation;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import com.sulimann.springsecurityexample.utils.httpresponse.ErroResponse;
import com.sulimann.springsecurityexample.utils.httpresponse.Resultado;

public class Validation<T> {
    private final T value;
    private boolean isValid = true;
    private Resultado<?, ErroResponse> erro;
    private Map<String, Object> entities = new HashMap<>();

    private Validation(T value) {
        this.value = value;
    }

    public static <T> Validation<T> of(T value) {
        return new Validation<>(value);
    }

    public Validation<T> ifIsTrue(boolean condition) {
        if (isValid && condition) {
            isValid = false;
        }
        return this;
    }

    public <U> Validation<T> ifNotPresent(String key, Optional<U> optional) {
        if (!optional.isPresent()) {
            isValid = false;
        } else {
            entities.put(key, optional.get());
        }
        return this;
    }

    public Validation<T> thenReturn(Supplier<Resultado<?, ErroResponse>> supplier) {
        if (!isValid && erro == null) {
            erro = supplier.get();
        }
        return this;
    }

    @SuppressWarnings("unchecked")
    public <R> Resultado<R, ErroResponse> finallyExecute(Function<T, Resultado<R, ErroResponse>> function) {
        if (isValid) {
            return function.apply(value);
        } else {
            return (Resultado<R, ErroResponse>) erro;
        }
    }

    @SuppressWarnings("unchecked")
    public <R> Resultado<R, ErroResponse> finallyExecute(
            BiFunction<T, Map<String, Object>, Resultado<R, ErroResponse>> function) {
        if (isValid) {
            return function.apply(value, entities);
        } else {
            return (Resultado<R, ErroResponse>) erro;
        }
    }
}
