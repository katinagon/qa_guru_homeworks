package tests.api;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class ReqresTestBase {
    public static String usersEP = "/users/";

    @BeforeAll
    public static void beforeAllApi() {
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api";
    }
}
