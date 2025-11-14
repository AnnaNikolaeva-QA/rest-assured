package pets;

import dto.PetRequestUploadImage;

import java.io.File;

public final class PetFactoryForUploadImage {
    private PetFactoryForUploadImage() {
    }

    public static PetRequestUploadImage requestForUploadImage() {
        return PetRequestUploadImage.builder()
                .petId(12L)
                .additionalMetadata("Pretty cat")
                .file(new File(("src/test/resources/Arkasha.jpg")))
                .build();
    }
}
