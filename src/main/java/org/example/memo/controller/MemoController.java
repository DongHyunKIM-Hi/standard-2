package org.example.memo.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.memo.model.request.MemoRequestDto;
import org.example.memo.model.response.MemoResponseDto;
import org.example.memo.service.MemoService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    @PostMapping("/memos")
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto dto) {
        dto.validate();
        return memoService.createMemo(dto);
    }

    @GetMapping("/memos")
    public List<MemoResponseDto> getMemos() {
        return memoService.getMemos();
    }

    @GetMapping("/memos/{id}")
    public MemoResponseDto getMemo(@PathVariable Long id) {
        return memoService.getMemo(id);
    }

    @PutMapping("/memos/{id}")
    public MemoResponseDto updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto dto) {
        return memoService.updateMemo(id, dto);
    }

    @DeleteMapping("/memos/{id}")
    public String deleteMemo(@PathVariable Long id) {
        memoService.deleteMemo(id);
        return "삭제됐어요";
    }
}
