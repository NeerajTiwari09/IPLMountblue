package org.ipl.services;

import org.ipl.dao.DaoConnector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeliveryService {
    private DaoConnector dao = DaoConnector.getInstance();

    private synchronized List<Integer> getMatchId(List<Map<String, String>> listMatch, int year) {
        List<Integer> idList = new ArrayList<>();
        for (Map<String, String> matchMap : listMatch) {
            if (Integer.valueOf(matchMap.get("season")).equals(year)) {
                idList.add(Integer.valueOf(matchMap.get("id")));
            }
        }
        return idList;
    }

    public Map<String, Integer> getExtraRunsConcededPerTeam(int year) {
        List<Map<String, String>> listMatch = dao.getDataFromMatchesCsv();
        List<Map<String, String>> listDelivery = dao.getDataFromDeliveriesCsv();
        List<Integer> idList = getMatchId(listMatch, year);
        Map<String, Integer> teamGotExtraRunMap = new HashMap<>();
        for (Integer i : idList) {
            for (Map<String, String> map : listDelivery) {
                if (Integer.valueOf(map.get("match_id")).equals(i)) {
                    String teamName = map.get("batting_team");
                    if (!teamGotExtraRunMap.containsKey(teamName)) {
                        teamGotExtraRunMap.put(teamName, Integer.valueOf(map.get("extra_runs")));
                    } else {
                        teamGotExtraRunMap.put(teamName, teamGotExtraRunMap.get(teamName) + Integer.valueOf(map.get("extra_runs")));
                    }
                }
            }
        }
        return teamGotExtraRunMap;
    }

    public Map<String, Float> getTheTopEconomicalBowlers(int year) {
        List<Map<String, String>> listMatch = dao.getDataFromMatchesCsv();
        List<Map<String, String>> listDelivery = dao.getDataFromDeliveriesCsv();
        List<Integer> idList = getMatchId(listMatch, year);
        Map<String, Integer> ballsByBowler = new HashMap<>();
        List<String> name = new ArrayList<>();
        Map<String, Integer> bowlerRunsMap = new HashMap<>();
        for (Integer i : idList) {
            for (Map<String, String> map : listDelivery) { //  TODO - needs optimization here
                if (Integer.valueOf(map.get("match_id")).equals(i)) {
                    String bowlerName = map.get("bowler");
                    if (!bowlerRunsMap.containsKey(bowlerName)) {
                        bowlerRunsMap.put(bowlerName, Integer.valueOf(map.get("total_runs")));
                        ballsByBowler.put(bowlerName, 1);
                        name.add(bowlerName);
                    } else {
                        bowlerRunsMap.put(bowlerName, bowlerRunsMap.get(bowlerName) + Integer.valueOf(map.get("total_runs")));
                        ballsByBowler.put(bowlerName, ballsByBowler.get(bowlerName)+1);
                    }
                }
            }
        }
        return getTheTopEconomicalBowler(bowlerRunsMap, ballsByBowler, name);
    }

    private Map<String, Float> getTheTopEconomicalBowler(Map<String, Integer> runsByBowler, Map<String, Integer> ballsByBowler, List<String> listOfBowlers){
        Map<String, Float> economyByBowler = new HashMap<>();
        // TODO- use merge() and apply (v1/v2)*6 (need to change ballsByBowler into Map<String, Float>);
       /* no need of another list ie List<String>
        runsByBowler.forEach((key, value) -> ballsByBowler.merge(key, value, (v1, v2) -> ((v2 / v1))*6));
        */
        for(String bName : listOfBowlers){
            float rate = ((float)runsByBowler.get(bName) / ballsByBowler.get(bName))*6;
            economyByBowler.put(bName, rate);
        }
        return economyByBowler;
    }

    private Map<String, Float> getStrikeRateByBatsman(Map<String, Integer> runByBatsman, Map<String, Integer> ballsByBatsman, List<String> listOfBatsman){
        Map<String, Float> topEconomicBowlers = new HashMap<>();
        for(String bowlerName : listOfBatsman){
            float rate = ((float)runByBatsman.get(bowlerName) / ballsByBatsman.get(bowlerName))*100;
            topEconomicBowlers.put(bowlerName, rate);
        }
        return topEconomicBowlers;
    }

    public Map<String, Float> getTheTopStrikeRateByBatsman(int year) {
        List<Map<String, String>> listMatch = dao.getDataFromMatchesCsv();
        List<Map<String, String>> listDelivery = dao.getDataFromDeliveriesCsv();
        List<Integer> idList = getMatchId(listMatch, year);
        Map<String, Integer> ballsByBatsman = new HashMap<>();
        List<String> listOfBatsman = new ArrayList<>();
        Map<String, Integer> runByBatsman = new HashMap<>();
        //  TODO - needs optimization here
        for (Integer i : idList) {
            for (Map<String, String> map : listDelivery) {
                if (Integer.valueOf(map.get("match_id")).equals(i)) {
                    String batsmanName = map.get("batsman");
                    if (!runByBatsman.containsKey(batsmanName)) {
                        runByBatsman.put(batsmanName, Integer.valueOf(map.get("total_runs")));
                        ballsByBatsman.put(batsmanName, 1);
                        listOfBatsman.add(batsmanName);
                    } else {
                        runByBatsman.put(batsmanName, runByBatsman.get(batsmanName) + Integer.valueOf(map.get("total_runs")));
                        ballsByBatsman.put(batsmanName, ballsByBatsman.get(batsmanName) + 1);
                    }
                }
            }
        }
        return getStrikeRateByBatsman(runByBatsman, ballsByBatsman, listOfBatsman);
    }
}