package com.yoti.robohoover.client.transport;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.yoti.robohoover.client.Coordinate;

import java.io.IOException;
import java.util.List;

public class CoordinateListSerializer extends JsonSerializer<List<Coordinate>> {
    @Override
    public void serialize(List<Coordinate> coordinates, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartArray();
        for(Coordinate c: coordinates) {
            jsonGenerator.writeStartArray(2);
            jsonGenerator.writeNumber(c.getX());
            jsonGenerator.writeNumber(c.getY());
            jsonGenerator.writeEndArray();
        }
        jsonGenerator.writeEndArray();
    }
}
