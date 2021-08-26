package org.ipl.services;

import org.ipl.dao.DaoConnector;

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

    public int getNumberOfMatchesWonOfAllTeamsOverAllYear(){
        List<Map<String, String>> listOfMatches = dao.getDataFromMatchesCsv();
        int count = 0;
        for (Map<String, String> map : listOfMatches) {
            if(!map.get("result").equals( "no result")) {
                count += 1;
            }
        }
        return count;
    }
}
