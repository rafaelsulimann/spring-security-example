package com.sulimann.springsecurityexample.utils.constants;

public final class ErrorMessage {

    public static final String CAMPO_OBRIGATORIO = "Campo obrigatório";
    public static final String ERRO_INTERNO = "Erro interno. Operação não realizada!";

    private ErrorMessage() {
        throw new AssertionError("Não é permitido instanciar esta classe.");
    }

}
