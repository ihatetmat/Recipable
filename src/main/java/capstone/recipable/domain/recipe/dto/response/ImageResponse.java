package capstone.recipable.domain.recipe.dto.response;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class ImageResponse {

    private String imageUrl;

    public static ImageResponse of(String imageUrl) {
        return ImageResponse.builder()
                .imageUrl(imageUrl)
                .build();
    }
}
