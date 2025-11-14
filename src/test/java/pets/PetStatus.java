package pets;

import lombok.*;

@AllArgsConstructor
@Getter
public enum PetStatus {
    AVAILABLE("available"),
    PENDING("pending"),
    SOLD("sold");

    private final String value;
}
