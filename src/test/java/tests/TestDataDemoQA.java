package tests;

import com.github.javafaker.Faker;

import java.util.Locale;

public class TestDataDemoQA {
    static Faker fakerRU = new Faker(new Locale("ru"));
    static Faker fakerEN = new Faker(new Locale("en"));

    public static String firstName = fakerRU.name().firstName(),
            lastName = fakerRU.name().lastName(),
            phoneNumber = fakerRU.phoneNumber().subscriberNumber(10),
            email = fakerEN.internet().emailAddress(),
            address = fakerRU.address().secondaryAddress(),
            gender = fakerRU.options().option("Male", "Female", "Other"),
            subject = fakerRU.options().option("Physics", "Commerce", "Chemistry", "Maths", "Arts", "Biology"),
            hobby = fakerRU.options().option("Sports", "Reading", "Music"),
            calendarDay = Integer.toString(fakerRU.number().numberBetween(1, 28)),
            calendarMonth = fakerRU.options().option("January", "February", "March", "April", "May", "June",
                    "July", "August", "September", "October", "November", "December"),
            calendarYear = fakerRU.options().option("1980", "1981", "1982", "1983", "1984", "1985", "1986",
                    "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998",
                    "1999", "2000", "2001", "2002", "2003", "2004", "2005"),
            stateSelectPoint = fakerRU.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan"),
            citySelectPoint = getRandomCitySelectPoint(stateSelectPoint);

    public static String image = "img.png";

    public static String getRandomCitySelectPoint(String stateSelectPoint) {
        String result = "";
        switch (stateSelectPoint) {
            case "NCR" -> result = fakerRU.options().option("Delhi", "Gurgaon", "Noida");
            case "Uttar Pradesh" -> result = fakerRU.options().option("Agra", "Lucknow", "Merrut");
            case "Haryana" -> result = fakerRU.options().option("Karnal", "Panipat");
            case "Rajasthan" -> result = fakerRU.options().option("Jaipur", "Jaiselmer");
        }
        return result;
    }
}
