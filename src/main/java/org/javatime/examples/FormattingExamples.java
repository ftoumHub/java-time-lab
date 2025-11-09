package org.javatime.examples;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FormattingExamples {

    public String formatSimpleDate() {
        return LocalDate.now().format(DateTimeFormatter.ISO_DATE);
    }

    //MM/DD/yyyy
    public String formatMonthDayYear() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    }

    public LocalDate parseLocalDate(String dateString) {
        return LocalDate.parse(dateString);
    }
}
