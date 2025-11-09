package org.javatime.examples;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestJsonExamples {

    @Test
    @Order(1)
    void serializeLocalDateToIsoString() throws Exception {
        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        LocalDate date = LocalDate.of(2025, 11, 9);
        String json = mapper.writeValueAsString(date);

        assertEquals("\"2025-11-09\"", json);
    }

    @Test
    @Order(2)
    void deserializeIsoStringToLocalDate() throws Exception {
        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule());

        String json = "\"2025-11-09\"";
        LocalDate date = mapper.readValue(json, LocalDate.class);

        assertEquals(LocalDate.of(2025, 11, 9), date);
    }

    @Test
    @Order(3)
    void serializeLocalDateAsTimestamp() throws Exception {
        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        LocalDate date = LocalDate.of(2025, 11, 9);
        String json = mapper.writeValueAsString(date);

        assertEquals("[2025,11,9]", json);
    }

    static class Event {
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        public LocalDate date;
    }

    @Test
    @Order(4)
    void serializeLocalDateWithCustomPattern() throws Exception {
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        Event event = new Event();
        event.date = LocalDate.of(2025, 11, 9);

        String json = mapper.writeValueAsString(event);
        assertTrue(json.contains("09/11/2025"));
    }

    @Test
    @Order(5)
    void failOnInvalidDateFormat() {
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        String invalidJson = "\"09-11-2025\""; // mauvais format

        assertThrows(JsonProcessingException.class, () ->
                mapper.readValue(invalidJson, LocalDate.class)
        );
    }

    static class Person {
        public String name;
        public LocalDate birthDate;
    }

    @Test
    @Order(6)
    void serializeAndDeserializeObjectWithLocalDate() throws Exception {
        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        Person original = new Person();
        original.name = "Alice";
        original.birthDate = LocalDate.of(1990, 5, 15);

        String json = mapper.writeValueAsString(original);
        Person deserialized = mapper.readValue(json, Person.class);

        assertEquals(original.name, deserialized.name);
        assertEquals(original.birthDate, deserialized.birthDate);
    }

    @Test
    @Order(7)
    void serializePerson_shouldProduceCorrectJsonFormat() throws Exception {
        // 🔧 Configuration du mapper
        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // 🧍‍♂️ Création de l’objet à sérialiser
        Person person = new Person();
        person.name = "Alice";
        person.birthDate = LocalDate.of(1990, 5, 15);

        // 🎯 Sérialisation en JSON
        String json = mapper.writeValueAsString(person);

        // 🔍 Vérification exacte du format attendu
        String expectedJson = "{\"name\":\"Alice\",\"birthDate\":\"1990-05-15\"}";
        assertEquals(expectedJson, json);
    }

}
