package com.rcd.rcdapi.api.dto.common;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PagingDTO<T> {

    private int currentPage;
    private int totalPage;
    private int pageSize;
    private long totalCount;
    private boolean isFirstPage;
    private boolean isLastPage;
    private List<T> contents;
}
