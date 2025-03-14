package com.university.personalizedLessons.domain.valueObject;

public class Cpf {

    private boolean value;

    public Cpf (String value) {
        this.value = isCPF(value);
    }

    public static boolean isCPF(String cpf) {
        if (cpf == null || cpf.isEmpty()) {
            return false;
        }

        if (cpf.length() != 11) return false;

        if (cpf.chars().distinct().count() == 1) {
            return false;
        }

        try {
            return verificaDigito(cpf, 9) && verificaDigito(cpf, 10);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean verificaDigito(String cpf, int posicao) {
        int soma = 0;
        for (int i = 0; i < posicao; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * ((posicao + 1) - i);
        }
        int digitoCalculado = 11 - (soma % 11);
        digitoCalculado = (digitoCalculado >= 10) ? 0 : digitoCalculado;
        return digitoCalculado == Character.getNumericValue(cpf.charAt(posicao));
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}
