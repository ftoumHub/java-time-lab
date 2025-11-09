package org.javatime.examples;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.time.*;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestSecondExamples {

    private final SecondExamples samples = new SecondExamples();

    @Test
    @Order(1)
    public void test_is_today_after_tomorrow() {
        assertTrue(samples.isTodayAfterTomorrow());
    }

    @Test
    @Order(2)
    public void test_lastDayOfMonth() {
        assertEquals(DayOfWeek.SUNDAY, samples.getLastDayOfMonth());
    }

    @Test
    @Order(3)
    public void test_period_to_longest_day() {
        LocalDate now = LocalDate.now();
        LocalDate longestDay = LocalDate.now().with(Month.JUNE).withDayOfMonth(21);
        Period untilLongestDay = now.until(longestDay);
        assertEquals(untilLongestDay.getDays(), samples.howManyDaysUntilLongestDayOfTheYear());
    }

    @Test
    @Order(4)
    public void test_duration_to_new_year() {
        Duration duration = samples.howLongUntilNewYear();

        //print duration for example
        System.out.print(duration);
        LocalDateTime now = LocalDateTime.now();
        ZonedDateTime gmtNewYear = ZonedDateTime.of(2014, 12, 31, 23, 59, 59, 0, ZoneId.of("Europe/London"));

        //Left as an exercise for the user to inject a clock
        //assertEquals(Duration.between(now, gmtNewYear), duration);

    }

    @Test
    @Order(5)
    public void create_local_date_from_date() {
        assertEquals(LocalDate.now(), samples.createDateFromJavaUtilDate());
    }

    @Test
    @Order(6)
    public void test_days_of_month_in_year() {
        List<DayOfWeek> days = samples.lastDaysOfMonthsInYear(2014);

        days.forEach(System.out::println);

        List<DayOfWeek> answers = asList(
                DayOfWeek.valueOf("FRIDAY"),
                DayOfWeek.valueOf("FRIDAY"),
                DayOfWeek.valueOf("MONDAY"),
                DayOfWeek.valueOf("WEDNESDAY"),
                DayOfWeek.valueOf("SATURDAY"),
                DayOfWeek.valueOf("MONDAY"),
                DayOfWeek.valueOf("THURSDAY"),
                DayOfWeek.valueOf("SUNDAY"),
                DayOfWeek.valueOf("TUESDAY"),
                DayOfWeek.valueOf("FRIDAY"),
                DayOfWeek.valueOf("SUNDAY"),
                DayOfWeek.valueOf("WEDNESDAY"));

        assertEquals(answers, days);
    }
}
