package org.example.memo;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MemoService {

    private final MemoRepository memoRepository;

    public MemoService(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    public MemoResponseDto createMemo(MemoRequestDto dto) {
        validate(dto);
        if (memoRepository.existsByTitle(dto.getTitle())) {
            throw new RuntimeException("이미 같은 제목의 메모가 있어요");
        }
        int id = memoRepository.save(dto);
        return new MemoResponseDto(id, dto.getTitle(), dto.getContent());
    }

    public List<MemoResponseDto> getMemos() {
        List<MemoRequestDto> memos = memoRepository.findAll();
        List<MemoResponseDto> result = new ArrayList<>();
        for (int i = 0; i < memos.size(); i++) {
            result.add(new MemoResponseDto(i, memos.get(i).getTitle(), memos.get(i).getContent()));
        }
        return result;
    }

    public MemoResponseDto getMemo(int id) {
        MemoRequestDto memo = memoRepository.findById(id);
        return new MemoResponseDto(id, memo.getTitle(), memo.getContent());
    }

    public MemoResponseDto updateMemo(int id, MemoRequestDto dto) {
        memoRepository.findById(id);  // 존재 확인
        validate(dto);
        memoRepository.update(id, dto);
        return new MemoResponseDto(id, dto.getTitle(), dto.getContent());
    }

    public void deleteMemo(int id) {
        memoRepository.findById(id);  // 존재 확인
        memoRepository.delete(id);
    }

    // 유효성 검사는 여기 한 곳에서만 관리
    private void validate(MemoRequestDto dto) {
        if (dto.getTitle() == null || dto.getTitle().isBlank()) {
            throw new RuntimeException("제목은 비워둘 수 없어요");
        }
        if (dto.getTitle().length() > 20) {
            throw new RuntimeException("제목은 20자를 넘을 수 없어요");
        }
        if (dto.getContent() == null || dto.getContent().isBlank()) {
            throw new RuntimeException("내용은 비워둘 수 없어요");
        }
    }
}
