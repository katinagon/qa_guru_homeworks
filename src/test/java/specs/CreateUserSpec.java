package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import tests.api.ReqresTestBase;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;

public class CreateUserSpec extends ReqresTestBase {
    public static RequestSpecification createUserRequestSpec = with()
            .filter(withCustomTemplates())
            .header("x-api-key", apiKey)
            .log().uri()
            .log().body()
            .log().headers()
            .contentType(JSON)
            .basePath(usersEP);

    public static RequestSpecification updateUserRequestSpec(String userId) {
        return with()
                .filter(withCustomTemplates())
                .header("x-api-key", apiKey)
                .log().uri()
                .log().body()
                .log().headers()
                .contentType(JSON)
                .basePath(usersEP + userId);
    }

    public static RequestSpecification deleteUserRequestSpec(String userId) {
        return with()
                .filter(withCustomTemplates())
                .header("x-api-key", apiKey)
                .log().uri()
                .log().headers()
                .basePath(usersEP + userId);
    }

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
