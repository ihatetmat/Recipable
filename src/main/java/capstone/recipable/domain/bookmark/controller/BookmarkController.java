package capstone.recipable.domain.bookmark.controller;

import capstone.recipable.domain.bookmark.service.BookmarkService;
import capstone.recipable.domain.recipe.dto.response.RecipeSummaryResponse;
import capstone.recipable.global.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/bookmark")
@Tag(name = "bookmark", description = "북마크 관련 api")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @Operation(summary = "summary", description = """
            북마크 생성 api입니다.
            
            레시피 id로 북마크를 생성합니다.
            
            """)
    @PostMapping("/{recipeId}")
    public ResponseEntity<SuccessResponse<String>> createBookmark(@PathVariable long recipeId) {
        String response = bookmarkService.createBookmark(recipeId);
        return SuccessResponse.of(response);
    }

    @Operation(summary = "북마크 목록 조회", description = """
            북마크 목록 조회 api입니다.
            
            사용자의 북마크 목록을 조회해줍니다.
            """)
    @GetMapping
    public ResponseEntity<SuccessResponse<List<RecipeSummaryResponse>>> getBookmarkList() {
        List<RecipeSummaryResponse> response = bookmarkService.getBookmarkList();
        return SuccessResponse.of(response);
    }

    @Operation(summary = "북마크 삭제", description = """
            북마크 삭제 api입니다.
            
            """)
    @DeleteMapping("/{recipeId}")
    public ResponseEntity<SuccessResponse<String>> deleteBookmark(@PathVariable long recipeId) {
        String response = bookmarkService.deleteBookmark(recipeId);
        return SuccessResponse.of(response);
    }
}
