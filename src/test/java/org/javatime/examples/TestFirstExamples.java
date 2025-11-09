package org.javatime.examples;


import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestFirstExamples {

    private final FirstExamples firstExamples = new FirstExamples();

    @Test
    @Order(1)
    public void verify_henry_date_of_birth_is_correct() {
        final LocalDate henry_viii_date_of_birth = firstExamples.getHenrysBirthday();
        assertEquals(1491, henry_viii_date_of_birth.getYear());
        assertEquals(28, henry_viii_date_of_birth.getDayOfMonth());
        assertEquals(Month.JUNE, henry_viii_date_of_birth.getMonth());
    }

    @Test
    @Order(2)
    public void test_time_is_13_51() {
        final LocalTime localTime = firstExamples.getSampleLocalTime();
        assertEquals(13, localTime.getHour());
        assertEquals(51, localTime.getMinute());
        assertEquals(0, localTime.getSecond());
    }

    @Test
    @Order(3)
    public void test_henry_birth_and_time() {
        final LocalDateTime combined = firstExamples.getSampleLocalDateTime();
        verifyCombinedDateTime(combined);
    }

    @Test
    @Order(4)
    public void test_combined_date_time() {
        verifyCombinedDateTime(firstExamples.getComponentDateTime());
    }

    @Test
    @Order(5)
    public void test_date_from_time() {
        assertEquals(LocalDate.now(), firstExamples.getTodayFromLocalDateTime());
    }

    @Test
    @Order(6)
    public void test_time_difference_between_london_and_paris() {
        assertEquals(1, firstExamples.getDifferenceBetweenParisAndLondon());
    }

    private void verifyCombinedDateTime(LocalDateTime combined) {
        assertEquals(1491, combined.getYear());
        assertEquals(28, combined.getDayOfMonth());
        assertEquals(Month.JUNE, combined.getMonth());
        assertEquals(13, combined.getHour());
        assertEquals(51, combined.getMinute());
        assertEquals(0, combined.getSecond());
    }
}