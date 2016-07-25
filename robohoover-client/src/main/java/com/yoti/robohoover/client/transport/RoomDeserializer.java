package com.yoti.robohoover.client.transport;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.yoti.robohoover.client.RoomSize;

import java.io.IOException;

public class RoomDeserializer extends JsonDeserializer<RoomSize> {
    @Override
    public RoomSize deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        JsonNode width = node.get(0);
        JsonNode height = node.get(1);
        if(null==width || null==height) {
            throw new IllegalArgumentException("Bad dimensions provided for room size");
        }

        return new RoomSize(width.asInt(), height.asInt());
    }
}
