package org.ipl;

import org.ipl.model.Delivery;
import org.ipl.model.Match;

import java.io.IOException;

import java.sql.*;
import java.util.*;

public class Main {

    public static final int MATCH_ID = 1;
    public static final int MATCH_SEASON = 2;
    public static final int MATCH_CITY = 3;
    public static final int MATCH_DATE = 4;
    public static final int MATCH_TEAM1 = 5;
    public static final int MATCH_TEAM2 = 6;
    public static final int MATCH_TOSS_WINNER = 7;
    public static final int MATCH_TOSS_DECISION = 8;
    public static final int MATCH_RESULT = 9;
    public static final int MATCH_DL_APPLIED = 10;
    public static final int MATCH_WINNER = 11;
    public static final int MATCH_WIN_BY_RUNS = 12;
    public static final int MATCH_WIN_BY_WICKETS = 13;
    public static final int MATCH_PLAYER_OF_MATCH = 14;
    public static final int MATCH_VENUE = 15;
    public static final int MATCH_UMPIRE1 = 16;
    public static final int MATCH_UMPIRE2 = 17;
    public static final int MATCH_UMPIRE3 = 18;

    public static final int DELIVERY_MATCH_ID = 1;
    public static final int DELIVERY_INNING = 2;
    public static final int DELIVERY_BATTING_TEAM = 3;
    public static final int DELIVERY_BOWLING_TEAM = 4;
    public static final int DELIVERY_OVER = 5;
    public static final int DELIVERY_BALL = 6;
    public static final int DELIVERY_BATSMAN = 7;
    public static final int DELIVERY_NON_STRIKER = 8;
    public static final int DELIVERY_BOWLER = 9;
    public static final int DELIVERY_IS_SUPER_OVER = 10;
    public static final int DELIVERY_WIDE_RUN = 11;
    public static final int DELIVERY_BYE_RUN = 12;
    public static final int DELIVERY_LEGBYE_RUNS = 13;
    public static final int DELIVERY_NO_BALL_RUN = 14;
    public static final int DELIVERY_PENALTY_RUN = 15;
    public static final int DELIVERY_BATSMAN_RUN = 16;
    public static final int DELIVERY_EXTRA_RUN = 17;
    public static final int DELIVERY_TOTAL_RUN = 18;
    public static final int DELIVERY_PLAYER_DISMISSED = 19;
    public static final int DELIVERY_DISMISSAL_KIND = 20;
    public static final int DELIVERY_FIELDER = 21;

    private Connection connection;
    private  Statement statement;

    public Connection getConnectionInstance() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/IPL", "root", "root8080");
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public List<Match> getMatchesData() {
        List<Match> matches = new ArrayList<>();
        try {
//            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM matches");
            while (resultSet.next()) {
                Match match = new Match();
                match.setMatchId(resultSet.getInt(MATCH_ID));
                match.setSeason(resultSet.getString(MATCH_SEASON));
                match.setCity(resultSet.getString(MATCH_CITY));
                match.setDate(resultSet.getString(MATCH_DATE));
                match.setTeam1(resultSet.getString(MATCH_TEAM1));
                match.setTeam2(resultSet.getString(MATCH_TEAM2));
                match.setTossWinner(resultSet.getString(MATCH_TOSS_WINNER));
                match.setTossDecision(resultSet.getString(MATCH_TOSS_DECISION));
                match.setResult(resultSet.getString(MATCH_RESULT));
                match.setDlApplied(resultSet.getInt(MATCH_DL_APPLIED));
                match.setWinner(resultSet.getString(MATCH_WINNER));
                match.setWinByRuns(resultSet.getInt(MATCH_WIN_BY_RUNS));
                match.setWinByWickets(resultSet.getInt(MATCH_WIN_BY_WICKETS));
                match.setPlayerOfMatch(resultSet.getString(MATCH_PLAYER_OF_MATCH));
                match.setVenue(resultSet.getString(MATCH_VENUE));
                match.setUmpire1(resultSet.getString(MATCH_UMPIRE1));
                match.setUmpire2(resultSet.getString(MATCH_UMPIRE2));
                match.setUmpire3(resultSet.getString(MATCH_UMPIRE3));
                matches.add(match);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matches;
    }

    public synchronized List<Delivery> getDeliveriesData() {
        List<Delivery> deliveries = new ArrayList<>();
        try {
//            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM deliveries");
            while (resultSet.next()) {
                Delivery delivery = new Delivery();
                delivery.setMatchId(resultSet.getInt(DELIVERY_MATCH_ID));
                delivery.setInning(resultSet.getInt(DELIVERY_INNING));
                delivery.setBattingTeam(resultSet.getString(DELIVERY_BATTING_TEAM));
                delivery.setBowlingTeam(resultSet.getString(DELIVERY_BOWLING_TEAM));
                delivery.setOver(resultSet.getInt(DELIVERY_OVER));
                delivery.setBall(resultSet.getInt(DELIVERY_BALL));
                delivery.setBatsman(resultSet.getString(DELIVERY_BATSMAN));
                delivery.setNonStriker(resultSet.getString(DELIVERY_NON_STRIKER));
                delivery.setBowler(resultSet.getString(DELIVERY_BOWLER));
                delivery.setIsSuperOver(resultSet.getInt(DELIVERY_IS_SUPER_OVER));
                delivery.setWideRun(resultSet.getInt(DELIVERY_WIDE_RUN));
                delivery.setByeRun(resultSet.getInt(DELIVERY_BYE_RUN));
                delivery.setLegByeRun(resultSet.getInt(DELIVERY_LEGBYE_RUNS));
                delivery.setNoBallRun(resultSet.getInt(DELIVERY_NO_BALL_RUN));
                delivery.setPenaltyRun(resultSet.getInt(DELIVERY_PENALTY_RUN));
                delivery.setBatsmanRun(resultSet.getInt(DELIVERY_BATSMAN_RUN));
                delivery.setExtraRun(resultSet.getInt(DELIVERY_EXTRA_RUN));
                delivery.setTotalRun(resultSet.getInt(DELIVERY_TOTAL_RUN));
                delivery.setPlayerDismissed(resultSet.getString(DELIVERY_PLAYER_DISMISSED));
                delivery.setDismissalKind(resultSet.getString(DELIVERY_DISMISSAL_KIND));
                delivery.setFielder(resultSet.getString(DELIVERY_FIELDER));
                deliveries.add(delivery);
            }
        } catch (SQLException e) {
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
        Connection con = main.getConnectionInstance();
        System.out.println(con);

        List<Match> matches = main.getMatchesData();
        List<Delivery> deliveries = main.getDeliveriesData();

        Map<Integer, Integer> noOfMatches = main.getNumberOfMatchesPlayedPerYear(matches);
        System.out.println("Number of matches played per year: " + noOfMatches);
        Map<String, Integer> noOfWonMatches = main.getNumberOfMatchesWonOfAllTeamsOverAllYear(matches);
        System.out.println("Number of matches won of all teams over all year: " + noOfWonMatches);
        int playCount = main.getAllMatchesOfOneTeam(matches, 2017, "Sunrisers Hyderabad");
        System.out.println("For the year matches played by team: " + playCount);
        Set<String> venues = main.getVenueOfAllMatchesInYear(matches, 2015);
        System.out.println("Name of venues in year: " + venues);

        Map<String, Integer> extraRunGotByTeam = main.getExtraRunsConcededPerTeamByYear(matches, deliveries, 2016);
        System.out.println("For the year 2016 get the extra runs conceded per team: " + extraRunGotByTeam);
        SortedSet<Map.Entry<String, Float>> topEconomyByBowler = main.getTheTopEconomicalBowlers(matches, deliveries, 2015);
        System.out.println("For the year 2015 get the top economical bowlers: " + topEconomyByBowler);
    }
}
