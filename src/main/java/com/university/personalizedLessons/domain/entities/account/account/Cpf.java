package com.university.personalizedLessons.domain.valueObject.account;

import java.util.regex.Pattern;

public class Cpf {
    private final boolean isValid;
    private final String value;
    private static final Pattern CPF_PATTERN = Pattern.compile("^[0-9]{11}$");

    private Cpf(String value) {
        this.isValid = validCpf(value);
        this.value = value;
    }

    public static Cpf create(String value) {
        value = value.replaceAll("[^0-9]", "");
        return new Cpf(value);
    }

    private boolean validCpf(String cpf) {
        if (cpf.length() != 11) {
            return false;
        }

        if (!CPF_PATTERN.matcher(cpf).matches()) {
            return false;
        }

        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        return validateCharacter(cpf);
    }

    private boolean validateCharacter(String cpf) {
        int[] weights1 = {10, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] weights2 = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};

        int sum1 = 0;
        for (int i = 0; i < 9; i++) {
            sum1 += Character.getNumericValue(cpf.charAt(i)) * weights1[i];
        }
        int digit1 = calculateValue(sum1);

        int sum2 = 0;
        for (int i = 0; i < 9; i++) {
            sum2 += Character.getNumericValue(cpf.charAt(i)) * weights2[i];
        }
        sum2 += digit1 * 2;
        int digit2 = calculateValue(sum2);

        return cpf.charAt(9) == (digit1 + '0') && cpf.charAt(10) == (digit2 + '0');
    }

    private int calculateValue(int sum) {
        int remainder = sum % 11;
        return (remainder < 2) ? 0 : 11 - remainder;
    }

    public boolean isValid() {
        return this.isValid;
    }

    public String getValue() {
        return this.value;
    }
}
