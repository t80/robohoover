package com.yoti.robohoover.domain;

public enum Direction {
    NORTH('N'), EAST('E'), SOUTH('S'), WEST('W');

    private char direction;

    Direction(char direction) {
        this.direction = direction;
    }

    public static Direction from(char c) {
        for(Direction d: values()) {
            if(c == d.direction ) {
                return d;
            }
        }
        throw new IllegalArgumentException("bad direction");
    }
}
