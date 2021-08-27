package org.ipl.dao;

import java.io.*;
import java.util.*;

public class DaoConnector {

//    private List<Map<String, String>> matchesData;
//    private List<Map<String, String>> deliveriesData;
    private static DaoConnector dao;
    private DaoConnector(){
        // private constructor
    }
    public static DaoConnector getInstance(){
        if(dao == null){
            return new DaoConnector();
        }
        return dao;
    }
    public List<Map<String, String>> getDataFromMatchesCsv(){

        List<Map<String, String>> listOfMatches = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("matches.csv"));
            String[] headings = br.readLine().split(",");
            String line ;
            while ((line = br.readLine()) != null){
                String[] row = line.split(",");
                Map<String, String> map = new HashMap<>();
                for(int i = 0; i < row.length; i++){
                    map.put(headings[i],row[i]);
                }
                listOfMatches.add(map);
            }
        }
        catch(FileNotFoundException e){  // FileReader related Exception
            e.printStackTrace();
        }
        catch (IOException e){           // BufferedReader readLine() Exception
            e.printStackTrace();
        }
        return listOfMatches;
    }

    public List<Map<String, String>> getDataFromDeliveriesCsv(){
        List<Map<String, String>> listOfDeliveries = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("deliveries.csv"));
            String[] headings = br.readLine().split(",");
            String line ;
            while((line = br.readLine()) != null){
                String[] row = line.split(",");
                Map<String, String> map = new HashMap<>();
                for(int i = 0; i < row.length; i++){
                    map.put(headings[i],row[i]);
//                    System.out.println(headings[i]+" : "+row[i]+" ");
                }
                listOfDeliveries.add(map);
            }
        }
        catch(FileNotFoundException e){  // FileReader related Exception
            e.printStackTrace();
        }
        catch (IOException e){           // BufferedReader readLine() Exception
            e.printStackTrace();
        }
        return  listOfDeliveries;
    }

}
