package com.sulimann.springsecurityexample.utils.constants;

public final class Path {

    public static final String INIT = "/init";
    public static final String USUARIO = "/usuarios";
    public static final String LOGIN = "/login";

    private Path() {
        throw new AssertionError("Não é permitido instanciar esta classe.");
    }

}
