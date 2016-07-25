package com.yoti.robohoover.client.transport;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.yoti.robohoover.client.Coordinate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CoordinateListDeserializer extends JsonDeserializer<List<Coordinate>> {
    @Override
    public List<Coordinate> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        Iterator<JsonNode> iterator = node.iterator();
        List<Coordinate> coordinateList = new ArrayList<>();
        while(iterator.hasNext()) {
            JsonNode jsonNode = iterator.next();
            coordinateList.add(new Coordinate(jsonNode.get(0).asInt(), jsonNode.get(1).asInt()));
        }

        return coordinateList;
    }
}
