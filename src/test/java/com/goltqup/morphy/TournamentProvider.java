package com.goltqup.morphy;

import com.goltqup.morphy.domain.Group;
import com.goltqup.morphy.domain.Match;
import com.goltqup.morphy.domain.Team;
import com.goltqup.morphy.domain.Tournament;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.goltqup.morphy.TestUtils.getObjectFromJson;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toSet;

public class TournamentProvider {
    private static final String TOURNAMENT_ID = "RklGQVJ1c3NpYTIwMTg=";
    private static final String TOURNAMENT_NAME = "FIFA";
    private static final String TOURNAMENT_PLACE = "Russia";
    private static final int TOURNAMENT_YEAR = 2018;

    public static Tournament getExpectedTournament() {
        return new Tournament(TOURNAMENT_ID, TOURNAMENT_NAME, TOURNAMENT_PLACE, TOURNAMENT_YEAR, getGroupSet());
    }

    private static Set<Group> getGroupSet() {
        return getTournamentGroupsMap().entrySet().stream()
                .map(TournamentProvider::createGroup)
                .collect(toSet());
    }

    private static Group createGroup(Map.Entry<String, GroupDTO> stringGroupDTOEntry) {
        final String[] idNameArray = stringGroupDTOEntry.getKey().split(" ");
        return new Group(idNameArray[0], idNameArray[1],
                stringGroupDTOEntry.getValue().getTeamList().stream()
                        .map(TournamentProvider::getTeam).collect(toSet()),
                stringGroupDTOEntry.getValue().getMatchList().stream()
                        .map(TournamentProvider::getMatch).collect(toSet()));
    }

    private static Team getTeam(final String idAndName) {
        final String[] idNameArray = idAndName.split(" ");
        return new Team.TeamBuilder(idNameArray[0], idNameArray[1]).build();
    }

    private static Match getMatch(final String matchString) {
        final String[] matchArray = matchString.split(" ");
        final String id = matchArray[0];
        final String stadium = matchArray[1];
        final LocalDateTime schedule = LocalDateTime.parse(matchArray[2]);
        final String localTeam = matchArray[3];
        final String visitorTeam = matchArray[4];
        return new Match.MatchBuilder(id, stadium, Date.from(schedule.atZone(ZoneId.systemDefault()).toInstant()), localTeam, visitorTeam).build();
    }

    private static Map<String, GroupDTO> getTournamentGroupsMap() {
        final Map<String, GroupDTO> tournamentGroupsMap = new HashMap<>();
        tournamentGroupsMap.put("Qg== B",
                new GroupDTO(asList("UG9ydHVnYWw= Portugal", "SVJfSXJhbg== IR_Iran", "TW9yb2Njbw== Morocco", "U3BhaW4= Spain"),
                        asList("TW9yZG92aWFfQXJlbmEyMDE4LTA2LTIwVDE4OjAw Mordovia_Arena 2018-06-20T18:00 IR_Iran Portugal",
                                "S2FsaW5pbmdyYWRfU3RhZGl1bTIwMTgtMDYtMjBUMTg6MDA= Kaliningrad_Stadium 2018-06-20T18:00 Spain Morocco",
                                "S2F6YW5fQXJlbmEyMDE4LTA2LTIwVDE4OjAw Kazan_Arena 2018-06-20T18:00 IR_Iran Spain",
                                "THV6aG5pa2lfU3RhZGl1bTIwMTgtMDYtMjBUMTI6MDA= Luzhniki_Stadium 2018-06-20T12:00 Portugal Morocco",
                                "U2FpbnRfUGV0ZXJzYnVyZ19TdGFkaXVtMjAxOC0wNi0xNVQxNTowMA== Saint_Petersburg_Stadium 2018-06-15T15:00 Morocco IR_Iran",
                                "RmlzaHRfU3RhZGl1bTIwMTgtMDYtMTVUMTg6MDA= Fisht_Stadium 2018-06-15T18:00 Portugal Spain")));
        tournamentGroupsMap.put("Qw== C",
                new GroupDTO(asList("RGVubWFyaw== Denmark", "QXVzdHJhbGlh Australia", "UGVydQ== Peru", "RnJhbmNl France"),
                        asList("TW9yZG92aWFfQXJlbmEyMDE4LTA2LTE2VDE2OjAw Mordovia_Arena 2018-06-16T16:00 Peru Denmark",
                                "RWthdGVyaW5idXJnX0FyZW5hMjAxOC0wNi0yMVQxNTowMA== Ekaterinburg_Arena 2018-06-21T15:00 France Peru",
                                "RmlzaHRfU3RhZGl1bTIwMTgtMDYtMjZUMTQ6MDA= Fisht_Stadium 2018-06-26T14:00 Australia Peru",
                                "S2F6YW5fQXJlbmEyMDE4LTA2LTE2VDEwOjAw Kazan_Arena 2018-06-16T10:00 France Australia",
                                "U2FtYXJhX0FyZW5hMjAxOC0wNi0yMVQxMjowMA== Samara_Arena 2018-06-21T12:00 Denmark Australia",
                                "THV6aG5pa2lfU3RhZGl1bTIwMTgtMDYtMjZUMTQ6MDA= Luzhniki_Stadium 2018-06-26T14:00 Denmark France")));
        tournamentGroupsMap.put("Rw== G",
                new GroupDTO(asList("VHVuaXNpYQ== Tunisia", "QmVsZ2l1bQ== Belgium", "UGFuYW1h Panama", "RW5nbGFuZA== England"),
                        asList("S2FsaW5pbmdyYWRfU3RhZGl1bTIwMTgtMDYtMjhUMTg6MDA= Kaliningrad_Stadium 2018-06-28T18:00 England Belgium",
                                "U3BhcnRha19TdGFkaXVtMjAxOC0wNi0yM1QxMjowMA== Spartak_Stadium 2018-06-23T12:00 Belgium Tunisia",
                                "TW9yZG92aWFfQXJlbmEyMDE4LTA2LTI4VDE4OjAw Mordovia_Arena 2018-06-28T18:00 Panama Tunisia",
                                "Vm9sZ29ncmFkX0FyZW5hMjAxOC0wNi0xOFQxODowMA== Volgograd_Arena 2018-06-18T18:00 Tunisia England",
                                "Tml6aG55X05vdmdvcm9kX1N0YWRpdW0yMDE4LTA2LTI0VDEyOjAw Nizhny_Novgorod_Stadium 2018-06-24T12:00 England Panama",
                                "RmlzaHRfU3RhZGl1bTIwMTgtMDYtMThUMTU6MDA= Fisht_Stadium 2018-06-18T15:00 Belgium Panama")));
        tournamentGroupsMap.put("Rg== F",
                new GroupDTO(asList("U3dlZGVu Sweden", "TWV4aWNv Mexico", "R2VybWFueQ== Germany", "S29yZWFfUmVwdWJsaWM= Korea_Republic"),
                        asList("RmlzaHRfU3RhZGl1bTIwMTgtMDYtMjNUMTg6MDA= Fisht_Stadium 2018-06-23T18:00 Germany Sweden",
                                "Tml6aG55X05vdmdvcm9kX1N0YWRpdW0yMDE4LTA2LTE4VDEyOjAw Nizhny_Novgorod_Stadium 2018-06-18T12:00 Sweden Korea_Republic",
                                "S2F6YW5fQXJlbmEyMDE4LTA2LTI3VDE0OjAw Kazan_Arena 2018-06-27T14:00 Korea_Republic Germany",
                                "THV6aG5pa2lfU3RhZGl1bTIwMTgtMDYtMTdUMTU6MDA= Luzhniki_Stadium 2018-06-17T15:00 Germany Mexico",
                                "Um9zdG92X0FyZW5hMjAxOC0wNi0yM1QxNTowMA== Rostov_Arena 2018-06-23T15:00 Korea_Republic Mexico",
                                "RWthdGVyaW5idXJnX0FyZW5hMjAxOC0wNi0yN1QxNDowMA== Ekaterinburg_Arena 2018-06-27T14:00 Mexico Sweden")));
        tournamentGroupsMap.put("QQ== A",
                new GroupDTO(asList("U2F1ZGlfQXJhYmlh Saudi_Arabia", "RWd5cHQ= Egypt", "UnVzc2lh Russia", "VXJ1Z3VheQ== Uruguay"),
                        asList("Vm9sZ29ncmFkX0FyZW5hMjAxOC0wNi0yNVQxNDowMA== Volgograd_Arena 2018-06-25T14:00 Saudi_Arabia Egypt",
                                "U2FpbnRfUGV0ZXJzYnVyZ19TdGFkaXVtMjAxOC0wNi0xOVQxODowMA== Saint_Petersburg_Stadium 2018-06-19T18:00 Russia Egypt",
                                "THV6aG5pa2lfU3RhZGl1bTIwMTgtMDYtMTRUMTU6MDA= Luzhniki_Stadium 2018-06-14T15:00 Russia Saudi__Arabia",
                                "Um9zdG92X0FyZW5hMjAxOC0wNi0yMFQxNTowMA== Rostov_Arena 2018-06-20T15:00 Uruguay Saudi_Arabia",
                                "U2FtYXJhX0FyZW5hMjAxOC0wNi0yNVQxNDowMA== Samara_Arena 2018-06-25T14:00 Uruguay Russia",
                                "RWthdGVyaW5idXJnX0FyZW5hMjAxOC0wNi0xNVQxMjowMA== Ekaterinburg_Arena 2018-06-15T12:00 Egypt Uruguay")));
        tournamentGroupsMap.put("SA== H",
                new GroupDTO(asList("UG9sYW5k Poland", "SmFwYW4= Japan", "U2VuZWdhbA== Senegal", "Q29sb21iaWE= Colombia"),
                        asList("S2F6YW5fQXJlbmEyMDE4LTA2LTI0VDE4OjAw Kazan_Arena 2018-06-24T18:00 Poland Colombia",
                                "U3BhcnRha19TdGFkaXVtMjAxOC0wNi0xOVQxNTowMA== Spartak_Stadium 2018-06-19T15:00 Poland Senegal",
                                "RWthdGVyaW5idXJnX0FyZW5hMjAxOC0wNi0yNFQxNTowMA== Ekaterinburg_Arena 2018-06-24T15:00 Japan Senegal",
                                "U2FtYXJhX0FyZW5hMjAxOC0wNi0yOFQxNDowMA== Samara_Arena 2018-06-28T14:00 Senegal Colombia",
                                "Vm9sZ29ncmFkX0FyZW5hMjAxOC0wNi0yOFQxNDowMA== Volgograd_Arena 2018-06-28T14:00 Japan Poland",
                                "TW9yZG92aWFfQXJlbmEyMDE4LTA2LTE5VDEyOjAw Mordovia_Arena 2018-06-19T12:00 Colombia Japan")));
        tournamentGroupsMap.put("RA== D",
                new GroupDTO(asList("SWNlbGFuZA== Iceland", "TmlnZXJpYQ== Nigeria", "QXJnZW50aW5h Argentina", "Q3JvYXRpYQ== Croatia"),
                        asList("Vm9sZ29ncmFkX0FyZW5hMjAxOC0wNi0yMlQxNTowMA== Volgograd_Arena 2018-06-22T15:00 Nigeria Iceland",
                                "Um9zdG92X0FyZW5hMjAxOC0wNi0yNlQxODowMA== Rostov_Arena 2018-06-26T18:00 Iceland Croatia",
                                "U3BhcnRha19TdGFkaXVtMjAxOC0wNi0xNlQxMzowMA== Spartak_Stadium 2018-06-16T13:00 Argentina Iceland",
                                "S2FsaW5pbmdyYWRfU3RhZGl1bTIwMTgtMDYtMTZUMTk6MDA= Kaliningrad_Stadium 2018-06-16T19:00 Croatia Nigeria",
                                "Tml6aG55X05vdmdvcm9kX1N0YWRpdW0yMDE4LTA2LTIxVDE4OjAw Nizhny_Novgorod_Stadium 2018-06-21T18:00 Argentina Croatia",
                                "U2FpbnRfUGV0ZXJzYnVyZ19TdGFkaXVtMjAxOC0wNi0yNlQxODowMA== Saint_Petersburg_Stadium 2018-06-26T18:00 Nigeria Argentina")));
        tournamentGroupsMap.put("RQ== E",
                new GroupDTO(asList("U3dpdHplcmxhbmQ= Switzerland", "Q29zdGFfUmljYQ== Costa_Rica", "QnJhemls Brazil", "U2VyYmlh Serbia"),
                        asList("U3BhcnRha19TdGFkaXVtMjAxOC0wNi0yN1QxODowMA== Spartak_Stadium 2018-06-27T18:00 Serbia Brazil",
                                "Um9zdG92X0FyZW5hMjAxOC0wNi0xN1QxODowMA== Rostov_Arena 2018-06-17T18:00 Brazil Switzerland",
                                "U2FtYXJhX0FyZW5hMjAxOC0wNi0xN1QxMjowMA== Samara_Arena 2018-06-17T12:00 Costa_Rica Serbia",
                                "Tml6aG55X05vdmdvcm9kMjAxOC0wNi0yN1QxODowMA== Nizhny_Novgorod 2018-06-27T18:00 Switzerland Costa_Rica",
                                "U2FpbnRfUGV0ZXJzYnVyZ19TdGFkaXVtMjAxOC0wNi0yMlQxMjowMA== Saint_Petersburg_Stadium 2018-06-22T12:00 Brazil Costa_Rica",
                                "S2FsaW5pbmdyYWRfU3RhZGl1bTIwMTgtMDYtMjJUMTg6MDA= Kaliningrad_Stadium 2018-06-22T18:00 Serbia Switzerland")));

        return tournamentGroupsMap;
    }

    public static Tournament getExpectedTournamentFromJson() throws IOException {
        return getObjectFromJson("/expected/tournament.json", Tournament.class);
    }

}
