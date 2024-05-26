package capstone.recipable.domain.youtube.controller;

import capstone.recipable.domain.youtube.dto.response.YoutubeResponse;
import capstone.recipable.domain.youtube.service.YoutubeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/youtube")
@Tag(name = "youtube", description = "youtube 관련 api")
public class YoutubeController {

    private final YoutubeService youtubeService;

    @Operation(summary = "유튜브 api", description = """
            유튜브 영상 첨부 api입니다.
            
            param으로 keyword를 보내주시면 유튜브에서 검색하여 영상 3개를 반환해줍니다.
            
            """)
    @GetMapping
    public ResponseEntity<List<YoutubeResponse>> searchVideo(@RequestParam String keyword) throws IOException {
        List<YoutubeResponse> results = youtubeService.searchVideo(keyword);
        return ResponseEntity.ok(results);

    }
}
