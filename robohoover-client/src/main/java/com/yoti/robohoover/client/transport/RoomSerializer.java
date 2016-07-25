package com.yoti.robohoover.client.transport;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.yoti.robohoover.client.RoomSize;

import java.io.IOException;

public class RoomSerializer extends JsonSerializer<RoomSize> {
    @Override
    public void serialize(RoomSize roomSize, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartArray(2);
        jsonGenerator.writeNumber(roomSize.getWidth());
        jsonGenerator.writeNumber(roomSize.getHeight());
        jsonGenerator.writeEndArray();
    }
}
