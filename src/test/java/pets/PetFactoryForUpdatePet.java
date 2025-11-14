package pets;

import core.BaseApi;
import dto.PetRequestUpdatePet;

import java.util.Collections;
import java.util.List;

public final class PetFactoryForUpdatePet {
    private PetFactoryForUpdatePet() {
    }

    public static PetRequestUpdatePet updatePet(Integer idForUpdate, String newName) {
        return PetRequestUpdatePet.builder()
                .id(idForUpdate)
                .category(PetRequestUpdatePet.Category.builder()
                        .id(idForUpdate)
                        .name(PetCategory.CATS.getValue())
                        .build())
                .name(newName)
                .photoUrls(Collections.singletonList(BaseApi.BASE_URL + ".jpg"))
                .tags(List.of(PetRequestUpdatePet.Tags.builder()
                        .id(idForUpdate)
                        .name(newName)
                        .build()))
                .status(PetStatus.SOLD.getValue())
                .build();
    }
}
