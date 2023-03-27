package ru.challenge.forecastservice.validator;

public interface Validator<T> {
    boolean isValid(T data);
}
