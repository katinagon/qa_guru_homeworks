package tests.utils;

import java.security.SecureRandom;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils {
    public static String getRandomString(int len) {
        String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));

        return sb.toString();
    }

    public static String getRandomEmail() {
        return getRandomString(6) + "@test.com";
    }

    public static int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static String getRandomGender() {
        String[] genders = {"Male", "Female", "Other"};
        return getRandomItemFromArray(genders);
    }

    public static String getRandomItemFromArray(String[] array) {
        int index = getRandomInt(0, array.length - 1);
        return array[index];
    }

    public static String getRandomSubject() {
        String[] subjects = {"Physics", "Commerce", "Chemistry", "Maths", "Arts", "Biology"};
        return getRandomItemFromArray(subjects);
    }

    public static String getRandomHobby() {
        String[] hobbies = {"Sports", "Reading", "Music"};
        return getRandomItemFromArray(hobbies);
    }

    public static String getRandomCalendarDay() {
        int day = getRandomInt(1, 28);
        return Integer.toString(day);
    }

    public static String getRandomCalendarMonth() {
        String[] months = {"January", "February", "March", "April", "May", "June", "July",
                "August", "September", "October", "November", "December"};
        return getRandomItemFromArray(months);
    }

    public static String getRandomCalendarYear() {
        String[] years = {"1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979",
                "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992",
                "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005"};
        return getRandomItemFromArray(years);
    }

    public static String getRandomStateSelectPoint() {
        String[] stateSelectPoints = {"NCR", "Uttar Pradesh", "Haryana", "Rajasthan"};
        return getRandomItemFromArray(stateSelectPoints);
    }

    public static String getRandomCitySelectPoint(String stateSelectPoint) {
        String[] citySelectPointOfNCR = {"Delhi", "Gurgaon", "Noida"};
        String[] citySelectPointOfUttarPradesh = {"Agra", "Lucknow", "Merrut"};
        String[] citySelectPointOfHaryana = {"Karnal", "Panipat"};
        String[] citySelectPointOfRajasthan = {"Jaipur", "Jaiselmer"};
        String result = "";
        switch (stateSelectPoint) {
            case "NCR" -> result = getRandomItemFromArray(citySelectPointOfNCR);
            case "Uttar Pradesh" -> result = getRandomItemFromArray(citySelectPointOfUttarPradesh);
            case "Haryana" -> result = getRandomItemFromArray(citySelectPointOfHaryana);
            case "Rajasthan" -> result = getRandomItemFromArray(citySelectPointOfRajasthan);
        }
        return result;
    }
}
