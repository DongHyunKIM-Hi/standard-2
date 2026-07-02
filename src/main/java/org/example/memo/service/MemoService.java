package org.example.memo.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.memo.model.entity.Memo;
import org.example.memo.model.request.MemoRequestDto;
import org.example.memo.model.response.MemoResponseDto;
import org.example.memo.repository.MemoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    @PersistenceContext
    private EntityManager em;

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

        return new MemoResponseDto(memo.getId(), memo.getTitle(), memo.getContent());
    }

    public void deleteMemo(Long id) {
        memoRepository.deleteById(id);
    }

    @Transactional
    public void testFirstLevelCache() {

        System.out.println("=== 첫 번째 조회 ===");
        Memo memo1 = memoRepository.findById(1L).orElseThrow();

        System.out.println("=== 두 번째 조회 ===");
        Memo memo2 = memoRepository.findById(1L).orElseThrow();

        System.out.println("같은 객체? " + (memo1 == memo2));

    }

    // 케이스 1: @Transactional 없음
    public void updateWithoutTx() {
        Memo memo = memoRepository.findById(1L).orElseThrow();
        memo.updateContent("변경됨 (트랜잭션 없음)");
        System.out.println("실행 완료");
    }

    // 케이스 2: @Transactional 있음
    @Transactional
    public void updateWithTx() {
        Memo memo = memoRepository.findById(1L).orElseThrow();
        memo.updateContent("변경됨 (트랜잭션 있음)");
        System.out.println("실행 완료");
    }

    // 케이스 2: @Transactional 있음
    @Transactional
    public void updateWithTxAndUpdate() {
        Memo memo = memoRepository.findById(1L).orElseThrow();
        memo.updateContent("변경됨 (트랜잭션 있음)");
        System.out.println("실행 완료");
        memoRepository.save(memo);
    }


    @Transactional
    public void testWriteBehind() {

        System.out.println("=== 1번째 저장 ===");
        memoRepository.save(new Memo("메모1", "내용1"));
        System.out.println("numberOfInsertions: " +
            ((org.hibernate.internal.SessionImpl) em.getDelegate()).getActionQueue().numberOfInsertions());

        System.out.println("=== 2번째 저장 ===");
        memoRepository.save(new Memo("메모2", "내용2"));
        System.out.println("numberOfInsertions: " +
            ((org.hibernate.internal.SessionImpl) em.getDelegate()).getActionQueue().numberOfInsertions());




        System.out.println("=== 3번째 저장 ===");
        memoRepository.save(new Memo("메모3", "내용3"));
        System.out.println("numberOfInsertions: " +
            ((org.hibernate.internal.SessionImpl) em.getDelegate()).getActionQueue().numberOfInsertions());

        System.out.println("=== 메서드 끝, 곧 커밋됩니다 ===");
    }
}
