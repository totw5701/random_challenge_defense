package com.rcd.rcdapi.domain.challengecard;

import com.rcd.rcdapi.api.dto.challengecard.ChallengeCardDetailDTO;
import com.rcd.rcdapi.domain.challengecardtag.ChallengeCardTag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

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

    @OneToMany(mappedBy = "challengeCard", fetch = FetchType.LAZY)
    private List<ChallengeCardTag> tags;

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
