package com.game.gb5.repository;

import com.game.gb5.model.Matching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MatchingRepository extends JpaRepository<Matching, Long> {
    Optional<Matching> findByCode(String code);
}
