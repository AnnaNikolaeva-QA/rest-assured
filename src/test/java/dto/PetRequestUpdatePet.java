package dto;

import lombok.*;

import java.util.List;

@Value
@Builder
public class PetRequestUpdatePet {
    Integer id;
    Category category;
    String name;
    List<String> photoUrls;
    List<Tags> tags;
    String status;

    @Value
    @Builder
    public static class Category {
        Integer id;
        String name;
    }

    @Value
    @Builder
    public static class Tags {
        Integer id;
        String name;
    }
}
