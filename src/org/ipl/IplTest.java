package org.ipl;

import org.ipl.dao.DaoConnector;
import org.ipl.model.Delivery;
import org.ipl.model.Match;
import org.ipl.services.DeliveryService;
import org.ipl.services.MatchService;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

public class IplTest {

    public static void main(String[] args) {
        DaoConnector dao = DaoConnector.getInstance();
        List<Match> matches = dao.getMatchData();
        List<Delivery> deliveries = dao.getDeliveryData();
        MatchService matchesServices = new MatchService();
        Map<Integer, Integer> noOfMatches = matchesServices.getNumberOfMatchesPlayedPerYear(matches);
        System.out.println("Number of matches played per year: " + noOfMatches);
//        Map<String, Integer> noOfWonMatches = matchesServices.getNumberOfMatchesWonOfAllTeamsOverAllYear();
//        System.out.println("Number of matches won of all teams over all the years: " + noOfWonMatches);
//        Set<String> venueSet = matchesServices.getVenueOfAllMatchesInYear(2015);
//        System.out.println("All venues of matches in 2015: " + venueSet);
//        int playCount = matchesServices.getAllMatchesOfOneTeam(2017, "Sunrisers Hyderabad");
//        System.out.println("For the year matches played by team: " + playCount);

        DeliveryService deliveryService = new DeliveryService();
        Map<String, Integer> teamGotExtraRunMap = deliveryService.getExtraRunsConcededPerTeam(matches, deliveries,2016);
        System.out.println("For the year 2016 get the extra runs conceded per team: " + teamGotExtraRunMap);
//        Map<String, Float> topEconomicalBowler = deliveryService.getTheTopEconomicalBowlers(2015);
//        System.out.println("For the year 2015 get the top economical bowlers: " + topEconomicalBowler);
//        SortedSet<Map.Entry<String, Float>> topStrikeRateBatsMan = deliveryService.getTheTopStrikeRateByBatsman(2015);
//        System.out.println("For the year 2015 get the top strike rate batsman: " + topStrikeRateBatsMan);

    }
}
