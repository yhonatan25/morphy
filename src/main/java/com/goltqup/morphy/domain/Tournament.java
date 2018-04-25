package com.goltqup.morphy.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Tournament {

    private final String id;
    private final String name;
    private final String place;
    private final int year;

    @JsonCreator
    public Tournament(@JsonProperty("id") final String id,
                      @JsonProperty("name") final String name,
                      @JsonProperty("place") final String place,
                      @JsonProperty("year") final int year) {
        this.id = id;
        this.name = name;
        this.place = place;
        this.year = year;
    }


    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getPlace() {
        return this.place;
    }

    public int getYear() {
        return this.year;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Tournament)) return false;
        final Tournament other = (Tournament) o;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$place = this.getPlace();
        final Object other$place = other.getPlace();
        if (this$place == null ? other$place != null : !this$place.equals(other$place)) return false;
        return this.getYear() == other.getYear();
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $place = this.getPlace();
        result = result * PRIME + ($place == null ? 43 : $place.hashCode());
        result = result * PRIME + this.getYear();
        return result;
    }

    public String toString() {
        return "Tournament(id=" + this.getId() + ", name=" + this.getName() + ", place=" + this.getPlace() + ", year=" + this.getYear() + ")";
    }
}