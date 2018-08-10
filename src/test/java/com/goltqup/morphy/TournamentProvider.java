package com.goltqup.morphy;

import com.goltqup.morphy.domain.Tournament;

import java.io.IOException;

import static com.goltqup.morphy.TestUtils.getObjectFromJson;

public class TournamentProvider {
    private TournamentProvider(){
        throw new UnsupportedOperationException();
    }

    public static Tournament getExpectedTournamentFromJson() throws IOException {
        return getObjectFromJson("/expected/tournament.json", Tournament.class);
    }

}
