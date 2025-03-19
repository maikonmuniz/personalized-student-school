package com.university.personalizedLessons.interfaceAdapters;

public interface InterCrypt {
    String encrypt (String password);
    boolean verifyPassword (String password, String passwordCrypt);
}
