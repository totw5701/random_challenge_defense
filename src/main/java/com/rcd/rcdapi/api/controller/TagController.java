package com.rcd.rcdapi.api.controller;

import com.rcd.rcdapi.api.cservice.TagService;
import com.rcd.rcdapi.api.dto.common.CommonResponse;
import com.rcd.rcdapi.api.dto.tag.TagDetailDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tag")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @GetMapping("/all")
    public CommonResponse<List<TagDetailDTO>> tags() {
        List<TagDetailDTO> tags = tagService.getTags();
        return CommonResponse.success(tags);
    }
}
