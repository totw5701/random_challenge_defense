package com.rcd.rcdapi.domain.challengelog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChallengeLogRepository extends JpaRepository<ChallengeLog, Long> {

    long countByStatusAndMember_Id(String status, Long memberId);

    List<ChallengeLog> findAllByStatusAndMember_Id(String status, Long memberId);

    Page<ChallengeLog> findAllByMember_Id(Long memberId, Pageable pageable);
}
