package org.ipl.dao;

import org.ipl.model.Delivery;
import org.ipl.model.Match;

import java.io.*;
import java.util.*;

public class DaoConnector {

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

    private static DaoConnector dao;

    private DaoConnector(){

    }

    public static DaoConnector getInstance(){
        if(dao == null){
            return new DaoConnector();
        }
        return dao;
    }

    public List<Match> getMatchData(){

        List<Match> matches = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("./csv-files/matches.csv"));
            String[] heading = br.readLine().split(",");
            String line ;
            while ((line = br.readLine()) != null){
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
                match.setWinByRuns(Integer.valueOf(data[MATCH_WIN_BY_RUNS]));
                match.setWinByWickets(Integer.valueOf(data[MATCH_WIN_BY_WICKETS]));
                match.setPlayerOfMatch(data[MATCH_PLAYER_OF_MATCH]);
                match.setVenue(data[MATCH_VENUE]);
                match.setUmpire1(data[MATCH_UMPIRE1]);
                match.setUmpire2(data[MATCH_UMPIRE2]);
//                match.setUmpire3(data[MATCH_UMPIRE3]);
                matches.add(match);
            }
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return matches;
    }

    public synchronized List<Delivery> getDeliveryData(){
        List<Delivery> deliveries = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("./csv-files/deliveries.csv"));
            String[] headings = br.readLine().split(",");
            String line ;
            while((line = br.readLine()) != null){
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
                delivery.setLegbyeRun(Integer.valueOf(data[DELIVERY_LEGBYE_RUNS]));
                delivery.setNoballRun(Integer.valueOf(data[DELIVERY_NO_BALL_RUN]));
                delivery.setPenaltyRun(Integer.valueOf(data[DELIVERY_PENALTY_RUN]));
                delivery.setBatsmanRun(Integer.valueOf(data[DELIVERY_BATSMAN_RUN]));
                delivery.setExtraRun(Integer.valueOf(data[DELIVERY_EXTRA_RUN]));
                delivery.setTotalRun(Integer.valueOf(data[DELIVERY_TOTAL_RUN]));
                
//                delivery.setPlayerDismissed(data[DELIVERY_PLAYER_DISMISSED]);
//                delivery.setDismissalKind(data[DELIVERY_DISMISSAL_KIND]);
//                delivery.setFielder(data[DELIVERY_FIELDER]);
                deliveries.add(delivery);
            }
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return  deliveries;
    }
}
