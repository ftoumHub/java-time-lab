package org.javatime.examples;

import java.time.*;

public class FirstExamples {

    private final static ZoneId EUROPE_PARIS = ZoneId.of("Europe/Paris");
    private final static ZoneId EUROPE_LONDRES = ZoneId.of("Europe/London");


    // Créer une date simple - Albert EINSTEIN - 14 Mars 1879
    public LocalDate getEinsteinBirthday() {
        // L'API LocalDate est basée sur des méthodes factory, pas de constructeur publique.
        return LocalDate.of(1879, Month.MARCH, 14);
    }

    // Créer une heure simple - 11:30
    public LocalTime getSampleLocalTime() {
        return LocalTime.of(11, 30);
    }

    // Créer un LocalDateTime à partir des données précédentes
    public LocalDateTime getSampleLocalDateTime() {
        return LocalDateTime.of(1879, Month.MARCH, 14, 11, 30);
    }

    public LocalDateTime getComponentDateTime() {
        return LocalDateTime.of(getEinsteinBirthday(), getSampleLocalTime());
    }

    // Conversion LocalDateTime => LocalDate
    public LocalDate getTodayFromLocalDateTime() {
        return LocalDateTime.now().toLocalDate();
    }

    public int getDifferenceBetweenParisAndLondon() {
        ZonedDateTime paris = ZonedDateTime.now(EUROPE_PARIS);
        ZonedDateTime londres = ZonedDateTime.now(EUROPE_LONDRES);
        return paris.getHour() - londres.getHour();
    }
}