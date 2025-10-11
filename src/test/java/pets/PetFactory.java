package pets;

import core.BaseApi;
import dto.PetRequest;

import java.util.*;

public final class PetFactory {
    private PetFactory() {
    }

    public static PetRequest pendingCat(String name) {
        return PetRequest.builder()
                .id(0)
                .category(PetRequest.Category.builder()
                        .id(2)
                        .name("cats").build())
                .name(name)
                .photoUrls(Collections.singletonList(BaseApi.BASE_URL + "/" + name + ".jpg"))
                .tags(List.of(PetRequest.Tags.builder()
                        .id(236)
                        .name("Koska").build()))
                .status("pending")
                .build();
    }
}
