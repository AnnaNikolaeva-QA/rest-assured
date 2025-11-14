package pets;

import core.BaseApi;
import dto.PetRequestAddNewPet;

import java.util.*;

public final class PetFactoryForAddNewPet {
    private PetFactoryForAddNewPet() {
    }

    public static PetRequestAddNewPet pendingCat(String name) {
        return PetRequestAddNewPet.builder()
                .id(0)
                .category(PetRequestAddNewPet.Category.builder()
                        .id(2)
                        .name(PetCategory.CATS.getValue())
                        .build())
                .name(name)
                .photoUrls(Collections.singletonList(BaseApi.BASE_URL + "/" + name + ".jpg"))
                .tags(List.of(PetRequestAddNewPet.Tags.builder()
                        .id(236)
                        .name("Koska").build()))
                .status(PetStatus.PENDING.getValue())
                .build();
    }

    public static PetRequestAddNewPet availableDog(String name) {
        return PetRequestAddNewPet.builder()
                .id(0)
                .category(PetRequestAddNewPet.Category.builder()
                        .id(2)
                        .name(PetCategory.DOGS.getValue())
                        .build())
                .name(name)
                .photoUrls(Collections.singletonList(BaseApi.BASE_URL + "/" + name + ".jpg"))
                .tags(List.of(PetRequestAddNewPet.Tags.builder()
                        .id(22)
                        .name("doggy")
                        .build()))
                .status(PetStatus.AVAILABLE.getValue())
                .build();
    }
}
