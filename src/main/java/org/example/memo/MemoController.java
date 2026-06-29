package org.example.memo;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemoController {

    private final MemoService memoService;

    public MemoController(MemoService memoService) {
        this.memoService = memoService;
    }

    @PostMapping("/memos")
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto dto) {
        return memoService.createMemo(dto);
    }

    @GetMapping("/memos")
    public List<MemoResponseDto> getMemos() {
        return memoService.getMemos();
    }

    @GetMapping("/memos/{id}")
    public MemoResponseDto getMemo(@PathVariable int id) {
        return memoService.getMemo(id);
    }

    @PutMapping("/memos/{id}")
    public MemoResponseDto updateMemo(@PathVariable int id, @RequestBody MemoRequestDto dto) {
        return memoService.updateMemo(id, dto);
    }

    @DeleteMapping("/memos/{id}")
    public String deleteMemo(@PathVariable int id) {
        memoService.deleteMemo(id);
        return "삭제됐어요";
    }
}