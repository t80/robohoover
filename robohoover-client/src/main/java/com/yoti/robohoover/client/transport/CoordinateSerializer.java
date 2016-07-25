package com.yoti.robohoover.client.transport;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.yoti.robohoover.client.Coordinate;

import java.io.IOException;

public class CoordinateSerializer extends JsonSerializer<Coordinate> {
    @Override
    public void serialize(Coordinate coordinate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartArray(2);
        jsonGenerator.writeNumber(coordinate.getX());
        jsonGenerator.writeNumber(coordinate.getY());
        jsonGenerator.writeEndArray();
    }
}
