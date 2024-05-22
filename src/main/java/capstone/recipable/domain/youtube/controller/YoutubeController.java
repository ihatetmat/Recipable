package capstone.recipable.domain.youtube.controller;

import capstone.recipable.domain.youtube.dto.response.YoutubeResponse;
import capstone.recipable.domain.youtube.service.YoutubeService;
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
public class YoutubeController {

    private final YoutubeService youtubeService;

    @GetMapping
    public ResponseEntity<List<YoutubeResponse>> searchVideo(@RequestParam String keyword) throws IOException {
        List<YoutubeResponse> results = youtubeService.searchVideo(keyword);
        return ResponseEntity.ok(results);

    }
}
