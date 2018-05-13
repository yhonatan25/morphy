package com.goltqup.morphy.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Getter;

import static org.springframework.util.Assert.hasText;

@JsonDeserialize(builder = Team.TeamBuilder.class)
@Getter
public class Team {

    private final String id;

    private final String name;

    private final int playedMatches;

    private final int wonMatches;

    private final int drawMatches;

    private final int defeatedMatches;

    private final int goalsFor;

    private final int goalsAgainst;

    private final int goalDifference;

    private final int points;

    private Team(final TeamBuilder teamBuilder) {
        this.id = teamBuilder.id;
        this.name = teamBuilder.name;
        this.playedMatches = teamBuilder.playedMatches;
        this.wonMatches = teamBuilder.wonMatches;
        this.drawMatches = teamBuilder.drawMatches;
        this.defeatedMatches = teamBuilder.defeatedMatches;
        this.goalsFor = teamBuilder.goalsFor;
        this.goalsAgainst = teamBuilder.goalsAgainst;
        this.goalDifference = teamBuilder.goalDifference;
        this.points = teamBuilder.points;
    }

    public boolean equals(final Object object) {
        if (object == this) return true;
        if (!(object instanceof Team)) return false;
        final Team other = (Team) object;
        return id.equals(other.id) && name.equals(other.name);
    }

    public int hashCode() {
        return id.hashCode();
    }

    public String toString() {
        return "Team(id=" + this.id + ", name=" + this.name + ", playedMatches=" + this.playedMatches + ", wonMatches=" + this.wonMatches + ", drawMatches=" + this.drawMatches + ", defeatedMatches=" + this.defeatedMatches + ", goalsFor=" + this.goalsFor + ", goalsAgainst=" + this.goalsAgainst + ", goalDifference=" + this.goalDifference + ", points=" + this.points + ")";
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class TeamBuilder {

        private final String id;
        private final String name;
        private int playedMatches;
        private int wonMatches;
        private int drawMatches;
        private int defeatedMatches;
        private int goalsFor;
        private int goalsAgainst;
        private int goalDifference;
        private int points;

        public TeamBuilder(@JsonProperty("id") final String id, @JsonProperty("name") final String name) {
            hasText(id, "Group id must have text.");
            hasText(name, "Group name must have text.");
            this.id = id;
            this.name = name;
        }

        public TeamBuilder playedMatches(int playedMatches) {
            this.playedMatches = playedMatches;
            return this;
        }

        public TeamBuilder wonMatches(int wonMatches) {
            this.wonMatches = wonMatches;
            return this;
        }

        public TeamBuilder drawMatches(int drawMatches) {
            this.drawMatches = drawMatches;
            return this;
        }

        public TeamBuilder defeatedMatches(int defeatedMatches) {
            this.defeatedMatches = defeatedMatches;
            return this;
        }

        public TeamBuilder goalsFor(int goalsFor) {
            this.goalsFor = goalsFor;
            return this;
        }

        public TeamBuilder goalsAgainst(int goalsAgainst) {
            this.goalsAgainst = goalsAgainst;
            return this;
        }

        public TeamBuilder goalDifference(int goalDifference) {
            this.goalDifference = goalDifference;
            return this;
        }

        public TeamBuilder points(int points) {
            this.points = points;
            return this;
        }

        public Team build() {
            return new Team(this);
        }
    }
}