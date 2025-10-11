package tests;

import org.testng.annotations.Test;
import pets.PetFactory;

public class PetTest extends BaseApiTest {
    @Test
    public void canGetPetByValidId() {
        pet.getById(1, 200);
    }

    @Test
    public void canGetPetByInvalidId() {
        pet.getById("two", 400);
    }

    @Test
    void canCreateNewPet() {
        pet.addNewPet(PetFactory.pendingCat("White"), 200);
    }
}
