package com.yoti.robohoover.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "hoover_response")
public class RobohooverServiceResponse {

    @Id
    @GeneratedValue
    private Long id;
    @Embedded
    private Coordinate coordinate;
    @Column(name = "cleaned_patch_count")
    private int cleanPatches;

    public RobohooverServiceResponse() {
    }

    public RobohooverServiceResponse(Coordinate coordinate, int cleanPatches) {
        this.coordinate = coordinate;
        this.cleanPatches = cleanPatches;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public int cleanedPatchesCount() {
        return cleanPatches;
    }
}
