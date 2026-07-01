package org.example.memo.repository;

import org.example.memo.model.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface MemoRepository2 extends JpaRepository<Memo, Long> {

    Memo findById(long id);

    Memo findByContent(String content);


}
