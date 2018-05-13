package com.goltqup.morphy;

import com.goltqup.morphy.domain.Group;
import com.goltqup.morphy.domain.Team;
import com.goltqup.morphy.domain.Tournament;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toSet;

public class TournamentAssert {
    private static final String TOURNAMENT_ID = "RklGQVJ1c3NpYTIwMTg=";
    private static final String TOURNAMENT_NAME = "FIFA";
    private static final String TOURNAMENT_PLACE = "Russia";
    private static final int TOURNAMENT_YEAR = 2018;

    public static Tournament getExpectedTournament() {
        return new Tournament(TOURNAMENT_ID, TOURNAMENT_NAME, TOURNAMENT_PLACE, TOURNAMENT_YEAR, getGroupSet());
    }

    private static Set<Group> getGroupSet() {
        return getTournamentGroupsMap().entrySet().stream()
                .map(TournamentAssert::createGroup)
                .collect(toSet());
    }

    private static Team createTeam(final String idAndName) {
        final String[] idNameArray = idAndName.split(" ");
        return new Team.TeamBuilder(idNameArray[0], idNameArray[1]).build();
    }

    private static Group createGroup(Map.Entry<String, List<String>> stringListEntry) {
        final String[] idNameArray = stringListEntry.getKey().split(" ");
        return new Group(idNameArray[0], idNameArray[1],
                stringListEntry.getValue().stream()
                        .map(TournamentAssert::createTeam)
                        .collect(toSet()));
    }

    private static Map<String, List<String>> getTournamentGroupsMap() {
        final Map<String, List<String>> tournamentGroupsMap = new HashMap<>();
        tournamentGroupsMap.put("Qg== B", asList("UG9ydHVnYWw= Portugal", "SVJfSXJhbg== IR_Iran", "TW9yb2Njbw== Morocco", "U3BhaW4= Spain"));
        tournamentGroupsMap.put("Qw== C", asList("RGVubWFyaw== Denmark", "QXVzdHJhbGlh Australia", "UGVydQ== Peru", "RnJhbmNl France"));
        tournamentGroupsMap.put("Rw== G", asList("VHVuaXNpYQ== Tunisia", "QmVsZ2l1bQ== Belgium", "UGFuYW1h Panama", "RW5nbGFuZA== England"));
        tournamentGroupsMap.put("Rg== F", asList("U3dlZGVu Sweden", "TWV4aWNv Mexico", "R2VybWFueQ== Germany", "S29yZWFfUmVwdWJsaWM= Korea_Republic"));
        tournamentGroupsMap.put("QQ== A", asList("U2F1ZGlfQXJhYmlh Saudi_Arabia", "RWd5cHQ= Egypt", "UnVzc2lh Russia", "VXJ1Z3VheQ== Uruguay"));
        tournamentGroupsMap.put("SA== H", asList("UG9sYW5k Poland", "SmFwYW4= Japan", "U2VuZWdhbA== Senegal", "Q29sb21iaWE= Colombia"));
        tournamentGroupsMap.put("RA== D", asList("SWNlbGFuZA== Iceland", "TmlnZXJpYQ== Nigeria", "QXJnZW50aW5h Argentina", "Q3JvYXRpYQ== Croatia"));
        tournamentGroupsMap.put("RQ== E", asList("U3dpdHplcmxhbmQ= Switzerland", "Q29zdGFfUmljYQ== Costa_Rica", "QnJhemls Brazil", "U2VyYmlh Serbia"));

        return tournamentGroupsMap;
    }

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
        return actualGroup != null && teamSetsAreEquals(actualGroup.getTeamSet(), expectedGroup.getTeamSet());
    }

    private static boolean teamSetsAreEquals(final Set<Team> actualTeamSet, final Set<Team> expectedTeamSet) {
        return teamSetMatchesExpected(actualTeamSet, expectedTeamSet)
                && teamSetsHaveTheSameSize(actualTeamSet, expectedTeamSet);
    }

    private static boolean teamSetMatchesExpected(final Set<Team> actualTeamSet, final Set<Team> expectedTeamSet) {
        return expectedTeamSet.containsAll(actualTeamSet);
    }

    private static boolean teamSetsHaveTheSameSize(final Set<Team> actualTeamSet, final Set<Team> expectedTeamSet) {
        return expectedTeamSet.size() == actualTeamSet.size();
    }

}