package com.newera.marathon.web.zy.model;

import lombok.Data;

@Data
public class WebPage {

    private Long page;

    private Long limit;

    private String field;

    private String type;
}
