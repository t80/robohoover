package com.yoti.robohoover.client.transport;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.yoti.robohoover.client.Coordinate;
import com.yoti.robohoover.client.RoomSize;

import java.io.IOException;

public class CoordinateDeserializer extends JsonDeserializer<Coordinate> {
    @Override
    public Coordinate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        JsonNode x = node.get(0);
        JsonNode y = node.get(1);
        if(null==x || null==y) {
            throw new IllegalArgumentException("Bad coordinates provided in field");
        }
        return new Coordinate(x.asInt(), y.asInt());
    }
}
