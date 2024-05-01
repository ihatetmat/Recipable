package capstone.recipable.domain.recipe.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String recipeImg;

    private String recipeName;

    private String introduce;

    @ElementCollection
    private List<String> ingredients;

    private String recipeDetails;

    @ElementCollection
    private List<String> videoUrls;

    public static Recipe of(Long id, String recipeImg, String recipeName, String introduce,
                             List<String> ingredients, String recipeDetails,
                             List<String> videoUrls) {
        return Recipe.builder()
                .id(id)
                .recipeImg(recipeImg)
                .recipeName(recipeName)
                .introduce(introduce)
                .ingredients(ingredients)
                .recipeDetails(recipeDetails)
                .videoUrls(videoUrls)
                .build();
    }
}
