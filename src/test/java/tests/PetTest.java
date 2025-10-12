package tests;

import org.testng.annotations.Test;
import pets.PetFactory;

import static org.hamcrest.core.IsEqual.equalTo;

public class PetTest extends BaseApiTest {
    @Test
    public void canGetPetByValidId() {
        pet.getById(1, 200);
    }

    @Test
    public void canGetPetByInvalidId() {
        pet.getById("two", 400)
                .then()
                .body("type", equalTo("unknown"));
    }

    @Test
    void canCreateNewPet() {
        pet.addNewPet(PetFactory.pendingCat("White"), 200)
                .body("status", equalTo("pending"))
                .body("category.name", equalTo("cats"));
    }
}
