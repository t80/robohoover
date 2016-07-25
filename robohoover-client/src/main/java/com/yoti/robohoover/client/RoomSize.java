package com.yoti.robohoover.client;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yoti.robohoover.client.transport.RoomDeserializer;
import com.yoti.robohoover.client.transport.RoomSerializer;

@JsonSerialize(using = RoomSerializer.class)
@JsonDeserialize(using = RoomDeserializer.class)
public class RoomSize {

    private final int width;
    private final int height;

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
