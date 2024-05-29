package capstone.recipable.domain.bookmark.entity;

import capstone.recipable.domain.recipe.entity.Recipe;
import capstone.recipable.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    public static Bookmark of(User user, Recipe recipe) {
        return Bookmark.builder()
                .user(user)
                .recipe(recipe)
                .build();
    }
}
