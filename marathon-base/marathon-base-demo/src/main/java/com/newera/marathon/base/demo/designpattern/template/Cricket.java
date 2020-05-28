package com.newera.marathon.base.demo.designpattern.template;

public class Cricket extends Game {
    @Override
    void initialize() {
        System.out.println("Cricket Game Initialized!");
    }

    @Override
    void startPlay() {
        System.out.println("Cricket Game Started!");
    }

    @Override
    void endPlay() {
        System.out.println("Cricket Game Finished!");
    }
}
