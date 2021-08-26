package org.ipl.services;

import org.ipl.dao.DaoConnector;

import java.util.List;
import java.util.Map;

public class MatchesServices {
    private DaoConnector dao;

    public int getNumberOfMatchesPlayedPerYear(int year){
        dao = new DaoConnector();
        int count = 0;
        List<Map<String, String>> listOfMatches = dao.getDataFromMatchesCsv();
        for (Map<String, String> map : listOfMatches) {
            if(Integer.valueOf(map.get("season")) == year) {
                count += 1;
            }
        }
        return count;
    }
}
