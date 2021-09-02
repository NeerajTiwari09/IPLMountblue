package org.ipl;

import org.ipl.dao.DaoClass;
import org.ipl.model.Delivery;
import org.ipl.model.Match;
import org.ipl.services.DeliveryService;
import org.ipl.services.MatchService;

import java.util.*;

public class IplTest {

    public static void main(String[] args) {
        DaoClass dao = DaoClass.getInstance();
        List<Match> matches = dao.getMatchesData();
        List<Delivery> deliveries = dao.getDeliveriesData();
        MatchService matchesServices = new MatchService();
        Map<Integer, Integer> noOfMatches = matchesServices.getNumberOfMatchesPlayedPerYear(matches);
        System.out.println("Number of matches played per year: " + noOfMatches);
        Map<String, Integer> noOfWonMatches = matchesServices.getNumberOfMatchesWonOfAllTeamsOverAllYear(matches);
        System.out.println("Number of matches won of all teams over all the years: " + noOfWonMatches);
//        Set<String> venueSet = matchesServices.getVenueOfAllMatchesInYear(matches, 2015);
//        System.out.println("All venues of matches in 2015: " + venueSet);
        int playCount = matchesServices.getAllMatchesOfOneTeam(matches, 2017, "Sunrisers Hyderabad");
        System.out.println("For the year matches played by team: " + playCount);

        DeliveryService deliveryService = new DeliveryService();
        Map<String, Integer> teamGotExtraRunMap = deliveryService.getExtraRunsConcededPerTeamByYear(matches, deliveries,2016);
//        System.out.println("For the year 2016 get the extra runs conceded per team: " + teamGotExtraRunMap);
        Iterator<String> itr1 = teamGotExtraRunMap.keySet().iterator();
        Iterator<Integer> itr2 = teamGotExtraRunMap.values().iterator();
        while (itr1.hasNext() && itr2.hasNext()) {
            System.out.println(itr1.next()+ " "+itr2.next());
        }
        SortedSet<Map.Entry<String, Float>> topEconomicalBowler = deliveryService.getTheTopEconomicalBowlers(matches, deliveries, 2015);
        for(Map.Entry<String , Float> entry : topEconomicalBowler){
            System.out.println(entry.getKey() +" : "+entry.getValue());
        }
//        System.out.println("For the year 2015 get the top economical bowlers: " + topEconomicalBowler);
//        SortedSet<Map.Entry<String, Float>> topStrikeRateBatsMan = deliveryService.getTheTopStrikeRateByBatsman(matches, deliveries, 2017);
//        System.out.println("For the year 2015 get the top strike rate batsman: " + topStrikeRateBatsMan);
//        for(Map.Entry<String , Float> entry : topStrikeRateBatsMan){
//            System.out.println(entry.getKey() +" : "+entry.getValue());
//        }
    }
}
