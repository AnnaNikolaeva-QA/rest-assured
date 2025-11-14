package dto;

import lombok.Builder;
import lombok.Value;

import java.io.File;

@Value
@Builder
public class PetRequestUploadImage {
    Long petId;
    String additionalMetadata;
    File file;
}
