package org.javatime.examples;

import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;
import static java.util.stream.Collectors.toList;

public class SecondExamples {

    public boolean isTodayAfterTomorrow() {
        LocalDate aujourdhui = LocalDate.now();
        LocalDate demain = aujourdhui.plusDays(1);
        return demain.isAfter(aujourdhui);
    }

    public DayOfWeek getLastDayOfMonth() {
        Clock fixedClock = Clock.fixed(
                LocalDate.of(2025, Month.NOVEMBER, 1).atStartOfDay(ZoneId.of("Europe/Paris")).toInstant(),
                ZoneId.of("Europe/Paris")
        );

        return LocalDate.now(fixedClock).with(lastDayOfMonth()).getDayOfWeek();
    }

    //Longest day of year June 21st
    public int howManyDaysUntilLongestDayOfTheYear() {
        LocalDate today = LocalDate.now();
        LocalDate longestDay = today.with(Month.JUNE).withDayOfMonth(21);
        return Period.between(today, longestDay).getDays();
    }

    public Duration howLongUntilNewYear() {
        return null;
    }

    public LocalDate createDateFromJavaUtilDate() {
        return new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * On veut identifier le dernier jour de chaque mois d'une année.
     *
     * - Iteration sur les mois d'une année;
     *
     *
     */
    public List<DayOfWeek> lastDaysOfMonthsInYear(int year) {

        /**List<DayOfWeek> list = new ArrayList<>();

        for (Month month : Month.values()) {
            DayOfWeek dayOfWeek = LocalDate.now().withYear(year).with(month).with(TemporalAdjusters.lastDayOfMonth()).getDayOfWeek();
            list.add(dayOfWeek);
        }

        return list;*/

        return Stream.of(Month.values())
                .map(month -> LocalDate.now()
                        .withYear(year)
                        .with(month)
                        .with(lastDayOfMonth())
                        .getDayOfWeek())
                .collect(toList());
    }
}