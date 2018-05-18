package com.goltqup.morphy;

import java.util.List;

public class GroupDTO {
    private final List<String> teamList;
    private final List<String> matchList;

    public GroupDTO(List<String> teamList, List<String> matchList) {
        this.teamList = teamList;
        this.matchList = matchList;
    }

    public List<String> getTeamList() {
        return teamList;
    }

    public List<String> getMatchList() {
        return matchList;
    }

}