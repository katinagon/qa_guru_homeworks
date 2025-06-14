package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Student {
    private String name;
    private int age;
    @JsonProperty("isStudent")
    private boolean isStudent;
    private List<String> courses;
    private Address address;

    @Data
    public static class Address {
        private String city;
        private int index;
    }
}
