package org.example.memo.repository;

import java.util.Optional;
import org.example.memo.model.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface MemoRepository extends JpaRepository<Memo, Long> {

    Memo findByContent(String content);

    Memo findByDinnerJMT(String dinnerJMT);


}
