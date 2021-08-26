package org.ipl.services;

import org.ipl.dao.DaoConnector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatchesServices {
    private DaoConnector dao = new DaoConnector();;

    public int getNumberOfMatchesPlayedPerYear(int year){
//        dao = new DaoConnector();
        int count = 0;
        List<Map<String, String>> listOfMatches = dao.getDataFromMatchesCsv();
        for (Map<String, String> map : listOfMatches) {
            if(Integer.valueOf(map.get("season")) == year) {
                count += 1;
            }
        }
        return count;
    }

    public Map<String, Integer> getNumberOfMatchesWonOfAllTeamsOverAllYear(){
        List<Map<String, String>> listOfMatches = dao.getDataFromMatchesCsv();
        Map<String, Integer> teamMap = new HashMap<>();
        int count = 0;
        for (Map<String, String> map : listOfMatches) {
            if(!map.get("result").equals( "no result")) {
                String teamName = map.get("winner");
                if(!teamMap.containsKey(map.get("winner"))){
                    teamMap.put(teamName,1);
                }
                else {
                    teamMap.put(map.get("winner"),teamMap.get(teamName)+1);
                }
            }
        }
//        System.out.println(teamMap);
        return teamMap;
    }
}
