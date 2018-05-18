package com.goltqup.morphy;

import com.goltqup.morphy.domain.Group;
import com.goltqup.morphy.domain.Match;
import com.goltqup.morphy.domain.Team;
import com.goltqup.morphy.domain.Tournament;

import java.util.Set;

public class TournamentAssert {

    public static boolean tournamentMatchesExpected(final Tournament actualTournament, final Tournament expectedTournament) {
        return expectedTournament.equals(actualTournament)
                && groupSetsAreEquals(actualTournament.getGroupSet(), expectedTournament.getGroupSet());
    }

    private static boolean groupSetsAreEquals(final Set<Group> actualGroupSet, final Set<Group> expectedGroupSet) {
        return groupSetMatchesExpected(actualGroupSet, expectedGroupSet)
                && groupSetsHaveTheSameSize(actualGroupSet, expectedGroupSet);
    }

    private static boolean groupSetMatchesExpected(final Set<Group> actualGroupSet, final Set<Group> expectedGroupSet) {
        return expectedGroupSet.stream()
                .allMatch(expectedGroup -> groupMatchesExpected(getActualGroup(actualGroupSet, expectedGroup),
                        expectedGroup));
    }

    private static Group getActualGroup(final Set<Group> actualGroupSet, final Group expectedGroup) {
        return actualGroupSet.stream()
                .filter(expectedGroup::equals)
                .findFirst()
                .orElse(null);
    }

    private static boolean groupSetsHaveTheSameSize(final Set<Group> actualGroupSet, final Set<Group> expectedGroupSet) {
        return expectedGroupSet.size() == actualGroupSet.size();
    }

    private static boolean groupMatchesExpected(final Group actualGroup, final Group expectedGroup) {
        return actualGroup != null
                && teamSetsAreEquals(actualGroup.getTeamSet(), expectedGroup.getTeamSet())
                && matchSetsAreEquals(actualGroup.getMatchSet(), expectedGroup.getMatchSet());
    }

    private static boolean teamSetsAreEquals(final Set<Team> actualTeamSet, final Set<Team> expectedTeamSet) {
        return teamSetMatchesExpected(actualTeamSet, expectedTeamSet)
                && teamSetsHaveTheSameSize(actualTeamSet, expectedTeamSet);
    }

    private static boolean matchSetsAreEquals(final Set<Match> actualMatchSet, final Set<Match> expectedMatchSet) {
        return matchSetMatchesExpected(actualMatchSet, expectedMatchSet)
                && matchSetsHaveTheSameSize(actualMatchSet, expectedMatchSet);
    }

    private static boolean teamSetMatchesExpected(final Set<Team> actualTeamSet, final Set<Team> expectedTeamSet) {
        return expectedTeamSet.containsAll(actualTeamSet);
    }

    private static boolean teamSetsHaveTheSameSize(final Set<Team> actualTeamSet, final Set<Team> expectedTeamSet) {
        return expectedTeamSet.size() == actualTeamSet.size();
    }

    private static boolean matchSetMatchesExpected(final Set<Match> actualMatchSet, final Set<Match> expectedMatchSet) {
        return expectedMatchSet.containsAll(actualMatchSet);
    }

    private static boolean matchSetsHaveTheSameSize(final Set<Match> actualMatchSet, final Set<Match> expectedMatchSet) {
        return expectedMatchSet.size() == actualMatchSet.size();
    }

}