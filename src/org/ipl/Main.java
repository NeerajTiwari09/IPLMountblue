package org.ipl;

import org.ipl.model.Delivery;
import org.ipl.model.Match;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    public static final int MATCH_ID = 0;
    public static final int MATCH_SEASON = 1;
    public static final int MATCH_CITY = 2;
    public static final int MATCH_DATE = 3;
    public static final int MATCH_TEAM1 = 4;
    public static final int MATCH_TEAM2 = 5;
    public static final int MATCH_TOSS_WINNER = 6;
    public static final int MATCH_TOSS_DECISION = 7;
    public static final int MATCH_RESULT = 8;
    public static final int MATCH_DL_APPLIED = 9;
    public static final int MATCH_WINNER = 10;
    public static final int MATCH_WIN_BY_RUNS = 11;
    public static final int MATCH_WIN_BY_WICKETS = 12;
    public static final int MATCH_PLAYER_OF_MATCH = 13;
    public static final int MATCH_VENUE = 14;
    public static final int MATCH_UMPIRE1 = 15;
    public static final int MATCH_UMPIRE2 = 16;
    public static final int MATCH_UMPIRE3 = 17;

    public static final int DELIVERY_MATCH_ID = 0;
    public static final int DELIVERY_INNING = 1;
    public static final int DELIVERY_BATTING_TEAM = 2;
    public static final int DELIVERY_BOWLING_TEAM = 3;
    public static final int DELIVERY_OVER = 4;
    public static final int DELIVERY_BALL = 5;
    public static final int DELIVERY_BATSMAN = 6;
    public static final int DELIVERY_NON_STRIKER = 7;
    public static final int DELIVERY_BOWLER = 8;
    public static final int DELIVERY_IS_SUPER_OVER = 9;
    public static final int DELIVERY_WIDE_RUN = 10;
    public static final int DELIVERY_BYE_RUN = 11;
    public static final int DELIVERY_LEGBYE_RUNS = 12;
    public static final int DELIVERY_NO_BALL_RUN = 13;
    public static final int DELIVERY_PENALTY_RUN = 14;
    public static final int DELIVERY_BATSMAN_RUN = 15;
    public static final int DELIVERY_EXTRA_RUN = 16;
    public static final int DELIVERY_TOTAL_RUN = 17;
    public static final int DELIVERY_PLAYER_DISMISSED = 18;
    public static final int DELIVERY_DISMISSAL_KIND = 19;
    public static final int DELIVERY_FIELDER = 20;

    public List<Match> getMatchesData() {
        List<Match> matches = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("./csv-files/matches.csv"));
            String heading = br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Match match = new Match();
                match.setMatchId(Integer.valueOf(data[MATCH_ID]));
                match.setSeason(data[MATCH_SEASON]);
                match.setCity(data[MATCH_CITY]);
                match.setDate(data[MATCH_DATE]);
                match.setTeam1(data[MATCH_TEAM1]);
                match.setTeam2(data[MATCH_TEAM2]);
                match.setTossWinner(data[MATCH_TOSS_WINNER]);
                match.setTossDecision(data[MATCH_TOSS_DECISION]);
                match.setResult(data[MATCH_RESULT]);
                match.setDlApplied(Integer.valueOf(data[MATCH_DL_APPLIED]));
                match.setWinner(data[MATCH_WINNER]);
                matches.add(match);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matches;
    }

    public synchronized List<Delivery> getDeliveriesData() {
        List<Delivery> deliveries = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("./csv-files/deliveries.csv"));
            String heading = br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Delivery delivery = new Delivery();
                delivery.setMatchId(Integer.valueOf(data[DELIVERY_MATCH_ID]));
                delivery.setInning(Integer.valueOf(data[DELIVERY_INNING]));
                delivery.setBattingTeam(data[DELIVERY_BATTING_TEAM]);
                delivery.setBowlingTeam(data[DELIVERY_BOWLING_TEAM]);
                delivery.setOver(Integer.valueOf(data[DELIVERY_OVER]));
                delivery.setBall(Integer.valueOf(data[DELIVERY_BALL]));
                delivery.setBatsman(data[DELIVERY_BATSMAN]);
                delivery.setNonStriker(data[DELIVERY_NON_STRIKER]);
                delivery.setBowler(data[DELIVERY_BOWLER]);
                delivery.setIsSuperOver(Integer.valueOf(data[DELIVERY_IS_SUPER_OVER]));
                delivery.setWideRun(Integer.valueOf(data[DELIVERY_WIDE_RUN]));
                delivery.setByeRun(Integer.valueOf(data[DELIVERY_BYE_RUN]));
                delivery.setLegByeRun(Integer.valueOf(data[DELIVERY_LEGBYE_RUNS]));
                delivery.setNoBallRun(Integer.valueOf(data[DELIVERY_NO_BALL_RUN]));
                delivery.setPenaltyRun(Integer.valueOf(data[DELIVERY_PENALTY_RUN]));
                delivery.setBatsmanRun(Integer.valueOf(data[DELIVERY_BATSMAN_RUN]));
                delivery.setExtraRun(Integer.valueOf(data[DELIVERY_EXTRA_RUN]));
                delivery.setTotalRun(Integer.valueOf(data[DELIVERY_TOTAL_RUN]));
                deliveries.add(delivery);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return deliveries;
    }

    public Map<Integer, Integer> getNumberOfMatchesPlayedPerYear(List<Match> matches) {
        Map<Integer, Integer> matchesByYear = new HashMap<>();
        for (Match match : matches) {
            int year = Integer.parseInt(match.getSeason());
            if (!matchesByYear.containsKey(year)) {
                matchesByYear.put(year, 1);
            } else {
                matchesByYear.put(year, matchesByYear.get(year) + 1);
            }
        }
        return matchesByYear;
    }

    public Map<String, Integer> getNumberOfMatchesWonOfAllTeamsOverAllYear(List<Match> matches) {
        Map<String, Integer> matchesWinByYear = new HashMap<>();
        for (Match match : matches) {
            if (!match.getResult().equals("no result")) {
                String teamName = match.getWinner();
                if (!matchesWinByYear.containsKey(teamName)) {
                    matchesWinByYear.put(teamName, 1);
                } else {
                    matchesWinByYear.put(teamName, matchesWinByYear.get(teamName) + 1);
                }
            }
        }
        return matchesWinByYear;
    }

    public Set<String> getVenueOfAllMatchesInYear(List<Match> matches, int year) {
        Set<String> venues = new HashSet<>();
        for (Match match : matches) {
            if (Integer.valueOf(match.getSeason()).equals(year)) {
                venues.add(match.getVenue());
            }
        }
        return venues;
    }

    public int getAllMatchesOfOneTeam(List<Match> matches, int year, String teamName) {
        int count = 0;
        for (Match match : matches) {
            if (Integer.valueOf(match.getSeason()).equals(year)
                    && (match.getTeam1().equals(teamName) || match.getTeam2().equals(teamName))) {
                count += 1;
            }
        }
        return count;
    }

    private List<Integer> getMatchIds(List<Match> matches, int year) {
        List<Integer> matchIds = new ArrayList<>();
        for (Match match : matches) {
            if (Integer.valueOf(match.getSeason()).equals(year)) {
                matchIds.add(match.getMatchId());
            }
        }
        return matchIds;
    }

    public Map<String, Integer> getExtraRunsConcededPerTeamByYear(List<Match> matches, List<Delivery> deliveries, int year) {
        List<Integer> matchIds = getMatchIds(matches, year);
        Map<String, Integer> extraRunByTeam = new HashMap<>();
        for (Delivery delivery : deliveries) {
            if (matchIds.contains(delivery.getMatchId())) {
                String teamName = delivery.getBattingTeam();
                if (!extraRunByTeam.containsKey(teamName)) {
                    extraRunByTeam.put(teamName, delivery.getExtraRun());
                } else {
                    extraRunByTeam.put(teamName, extraRunByTeam.get(teamName) + delivery.getExtraRun());
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
        for (Delivery delivery : deliveries) {
            if (matchIds.contains(delivery.getMatchId())) {
                String bowlerName = delivery.getBowler();
                if (!runGivenByBowler.containsKey(bowlerName)) {
                    runGivenByBowler.put(bowlerName, delivery.getTotalRun());
                    ballsThrownByBowler.put(bowlerName, 1);
                    name.add(bowlerName);
                } else {
                    runGivenByBowler.put(bowlerName, runGivenByBowler.get(bowlerName) + delivery.getTotalRun());
                    ballsThrownByBowler.put(bowlerName, ballsThrownByBowler.get(bowlerName) + 1);
                }
            }
        }
        Map<String, Float> economyByBowler = getEconomicRateOfBowlers(runGivenByBowler, ballsThrownByBowler, name);
        return sortEconomyRateByValues(economyByBowler);
    }

    private Map<String, Float> getEconomicRateOfBowlers(Map<String, Integer> runsByBowler, Map<String,
            Integer> ballsByBowler, List<String> listOfBowlers) {
        Map<String, Float> economyByBowler = new TreeMap<>();
        for (String bName : listOfBowlers) {
            float rate = ((float) runsByBowler.get(bName) / ballsByBowler.get(bName)) * 6;
            economyByBowler.put(bName, rate);
        }
        return economyByBowler;
    }

    static <K, V extends Comparable<? super V>>
    SortedSet<Map.Entry<K, V>> sortEconomyRateByValues(Map<K, V> map) {
        SortedSet<Map.Entry<K, V>> sortedEntries = new TreeSet<>(
                Map.Entry.comparingByValue()
        );
        sortedEntries.addAll(map.entrySet());
        return sortedEntries;
    }

    public static void main(String[] args) {
        Main main = new Main();

        List<Match> matches = main.getMatchesData();
        List<Delivery> deliveries = main.getDeliveriesData();

        Map<Integer, Integer> noOfMatches = main.getNumberOfMatchesPlayedPerYear(matches);
        System.out.println("Number of matches played per year: " + noOfMatches);
        Map<String, Integer> noOfWonMatches = main.getNumberOfMatchesWonOfAllTeamsOverAllYear(matches);
        System.out.println("Number of matches won of all teams over all year: " + noOfWonMatches);
        int playCount = main.getAllMatchesOfOneTeam(matches, 2017, "Sunrisers Hyderabad");
        System.out.println("For the year matches played by team: " + playCount);

        Map<String, Integer> extraRunGotByTeam = main.getExtraRunsConcededPerTeamByYear(matches, deliveries, 2016);
        System.out.println("For the year 2016 get the extra runs conceded per team: " + extraRunGotByTeam);
        SortedSet<Map.Entry<String, Float>> topEconomyByBowler = main.getTheTopEconomicalBowlers(matches, deliveries, 2015);
        System.out.println("For the year 2015 get the top economical bowlers: " + topEconomyByBowler);

    }
}
