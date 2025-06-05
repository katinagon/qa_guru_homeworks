package tests.api;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class ReqresTestBase {
    public String usersEP = "/api/users/";
    public String apiKey = "reqres-free-v1";

    @BeforeAll
    public static void beforeAllApi() {
        RestAssured.baseURI = "https://reqres.in";
    }
}
