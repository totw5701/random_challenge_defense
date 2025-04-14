package com.rcd.rcdapi.api.cservice;

import com.rcd.rcdapi.api.advice.ExceptionCode;
import com.rcd.rcdapi.api.advice.exception.CustomException;
import com.rcd.rcdapi.api.dto.challengecard.ChallengeCardDetailDTO;
import com.rcd.rcdapi.api.dto.tag.TagDetailDTO;
import com.rcd.rcdapi.domain.challengecard.ChallengeCardRepository;
import com.rcd.rcdapi.utils.RandomUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChallengeCardService {

    private final ChallengeCardRepository challengeCardRepository;

    /**
     * 랜덤한 챌린지를 받아온다.
     * @param tags
     * @return
     */
    public ChallengeCardDetailDTO getRandomChallengeCard(Long[] tags) throws Exception {

        List<Long> idList = challengeCardRepository.findCardIdsHavingAllTags(Arrays.asList(tags), tags.length);

        // idList가 비어있을 시
        if (idList.isEmpty()) {
            throw new CustomException(ExceptionCode.NOT_FOUND_CHALLENGE_CARD);
        }

        // 랜덤한 카드 ID 선택
        Long cardId = RandomUtil.pickRandomId(idList);

        // 챌린지 카드 반환
        return challengeCardRepository.findById(cardId)
                .map(c -> c.toDetailDto())
                .orElseThrow(() -> new CustomException(ExceptionCode.NOT_FOUND_CHALLENGE_CARD));
    }
}
