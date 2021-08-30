package org.ipl.services;

import org.ipl.dao.DaoConnector;

import java.util.*;

public class MatchService {
    private DaoConnector dao = DaoConnector.getInstance();

    public Map<Integer, Integer> getNumberOfMatchesPlayedPerYear(){
//        DaoConnector dao = DaoConnector.getInstance();
        List<Map<String, String>> listOfMatches = dao.getDataFromMatchesCsv();
        Map<Integer, Integer> matchesByYear = new HashMap<>();
        for (Map<String, String> map : listOfMatches) {
            int year = Integer.parseInt(map.get("season"));
            if(!matchesByYear.containsKey(year)){
                matchesByYear.put(year, 1);
            }
            else{
               matchesByYear.put(year, matchesByYear.get(year)+1);
            }
        }
        return matchesByYear;
    }

    public Map<String, Integer> getNumberOfMatchesWonOfAllTeamsOverAllYear(){
//        DaoConnector dao = DaoConnector.getInstance();
        List<Map<String, String>> listOfMatches = dao.getDataFromMatchesCsv();
        Map<String, Integer> matchesWinByYear = new HashMap<>();
        for (Map<String, String> map : listOfMatches) {
            if(!map.get("result").equals( "no result")) {
                String teamName = map.get("winner");
                if(!matchesWinByYear.containsKey(teamName)){
                    matchesWinByYear.put(teamName,1);
                }
                else {
                    matchesWinByYear.put(teamName,matchesWinByYear.get(teamName)+1);
                }
            }
        }
        return matchesWinByYear;
    }

    public Set<String> getVenueOfAllMatchesInYear(int year){
        List<Map<String, String>> listMatches = dao.getDataFromMatchesCsv();
        Set<String> venueSet = new HashSet<>();
        for(Map<String, String> map : listMatches){
            if(Integer.valueOf(map.get("season")).equals(year)) {
                venueSet.add(map.get("venue"));
            }
        }
        return  venueSet;
    }

    public int  getAllMatchesOfOneTeam(int year, String teamName){
        List<Map<String, String>> listMatch = dao.getDataFromMatchesCsv();
        int count = 0;
        for(Map<String, String> map : listMatch){
            if(Integer.valueOf(map.get("season")).equals(year) && (map.get("team1").equals(teamName) || map.get("team2").equals(teamName))) {
                count += 1;
            }
        }
        return count;
    }
}
