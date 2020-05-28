package com.newera.marathon.base.demo.designpattern.builder;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LombokComputer {

    private String cpu;

    private String screen;

    private String disk;
}
