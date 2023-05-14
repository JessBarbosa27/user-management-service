package com.jesslabs.usermanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author barbo on 22-03-2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageDTO {
    private int totalPages;
    private int currentPageNo;
    private int currentPageSize;
    private Long total;
}
