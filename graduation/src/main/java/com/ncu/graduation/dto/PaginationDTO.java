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
        if (page>totalPage){
            this.page = totalPage;
        }else if (page <= 0){
            this.page = 1;
        } else{
            this.page = page;
        }
        this.totalPage = totalPage;
    }
}
