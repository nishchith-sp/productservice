package com.nishchith.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchRequestDTO {

    private String keyword;
    private int pageNumber;
    private int pageSize;
}
