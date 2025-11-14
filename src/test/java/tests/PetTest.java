package tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pets.*;

import java.util.stream.Stream;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PetTest extends BaseApiTest {
    private String statusPetForTestFindByStatus = PetStatus.SOLD.getValue();
    @Test
    public void canGetPetByValidId() {
        // TestNG
//        SoftAssert softAssert = new SoftAssert();
//        Response response = pet.getById(1, 200);
//        softAssert.assertNotNull(response.jsonPath().getString("name"));
//        softAssert.assertNotNull(response.jsonPath().getString("status"));
//        softAssert.assertAll();

        // JUnit5
        Response response = pet.getById(1, 200);
        String name = response.jsonPath().getString("name");
        String status = response.jsonPath().getString("status");

        assertAll(
                () -> assertNotNull(name),
                () -> assertNotNull(status)
        );
    }

    @Test
    public void canGetPetByInvalidId() {
        pet.getById("two", 400)
                .then()
                .body("type", equalTo("unknown"));
    }

    static Stream<Arguments> newPetCases() {
        return Stream.of(
                Arguments.of(
                        PetFactoryForAddNewPet.pendingCat("Blacky"),
                        200,
                        PetStatus.PENDING.getValue(),
                        PetCategory.CATS.getValue()
                ),
                Arguments.of(
                        PetFactoryForAddNewPet.availableDog("Voltik"),
                        200,
                        PetStatus.AVAILABLE.getValue(),
                        PetCategory.DOGS.getValue()
                )
        );
    }

    @ParameterizedTest
    @MethodSource("newPetCases")
    void canCreateNewPet(Object body, int statusCode, String status, String categoryName) {
        pet.addNewPet(body, statusCode)
                .body("status", equalTo(status))
                .body("category.name", equalTo(categoryName))
                .body(matchesJsonSchemaInClasspath("shemas/AddNewPetsResponse.json"));
    }

    @Test
    void findByStatus() {
        pet.findByStatus(statusPetForTestFindByStatus, 200)
                .body("status", everyItem(equalTo(statusPetForTestFindByStatus)));
    }

    @Test
    void uploadImage() {
        pet.uploadsImage(PetFactoryForUploadImage.requestForUploadImage(), 200)
                .body(matchesJsonSchemaInClasspath("shemas/UploadImageResponse.json"));
    }

    @Test
    void updatePet() {
        pet.updatePet(PetFactoryForUpdatePet.updatePet(123, "Arkasha"), 200)
                .body(matchesJsonSchemaInClasspath("shemas/UpdatePetResponse.json"));
    }
}
