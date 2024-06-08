package capstone.recipable.domain.recipe.entity;

import capstone.recipable.domain.user.entity.User;
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

    private String ingredients;

    private String recipeDetails;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private List<RecipeVideos> recipeVideos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public static Recipe of(String recipeImg, String recipeName, String introduce,
                             String ingredients, String recipeDetails,
                             List<RecipeVideos> recipeVideos, User user) {
        return Recipe.builder()
                .recipeImg(recipeImg)
                .recipeName(recipeName)
                .introduce(introduce)
                .ingredients(ingredients)
                .recipeDetails(recipeDetails)
                .recipeVideos(recipeVideos)
                .user(user)
                .build();
    }
}
