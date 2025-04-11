package com.rcd.rcdapi.domain.challengecard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChallengeCardRepository extends JpaRepository<ChallengeCard, Long> {

    @Query("""
        SELECT cct.challengeCard.id
        FROM ChallengeCardTag cct 
        WHERE cct.tag.id IN :tagIds 
        GROUP BY cct.challengeCard.id
        HAVING COUNT(DISTINCT cct.tag.id) = :tagSize
    """)
    List<Long> findCardIdsHavingAllTags(@Param("tagIds") List<Long> tagIds, @Param("tagSize") long tagSize);

}
