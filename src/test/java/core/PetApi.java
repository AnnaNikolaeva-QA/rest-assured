package core;

import io.restassured.http.ContentType;
import io.restassured.response.*;

import java.util.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsEqual.equalTo;

public class PetApi extends BaseApi {
    private final String endPoint = "/pet";
    private final ArrayList<String> statusPets = new ArrayList<>(List.of("available", "pending", "sold"));

    public Response getById(int id, int expectResponseCode) {
        return given().spec(req.basePath(endPoint))
                .log().all()
                .when().get("/{petId}", id)
                .then().log().all()
                .statusCode(expectResponseCode)
                .contentType(ContentType.JSON)
                .body(
                        "id", equalTo(id),
                        "status", isIn(statusPets)
                )
                .extract().response();
    }

    public Response getById(String idStr, int expectResponseCode) {
        return given().spec(req.basePath(endPoint))
                .when().get("/{petId}", idStr)
                .then().log().all()
                .statusCode(expectResponseCode)
                .extract().response();
    }

    public ValidatableResponse addNewPet(Object body, int expectResponseCode) {
        return given().spec(req.basePath(endPoint))
                .contentType(ContentType.JSON)
                .log().all()
                .body(body)
                .log().all()
                .when().post()
                .then().statusCode(expectResponseCode);
    }
}
