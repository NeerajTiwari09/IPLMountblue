package org.ipl.services;

import org.ipl.dao.DaoConnector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatchService {
    private DaoConnector dao = DaoConnector.getInstance();

    public Map<String, Integer> getNumberOfMatchesPlayedPerYear(){
//        DaoConnector dao = DaoConnector.getInstance();
        int count = 0;
        List<Map<String, String>> listOfMatches = dao.getDataFromMatchesCsv();
        Map<String, Integer> perYearMatchMap = new HashMap<>();
        for (Map<String, String> map : listOfMatches) {
            String year = map.get("season");
            if(!perYearMatchMap.containsKey(year)){
                perYearMatchMap.put(year, 1);
            }
            else{
               perYearMatchMap.put(year, perYearMatchMap.get(year)+1);
            }
        }
        return perYearMatchMap;
    }

    public Map<String, Integer> getNumberOfMatchesWonOfAllTeamsOverAllYear(){
//        DaoConnector dao = DaoConnector.getInstance();
        List<Map<String, String>> listOfMatches = dao.getDataFromMatchesCsv();
        Map<String, Integer> perTeamWinMap = new HashMap<>();
        int count = 0;
        for (Map<String, String> map : listOfMatches) {
            if(!map.get("result").equals( "no result")) {
                String teamName = map.get("winner");
                if(!perTeamWinMap.containsKey(map.get("winner"))){
                    perTeamWinMap.put(teamName,1);
                }
                else {
                    perTeamWinMap.put(teamName,perTeamWinMap.get(teamName)+1);
                }
            }
        }
        return perTeamWinMap;
    }
}
