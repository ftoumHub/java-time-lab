package org.javatime.examples;

import java.time.*;

public class FirstExamples {

    private final static ZoneId EUROPE_PARIS = ZoneId.of("Europe/Paris");
    private final static ZoneId EUROPE_LONDRES = ZoneId.of("Europe/London");


    // Créer une date simple - Henry VIII Birthday - June 28th 1491
    public LocalDate getHenrysBirthday() {
        // L'API LocalDate est basée sur des méthodes factory, pas de constructeur publique.
        return LocalDate.of(1491, Month.JUNE, 28);
    }

    // Créer une heure simple - 13:51
    public LocalTime getSampleLocalTime() {
        return LocalTime.of(13, 51);
    }

    // Créer un LocalDateTime à partir des données précédentes
    public LocalDateTime getSampleLocalDateTime() {
        return LocalDateTime.of(1491, Month.JUNE, 28, 13, 51);
    }

    public LocalDateTime getComponentDateTime() {
        return LocalDateTime.of(getHenrysBirthday(), getSampleLocalTime());
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