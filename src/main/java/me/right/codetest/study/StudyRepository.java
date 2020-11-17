package me.right.codetest.study;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudyRepository {

    Optional<Study> findById(Long memberId);

    Study save(Study study);
}
