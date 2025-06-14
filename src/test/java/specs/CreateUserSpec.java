package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.*;
import static io.restassured.http.ContentType.JSON;

public class CreateUserSpec {
    public static String apiKey = "reqres-free-v1";

    public static RequestSpecification createUserRequestSpec = with()
            .filter(withCustomTemplates())
            .header("x-api-key", apiKey)
            .log().uri()
            .log().body()
            .log().headers()
            .contentType(JSON);

    public static RequestSpecification updateUserRequestSpec = with()
                .filter(withCustomTemplates())
                .header("x-api-key", apiKey)
                .log().uri()
                .log().body()
                .log().headers()
                .contentType(JSON);

    public static RequestSpecification deleteUserRequestSpec = with()
                .filter(withCustomTemplates())
                .header("x-api-key", apiKey)
                .log().uri()
                .log().headers();


    public static ResponseSpecification createUserResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .log(STATUS)
            .log(BODY)
            .build();

    public static ResponseSpecification updateUserResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(STATUS)
            .log(BODY)
            .build();

    public static ResponseSpecification deleteUserResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(204)
            .log(STATUS)
            .build();
}
