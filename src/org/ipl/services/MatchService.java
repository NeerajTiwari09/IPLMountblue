package org.ipl.services;

import org.ipl.model.Match;

import java.util.*;

public class MatchService {
    public Map<Integer, Integer> getNumberOfMatchesPlayedPerYear(List<Match> matches){
        Map<Integer, Integer> matchesByYear = new HashMap<>();
        for (Match match : matches) {
            int year = Integer.parseInt(match.getSeason());
            if(!matchesByYear.containsKey(year)){
                matchesByYear.put(year, 1);
            }
            else{
               matchesByYear.put(year, matchesByYear.get(year)+1);
            }
        }
        return matchesByYear;
    }

    public Map<String, Integer> getNumberOfMatchesWonOfAllTeamsOverAllYear(List<Match> matches){
        Map<String, Integer> matchesWinByYear = new HashMap<>();
        for (Match match : matches) {
            if(!match.getResult().equals( "no result")) {
                String teamName = match.getWinner();
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

    public Set<String> getVenueOfAllMatchesInYear(List<Match> matches, int year){
        Set<String> venueSet = new HashSet<>();
        for(Match match : matches){
            if(Integer.valueOf(match.getSeason()).equals(year)) {
                venueSet.add(match.getVenue());
            }
        }
        return  venueSet;
    }

    public int  getAllMatchesOfOneTeam(List<Match> matches, int year, String teamName){
        int count = 0;
        for(Match match : matches){
            if(Integer.valueOf(match.getSeason()).equals(year) && (match.getTeam1().equals(teamName) || match.getTeam2().equals(teamName))) {
                count += 1;
            }
        }
        return count;
    }
}
