package com.rcd.rcdapi.domain.challengecard;

import com.rcd.rcdapi.api.dto.challengecard.ChallengeCardDetailDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ChallengeCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String createDtm;


    // 연관관계 편의 메서드

    // DTO 변환 메서드
    public ChallengeCardDetailDTO toDetailDto() {
        return ChallengeCardDetailDTO.builder()
                .id(this.id)
                .title(this.title)
                .description(this.description)
                .createDtm(this.createDtm)
                .build();
    }
}
