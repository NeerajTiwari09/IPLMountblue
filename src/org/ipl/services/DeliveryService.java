package org.ipl.services;

import org.ipl.dao.DaoConnector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeliveryService {
    private DaoConnector dao = DaoConnector.getInstance();

    private List<Integer> getMatchId(List<Map<String, String>> listMatch, int year) {
        List<Integer> idList = new ArrayList<>();
        for (Map<String, String> matchMap : listMatch) {
            if (Integer.valueOf(matchMap.get("season")).equals(year)) {
                idList.add(Integer.valueOf(matchMap.get("id")));
            }
        }
//        System.out.println(idList);
        return idList;
    }

    public Map<String, Integer> getExtraRunsConcededPerTeam(int year) {
        List<Map<String, String>> listMatch = dao.getDataFromMatchesCsv();
        List<Map<String, String>> listDelivery = dao.getDataFromDeliveriesCsv();
        List<Integer> idList = getMatchId(listMatch, year);
        Map<String, Integer> teamGotExtraRunMap = new HashMap<>();
//        Integer[] idList = new Integer[]{629,619,612,626,579,583,586,589,594,622,597,604,608,615};
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
//        System.out.println(teamGotExtraRunMap);
        return teamGotExtraRunMap;
    }

    public Map<String, Float> getTheTopEconomicalBowlers(int year) {
        List<Map<String, String>> listMatch = dao.getDataFromMatchesCsv();
        List<Map<String, String>> listDelivery = dao.getDataFromDeliveriesCsv();
        List<Integer> idList = getMatchId(listMatch, year);
        Map<String, Integer> totalBalls = new HashMap<>();
        List<String> name = new ArrayList<>();
        Map<String, Integer> bowlerRunsMap = new HashMap<>();
        for (Integer i : idList) {
            for (Map<String, String> map : listDelivery) { //  TODO - needs optimization here
                if (Integer.valueOf(map.get("match_id")).equals(i)) {
                    String bowlerName = map.get("bowler");
                    if (!bowlerRunsMap.containsKey(bowlerName)) {
                        bowlerRunsMap.put(bowlerName, Integer.valueOf(map.get("total_runs")));
                        totalBalls.put(bowlerName, 1);
                        name.add(bowlerName);
                    } else {
                        bowlerRunsMap.put(bowlerName, bowlerRunsMap.get(bowlerName) + Integer.valueOf(map.get("total_runs")));
                        totalBalls.put(bowlerName, totalBalls.get(bowlerName)+1);
                    }
                }
            }
        }
//        System.out.println(bowlerRunsMap);
//        System.out.println(totalBalls);
        return getTheTopEconomicalPlayer(bowlerRunsMap, totalBalls, name);
    }

    private Map<String, Float> getTheTopEconomicalPlayer(Map<String, Integer> playerRunsMap, Map<String, Integer> totalBalls, List<String> listOfBowers){
        Map<String, Float> topEconomicBowlers = new HashMap<>();
        // TODO- use merge() and apply (v1/v2)*6 (need to change totalBalls into Map<String, Float>);
        //no need of another list ie List<String>
//        playerRunsMap.forEach((key, value) ->
//                totalBalls.merge(key, value, (v1, v2) -> ((v2 / v1))*6));
//        System.out.println(totalBalls);
        for(String bName : listOfBowers){
            float rate = ((float)playerRunsMap.get(bName) / totalBalls.get(bName))*6;
            topEconomicBowlers.put(bName, rate);
        }
        return topEconomicBowlers;
    }

    public Map<String, Float> getTopEconomicalBatsMan(int year){
        List<Map<String, String>> listMatch = dao.getDataFromMatchesCsv();
        List<Map<String, String>> listDelivery = dao.getDataFromDeliveriesCsv();
        List<Integer> idList = getMatchId(listMatch, year);
        Map<String, Integer> totalBalls = new HashMap<>();
        List<String> name = new ArrayList<>();
        Map<String, Integer> batsmanRunsMap = new HashMap<>();
        for (Integer i : idList) {
            for (Map<String, String> map : listDelivery) { //  TODO - needs optimization here
                if (Integer.valueOf(map.get("match_id")).equals(i)) {
                    String batsmanName = map.get("batsman");
                    if (!batsmanRunsMap.containsKey(batsmanName)) {
                        batsmanRunsMap.put(batsmanName, Integer.valueOf(map.get("total_runs")));
                        totalBalls.put(batsmanName, 1);
                        name.add(batsmanName);
                    } else {
                        batsmanRunsMap.put(batsmanName, batsmanRunsMap.get(batsmanName) + Integer.valueOf(map.get("total_runs")));
                        totalBalls.put(batsmanName, totalBalls.get(batsmanName)+1);
                    }
                }
            }
        }

//        System.out.println(batsmanRunsMap);
        return  getTheTopEconomicalPlayer(batsmanRunsMap, totalBalls,name);
    }

}