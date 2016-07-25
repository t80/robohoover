package com.yoti.robohoover.client;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

public class CoordinateTest {

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(Coordinate.class).verify();
    }

}