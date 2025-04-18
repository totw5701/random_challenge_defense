package com.rcd.rcdapi.domain.tag;

import com.rcd.rcdapi.api.dto.tag.TagDetailDTO;
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
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public TagDetailDTO toDetailDto() {
        return TagDetailDTO.builder()
                .id(this.id)
                .name(this.name)
                .build();
    }
}
