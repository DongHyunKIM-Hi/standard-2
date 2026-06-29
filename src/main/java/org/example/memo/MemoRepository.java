package org.example.memo;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class MemoRepository {

    private final List<MemoRequestDto> memos = new ArrayList<>();

    public int save(MemoRequestDto dto) {
        memos.add(dto);
        return memos.size() - 1;
    }

    public MemoRequestDto findById(int id) {
        if (id < 0 || id >= memos.size()) {
            throw new RuntimeException("해당 메모가 없어요");
        }
        return memos.get(id);
    }

    public List<MemoRequestDto> findAll() {
        return memos;
    }

    public boolean existsByTitle(String title) {
        return memos.stream().anyMatch(m -> m.getTitle().equals(title));
    }

    public void update(int id, MemoRequestDto dto) {
        memos.set(id, dto);
    }

    public void delete(int id) {
        memos.remove(id);
    }
}