package com.rcd.rcdapi.domain.challengelog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChallengeLogRepository extends JpaRepository<ChallengeLog, Long> {

    long countByStatusAndMember_Id(ChallengeLogStatus status, Long memberId);

    List<ChallengeLog> findAllByStatusAndMember_Id(ChallengeLogStatus status, Long memberId);

    Page<ChallengeLog> findAllByStatusAndMember_Id(ChallengeLogStatus status, Long memberId, Pageable pageable);

}
