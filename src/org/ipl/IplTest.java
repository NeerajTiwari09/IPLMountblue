package org.ipl;

import org.ipl.services.MatchesServices;

import java.util.Map;

public class IplTest {
    public static void main(String[] args) {
        MatchesServices matchesServices = new MatchesServices();
        int noOfMatches = matchesServices.getNumberOfMatchesPlayedPerYear(2017);
        System.out.println("Number of matches played per year: " + noOfMatches);
        Map<String, Integer> noOfWonMatches = matchesServices.getNumberOfMatchesWonOfAllTeamsOverAllYear();
        System.out.println("Number of matches won of all teams over all the years: " + noOfWonMatches);

    }
}
