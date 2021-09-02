package org.ipl.services;

import org.ipl.model.Delivery;
import org.ipl.model.Match;

import java.util.*;

public class DeliveryService {

    private List<Integer> getMatchIds(List<Match> matches, int year) {
        List<Integer> matchIds = new ArrayList<>();
        for (Match match : matches) {
            if (Integer.valueOf(match.getSeason()).equals(year)) {
                matchIds.add(Integer.valueOf(match.getMatchId()));
            }
        }
        return matchIds;
    }

    public Map<String, Integer> getExtraRunsConcededPerTeamByYear(List<Match> matches, List<Delivery> deliveries, int year) {
        List<Integer> matchIds = getMatchIds(matches, year);
        Map<String, Integer> extraRunByTeam = new HashMap<>();
        for (Integer i : matchIds) {
            for (Delivery delivery : deliveries) {
                if (Integer.valueOf(delivery.getMatchId()).equals(i)) {
                    String teamName = delivery.getBattingTeam();
                    if (!extraRunByTeam.containsKey(teamName)) {
                        extraRunByTeam.put(teamName, delivery.getExtraRun());
                    } else {
                        extraRunByTeam.put(teamName, extraRunByTeam.get(teamName) + delivery.getExtraRun());
                    }
                }
            }
        }
        return extraRunByTeam;
    }

    public SortedSet<Map.Entry<String, Float>> getTheTopEconomicalBowlers(List<Match> matches, List<Delivery> deliveries, int year) {
        Map<String, Integer> ballsThrownByBowler = new HashMap<>();
        List<String> name = new ArrayList<>();
        Map<String, Integer> runGivenByBowler = new HashMap<>();
        List<Integer> matchIds = getMatchIds(matches, year);
        for (Integer i : matchIds) {
            for (Delivery delivery : deliveries) {
                if (delivery.getMatchId().equals(i)) {
                    String bowlerName = delivery.getBowler();
                    if (!runGivenByBowler.containsKey(bowlerName)) {
                        runGivenByBowler.put(bowlerName, Integer.valueOf(delivery.getTotalRun()));
                        ballsThrownByBowler.put(bowlerName, 1);
                        name.add(bowlerName);
                    } else {
                        runGivenByBowler.put(bowlerName, runGivenByBowler.get(bowlerName) + delivery.getTotalRun());
                        ballsThrownByBowler.put(bowlerName, ballsThrownByBowler.get(bowlerName)+1);
                    }
                }
            }
        }
        Map<String, Float> economyByBowler = getEconomicRateOfBowlers(runGivenByBowler, ballsThrownByBowler, name);
        SortedSet<Map.Entry<String, Float>> sortedEconomicRateOfBowlers = sortEconomyRateByValues(economyByBowler);
        return sortedEconomicRateOfBowlers;
    }

    private Map<String, Float> getEconomicRateOfBowlers(Map<String, Integer> runsByBowler, Map<String, Integer> ballsByBowler, List<String> listOfBowlers){
        Map<String, Float> economyByBowler = new TreeMap<>();
        // TODO- use merge() and apply (v1/v2)*6 (need to change ballsByBowler into Map<String, Float>);
       /* no need of another list ie List<String>
        *runsByBowler.forEach((key, value) -> ballsByBowler.merge(key, value, (v1, v2) -> ((v2 / v1))*6));
        */
        for(String bName : listOfBowlers){
            float rate = ((float)runsByBowler.get(bName) / ballsByBowler.get(bName)) * 6;
            economyByBowler.put(bName, rate);
        }
        return economyByBowler;
    }

    static <K,V extends Comparable<? super V>>
    SortedSet<Map.Entry<K,V>> sortEconomyRateByValues(Map<K,V> map) {
        SortedSet<Map.Entry<K,V>> sortedEntries = new TreeSet<Map.Entry<K,V>>(
                new Comparator<Map.Entry<K,V>>() {
                    @Override public int compare(Map.Entry<K,V> e1, Map.Entry<K,V> e2) {
                        int res = e1.getValue().compareTo(e2.getValue());
                        return res != 0 ? res : 1;
                    }
                }
        );
        sortedEntries.addAll(map.entrySet());
        return sortedEntries;
    }

    private Map<String, Float> getStrikeRateByBatsman(Map<String, Integer> runByBatsman, Map<String, Integer> ballsPlayedByBatsman, List<String> listOfBatsman){
        Map<String, Float> strikeRateByBatsman = new TreeMap<>();
        for(String bowlerName : listOfBatsman){
            float rate = ((float)runByBatsman.get(bowlerName) / ballsPlayedByBatsman.get(bowlerName)) * 100;
            strikeRateByBatsman.put(bowlerName, rate);
        }
        return strikeRateByBatsman;
    }

    public SortedSet<Map.Entry<String, Float>> getTheTopStrikeRateByBatsman(List<Match> matches, List<Delivery> deliveries, int year) {
        List<Integer> matchIds = getMatchIds(matches, year);
        Map<String, Integer> ballsByBatsman = new HashMap<>();
        List<String> listOfBatsman = new ArrayList<>();
        Map<String, Integer> runByBatsman = new HashMap<>();
        for (Integer i : matchIds) {
            for (Delivery delivery : deliveries) {
                if (delivery.getMatchId().equals(i)) {
                    String batsmanName = delivery.getBatsman();
                    if (!runByBatsman.containsKey(batsmanName)) {
                        runByBatsman.put(batsmanName, delivery.getTotalRun());
                        ballsByBatsman.put(batsmanName, 1);
                        listOfBatsman.add(batsmanName);
                    } else {
                        runByBatsman.put(batsmanName, runByBatsman.get(batsmanName) + delivery.getTotalRun());
                        ballsByBatsman.put(batsmanName, ballsByBatsman.get(batsmanName) + 1);
                    }
                }
            }
        }
        Map<String, Float> strikeRateByBatsman = getStrikeRateByBatsman(runByBatsman, ballsByBatsman, listOfBatsman);
        SortedSet<Map.Entry<String, Float>> sortedTopStrikeRateByBatsman = entriesSortedByValues(strikeRateByBatsman);
        return sortedTopStrikeRateByBatsman;
    }

    static <K,V extends Comparable<? super V>>
    SortedSet<Map.Entry<K,V>> entriesSortedByValues(Map<K,V> map) {
        SortedSet<Map.Entry<K,V>> sortedEntries = new TreeSet<Map.Entry<K,V>>(
                new Comparator<Map.Entry<K,V>>() {
                    @Override public int compare(Map.Entry<K,V> e1, Map.Entry<K,V> e2) {
                        int res = e2.getValue().compareTo(e1.getValue());
                        return res != 0 ? res : 1;
                    }
                }
        );
        sortedEntries.addAll(map.entrySet());
        return sortedEntries;
    }
}