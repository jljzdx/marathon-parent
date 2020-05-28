package com.newera.marathon.base.demo.designpattern.builder;

public class Computer {

    private String cpu;

    private String screen;

    private String disk;

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getDisk() {
        return disk;
    }

    public void setDisk(String disk) {
        this.disk = disk;
    }

    Computer(String cpu, String screen, String disk) {
        this.cpu = cpu;
        this.screen = screen;
        this.disk = disk;
    }

    public static ComputerBuilder builder() {
        return new ComputerBuilder();
    }

    public String toString() {
        return "Computer(cpu=" + getCpu() + ", screen=" + getScreen() + ", disk=" + getDisk() + ")";
    }

    public static class ComputerBuilder {
        private String cpu;

        public ComputerBuilder cpu(String cpu) {
            this.cpu = cpu;
            return this;
        }

        private String screen;

        public ComputerBuilder screen(String screen) {
            this.screen = screen;
            return this;
        }

        private String disk;

        public ComputerBuilder disk(String disk) {
            this.disk = disk;
            return this;
        }

        public Computer build() {
            return new Computer(this.cpu, this.screen, this.disk);
        }

        public String toString() {
            return "Computer.ComputerBuilder(cpu=" + this.cpu + ", screen=" + this.screen + ", disk=" + this.disk + ")";
        }
    }
}
