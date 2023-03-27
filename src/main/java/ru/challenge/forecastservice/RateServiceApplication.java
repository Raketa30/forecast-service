package ru.challenge.forecastservice;

import ru.challenge.forecastservice.factory.ApplicationFactory;

public class RateServiceApplication {
    public static void main(String[] args) {
        ApplicationFactory.create().start();
    }
}