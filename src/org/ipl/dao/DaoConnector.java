package org.ipl.dao;

import java.io.*;
import java.util.*;

public class DaoConnector {

    public List<Map<String, String>> getDataFromMatchesCsv(){
        List<Map<String, String>> listOfMatches = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("matches.csv"));
            List<String> tableHead = new ArrayList<>();
            String[] headings = br.readLine().split(",");
            String line = "";
            while ((line = br.readLine()) != null){
                String[] row = line.split(",");
                Map<String, String> map = new HashMap<>();
                for(int i = 0; i < row.length; i++){
                    map.put(headings[i],row[i]);
                }
                listOfMatches.add(map);
            }
            System.out.println(listOfMatches);
        }
        catch(FileNotFoundException e){  // FileReader related Exception
            e.printStackTrace();
        }
        catch (IOException e){           // BufferedReader readLine() Exception
            e.printStackTrace();
        }
        catch (Exception e){            // If there is any other exception
            e.printStackTrace();
        }
        return listOfMatches;
    }

    public List<Map<String, String>> getDataFromDeliveriesCsv(){
        List<Map<String, String>> listOfDeliveries = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("deliveries.csv"));
            List<String> tableHead = new ArrayList<>();
            String[] headings = br.readLine().split(",");
            String line = "";
            while ((line = br.readLine()) != null){
                String[] row = line.split(",");
                Map<String, String> map = new HashMap<>();
                for(int i = 0; i < row.length; i++){
                    map.put(headings[i],row[i]);
//                    System.out.println(headings[i]+" : "+row[i]+" ");
                }
                listOfDeliveries.add(map);
                break;
            }
            System.out.println(listOfDeliveries);
        }
        catch(FileNotFoundException e){  // FileReader related Exception
            e.printStackTrace();
        }
        catch (IOException e){           // BufferedReader readLine() Exception
            e.printStackTrace();
        }
        catch (Exception e){            // If there is any other exception
            e.printStackTrace();
        }
        return  listOfDeliveries;
    }

    public static void main(String[] args) {
        DaoConnector daoConnector = new DaoConnector();
//        daoConnector.getDataFromMatchesCsv();
        daoConnector.getDataFromDeliveriesCsv();
    }
}
