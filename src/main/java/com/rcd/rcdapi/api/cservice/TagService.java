package com.rcd.rcdapi.api.cservice;

import com.rcd.rcdapi.api.dto.tag.TagDetailDTO;
import com.rcd.rcdapi.domain.tag.Tag;
import com.rcd.rcdapi.domain.tag.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TagService {

    private final TagRepository tagRepository;


    /**
     * 전체 태그를 조회한다.
     */
    public List<TagDetailDTO> getTags() {
        return tagRepository.findAll().stream().map(Tag::toDetailDto).collect(Collectors.toList());
    }
}
