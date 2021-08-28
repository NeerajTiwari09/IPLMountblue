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

    /**********************
     *Has some bugs
     *Not completed yet
     *Find the most Economical bowlers
     * *********************/
    public Map<String, Integer> getTheTopEconomicalBowlers(int year) {
        List<Map<String, String>> listMatch = dao.getDataFromMatchesCsv();
        List<Map<String, String>> listDelivery = dao.getDataFromDeliveriesCsv();
        List<Integer> idList = getMatchId(listMatch, year);
        System.out.println(idList);
        Map<String, Integer> topEconomicalBowlerMap = new HashMap<>();
        for (Integer i : idList) {
            for (Map<String, String> map : listDelivery) {
                if (Integer.valueOf(map.get("match_id")).equals(i)) {
                    String bowlerName = map.get("bowler");
                    if (!topEconomicalBowlerMap.containsKey(bowlerName)) {
                        topEconomicalBowlerMap.put(bowlerName, Integer.valueOf(map.get("total_runs")));
                    } else {
                        topEconomicalBowlerMap.put(bowlerName, topEconomicalBowlerMap.get(bowlerName) + Integer.valueOf(map.get("total_runs")));
                    }
                }
            }
//            break;
        }
        System.out.println(topEconomicalBowlerMap);
        return topEconomicalBowlerMap;
    }
}