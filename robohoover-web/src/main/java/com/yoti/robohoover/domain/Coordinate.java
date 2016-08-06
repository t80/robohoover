package com.yoti.robohoover.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Embeddable;

@Embeddable
public final class Coordinate {
    public static Coordinate of(int x, int y) {
        return new Coordinate(x, y);
    }

    private int x;
    private int y;

    public Coordinate() {
    }

    private Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinate that = (Coordinate) o;

        if (x != that.x) return false;
        return y == that.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("x", x)
                .append("y", y)
                .build();
    }
}
