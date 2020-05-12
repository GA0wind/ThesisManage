package com.ncu.graduation.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO<T> {

    private List<T> data;
    private Integer page;
    private Integer totalPage = 0;

    public void setPagination(Integer totalPage, Integer page, Integer size) {

        this.totalPage = totalPage;
        this.page = page;
    }
}
