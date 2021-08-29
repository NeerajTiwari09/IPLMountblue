package org.ipl.services;

import org.ipl.dao.DaoConnector;

import java.util.*;

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

    public Set<String> getVenueOfAllMatchesInYear(int year){
        List<Map<String, String>> listMatches = dao.getDataFromMatchesCsv();
        Set<String> venuesSet = new HashSet<>();
        for(Map<String, String> map : listMatches){
            if(Integer.valueOf(map.get("season")).equals(year)) {
                venuesSet.add(map.get("venue"));
            }
        }
        return  venuesSet;
    }


}
