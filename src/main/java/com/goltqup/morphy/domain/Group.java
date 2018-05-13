package com.goltqup.morphy.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Set;

import static java.util.Collections.unmodifiableSet;
import static org.springframework.util.Assert.hasText;
import static org.springframework.util.Assert.notEmpty;

@Getter
public class Group {

    private final String id;
    private final String name;
    @JsonProperty("teams")
    private final Set<Team> teamSet;

    @JsonCreator
    public Group(@JsonProperty("id") final String id, @JsonProperty("name") final String name, @JsonProperty("teams") final Set<Team> teamSet) {
        hasText(id, "Group id must have text.");
        hasText(name, "Group name must have text.");
        notEmpty(teamSet, "Group team set must not be empty.");
        this.id = id;
        this.name = name;
        this.teamSet = unmodifiableSet(teamSet);
    }

    public boolean equals(final Object object) {
        if (object == this) return true;
        if (!(object instanceof Group)) return false;
        final Group other = (Group) object;
        return id.equals(other.id) && name.equals(other.name);
    }

    public int hashCode() {
        return id.hashCode();
    }

    public String toString() {
        return "Group(id=" + this.getId() + ", name=" + this.getName() + ", teams=" + this.getTeamSet().toString() + ")";
    }
}
