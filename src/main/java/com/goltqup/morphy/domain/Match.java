package com.goltqup.morphy.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static java.time.LocalDateTime.ofInstant;
import static org.springframework.util.Assert.hasText;
import static org.springframework.util.Assert.notNull;

@JsonDeserialize(builder = Match.MatchBuilder.class)
@Getter
public class Match {
    private final String id;
    private final String stadium;
    private final LocalDateTime schedule;
    private final String localTeam;
    private final int localGoals;
    private final String visitorTeam;
    private final int visitorGoals;

    private Match(final MatchBuilder matchBuilder) {
        this.id = matchBuilder.id;
        this.stadium = matchBuilder.stadium;
        this.schedule = matchBuilder.schedule;
        this.localTeam = matchBuilder.localTeam;
        this.localGoals = matchBuilder.localGoals;
        this.visitorTeam = matchBuilder.visitorTeam;
        this.visitorGoals = matchBuilder.visitorGoals;
    }

    public boolean equals(Object object) {
        if (object == this) return true;
        if (!(object instanceof Match)) return false;
        final Match other = (Match) object;
        return this.id.equals(other.id);
    }

    public int hashCode() {
        return id.hashCode();
    }

    public String toString() {
        return "Match(id=" + this.getId() + ", stadium=" + this.getStadium() + ", schedule=" + this.getSchedule() + ", localTeam=" + this.getLocalTeam() + ", localGoals=" + this.getLocalGoals() + ", visitorTeam=" + this.getVisitorTeam() + ", visitorGoals=" + this.getVisitorGoals() + ")";
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static final class MatchBuilder {

        private final String id;
        private final String stadium;
        private final LocalDateTime schedule;
        private final String localTeam;
        private final String visitorTeam;
        private int localGoals;
        private int visitorGoals;

        public MatchBuilder(@JsonProperty("id") final String id,
                            @JsonProperty("stadium") final String stadium,
                            @JsonProperty("schedule") final Date schedule,
                            @JsonProperty("localTeam") final String localTeam,
                            @JsonProperty("visitorTeam") final String visitorTeam) {
            hasText(stadium, "Stadium must have text.");
            notNull(schedule, "Schedule must not be null.");
            hasText(stadium, "Local team must have text.");
            hasText(stadium, "Visitor team must have text.");

            this.id = id;
            this.stadium = stadium;
            this.schedule = ofInstant(schedule.toInstant(), ZoneId.of("GMT"));
            this.localTeam = localTeam;
            this.visitorTeam = visitorTeam;
        }

        public MatchBuilder localGoals(final int localGoals) {
            this.localGoals = localGoals;
            return this;
        }

        public MatchBuilder visitorGoals(final int visitorGoals) {
            this.visitorGoals = visitorGoals;
            return this;
        }

        public Match build() {
            return new Match(this);
        }
    }
}
