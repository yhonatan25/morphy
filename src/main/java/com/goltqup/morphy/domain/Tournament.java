package com.goltqup.morphy.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Set;
import java.util.TreeSet;

import static java.util.Collections.unmodifiableSet;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toCollection;
import static org.springframework.util.Assert.hasText;
import static org.springframework.util.Assert.notEmpty;
import static org.springframework.util.Assert.state;

@Getter
public class Tournament {

    private final String id;
    private final String name;
    private final String place;
    private final int year;

    @JsonProperty("groups")
    private final Set<Group> groupSet;

    @JsonCreator
    public Tournament(@JsonProperty("id") final String id,
                      @JsonProperty("name") final String name,
                      @JsonProperty("place") final String place,
                      @JsonProperty("year") final int year,
                      @JsonProperty("groups") final Set<Group> groupSet) {
        hasText(name, "Tournament name must have text.");
        hasText(place, "Tournament place must have text.");
        state(year >= 2018, "Tournament year must be greater than or equals to 2018.");
        notEmpty(groupSet, "Tournament group set must not be empty.");

        this.id = id;
        this.name = name;
        this.place = place;
        this.year = year;
        this.groupSet = unmodifiableSet(groupSet.stream()
                .collect(toCollection(() -> new TreeSet<>(comparing(Group::getName)))));
    }

    public boolean equals(final Object object) {
        if (object == this) return true;
        if (!(object instanceof Tournament)) return false;
        final Tournament other = (Tournament) object;
        return id.equals(other.id)
                && name.equals(other.name)
                && place.equals(other.place)
                && year == other.year;
    }

    public int hashCode() {
        return id.hashCode();
    }

    public String toString() {
        return "TournamentDocument(id=" + this.getId() + ", name=" + this.getName() + ", place=" + this.getPlace() + ", year=" + this.getYear() + ", groups=" + this.getGroupSet().toString() + ")";
    }
}
