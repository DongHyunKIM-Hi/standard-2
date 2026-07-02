package org.example.memo.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.memo.model.entity.Memo;
import org.example.memo.model.request.MemoRequestDto;
import org.example.memo.model.response.MemoResponseDto;
import org.example.memo.repository.MemoRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    public MemoResponseDto createMemo(MemoRequestDto dto) {

        Memo memo = new Memo(dto.getTitle(), dto.getContent());
        memoRepository.save(memo);

        return new MemoResponseDto(memo.getId(), memo.getTitle(), memo.getContent());
    }

    public List<MemoResponseDto> getMemos() {

        List<Memo> memos = memoRepository.findAll();

        return memos.stream().map(it -> new MemoResponseDto(it.getId(), it.getTitle(), it.getContent())).toList();
    }

    public MemoResponseDto getMemo(long id) {

        Memo memo = memoRepository.findById(id).orElseThrow();

        return new MemoResponseDto(id, memo.getTitle(), memo.getContent());
    }

    public MemoResponseDto updateMemo(long id, MemoRequestDto dto) {

        Memo memo = memoRepository.findById(id).orElseThrow();  // 존재 확인

        memo.update(dto);

        memoRepository.save(memo);

        return new MemoResponseDto(id, dto.getTitle(), dto.getContent());
    }

    public void deleteMemo(Long id) {
        memoRepository.deleteById(id);
    }

}
