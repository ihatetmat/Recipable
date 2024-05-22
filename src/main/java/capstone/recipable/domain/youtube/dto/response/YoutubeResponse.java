package capstone.recipable.domain.youtube.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class YoutubeResponse {

    private String videoId;

    private String title;

    private String thumbnail;

    public static YoutubeResponse of(String videoId, String title, String thumbnail) {
        return YoutubeResponse.builder()
                .videoId(videoId)
                .title(title)
                .thumbnail(thumbnail)
                .build();
    }
}
