package tests;

import com.github.javafaker.Faker;

import java.util.Locale;

import static tests.utils.RandomUtils.*;

public class TestData {
    Faker faker = new Faker(new Locale("ru"));

    public String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            phoneNumber = faker.phoneNumber().subscriberNumber(10),
            address = faker.address().secondaryAddress();

    public static String email = getRandomEmail(),
            gender = getRandomGender(),
            subject = getRandomSubject(),
            hobby = getRandomHobby(),
            image = "img.png",
            calendarDay = getRandomCalendarDay(),
            calendarMonth = getRandomCalendarMonth(),
            calendarYear = getRandomCalendarYear(),
            stateSelectPoint = getRandomStateSelectPoint(),
            citySelectPoint = getRandomCitySelectPoint(stateSelectPoint);
}
