package com.yoti.robohoover.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RoomSize {

    public static RoomSize of(int width, int height) {
        return new RoomSize(width, height);
    }

    @Column(name = "room_width")
    private int width;
    @Column(name = "room_height")
    private int height;

    public RoomSize() {
    }

    public RoomSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
