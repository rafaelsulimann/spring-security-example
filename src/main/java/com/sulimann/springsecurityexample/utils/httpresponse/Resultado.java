package com.sulimann.springsecurityexample.utils.httpresponse;

public abstract class Resultado<S, E> {
  private Resultado() {}

  public static <S, E> Resultado<S, E> sucesso(S value) {
      return new Sucesso<>(value);
  }

  public static <S, E> Resultado<S, E> erro(E value) {
      return new Erro<>(value);
  }

  public abstract boolean isSucesso();
  public abstract boolean isErro();
  public abstract S getSucesso();
  public abstract E getErro();

  private static class Sucesso<S, E> extends Resultado<S, E> {
      private final S value;

      private Sucesso(S value) {
          this.value = value;
      }

      public boolean isSucesso() { return true; }
      public boolean isErro() { return false; }
      public S getSucesso() { return value; }
      public E getErro() { throw new UnsupportedOperationException("Não é Erro"); }
  }

  private static class Erro<S, E> extends Resultado<S, E> {
      private final E value;

      private Erro(E value) {
          this.value = value;
      }

      public boolean isSucesso() { return false; }
      public boolean isErro() { return true; }
      public S getSucesso() { throw new UnsupportedOperationException("Não é Sucesso"); }
      public E getErro() { return value; }
  }
}
