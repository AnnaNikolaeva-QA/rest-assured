package pets;

import lombok.*;

@AllArgsConstructor
@Getter
public enum PetCategory {
    CATS("cats"),
    DOGS("dogs"),
    OTHER("other");

    private final String value;
}
