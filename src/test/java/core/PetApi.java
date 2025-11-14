package core;

import dto.PetRequestUpdatePet;
import dto.PetRequestUploadImage;
import io.restassured.http.ContentType;
import io.restassured.response.*;

import java.util.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsEqual.equalTo;

public class PetApi extends BaseApi {
    private final String endPoint = "/pet";
    private final ArrayList<String> statusPets = new ArrayList<>(List.of("available", "pending", "sold"));

    public Response getById(int id, int expectResponseCode) { // 5 Find pet by ID
        return given()
                .spec(req.basePath(endPoint))
                .log().all()
                .when()
                .get("/{petId}", id)
                .then()
                .log().all()
                .statusCode(expectResponseCode)
                .body(
                        "id", equalTo(id),
                        "status", isIn(statusPets)
                )
                .extract().response();
    }

    public Response getById(String idStr, int expectResponseCode) {
        return given()
                .spec(req.basePath(endPoint))
                .when()
                .get("/{petId}", idStr)
                .then()
                .log().all()
                .statusCode(expectResponseCode)
                .extract().response();
    }

    public ValidatableResponse addNewPet(Object body, int expectResponseCode) {  // 2 Add a new pet to the store
        return given()
                .spec(req.basePath(endPoint))
                .log().all()
                .body(body)
                .log().all()
                .when()
                .post()
                .then()
                .statusCode(expectResponseCode);
    }

    public ValidatableResponse findByStatus(String value, int expectResponseCode) {  // 4 Finds pets by status
        return given()
                .spec(req.basePath(endPoint))
                .when()
                .get("findByStatus?status=" + value)
                .then()
                .log().all()
                .statusCode(expectResponseCode);
    }

    public ValidatableResponse uploadsImage(PetRequestUploadImage body, int expectResponseCode) { // 1 uploads an image
        return given()
                .spec(req.basePath(endPoint))
                .contentType(ContentType.MULTIPART)
                .pathParam("petId", body.getPetId())
                .multiPart("additionalMetadata", body.getAdditionalMetadata())
                .multiPart("file", body.getFile())
                .when()
                .post("/{petId}/uploadImage")
                .then()
                .log().all()
                .statusCode(expectResponseCode);
    }

    public ValidatableResponse updatePet(PetRequestUpdatePet body, int expectResponseCode) { // 3 update an existing pet
        return given()
                .spec(req.basePath(endPoint))
                .body(body)
                .log().all()
                .when()
                .post()
                .then()
                .log().all()
                .statusCode(expectResponseCode);
    }
}
