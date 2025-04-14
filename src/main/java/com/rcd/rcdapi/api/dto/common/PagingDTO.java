package com.rcd.rcdapi.api.dto.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PagingDTO<T> {

    private int currentPage;
    private int totalPage;
    private int pageSize;
    private long totalCount;
    private boolean isFirstPage;
    private boolean isLastPage;
    private List<T> contents;
}
