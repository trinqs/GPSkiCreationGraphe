import java.io.*;
import java.util.*;

public class CSVReader {
    //private String filepath = "D:/ETUDE/M2/S2/DillXp/TraitementCSV/traitementcsv/pisteType_10km_lesMenuires.csv";
    //private String filepath = "C:/Users/thoma/OneDrive/Documents/Etude/M2/S1/DillXP/SupaBase/CSV/traitementcsv/pisteType_10km_lesMenuires.csv";
    //private String filepath = "C:/Users/sylva/Sylvain/OneDrive/Perso/Universite/Master/DILL/traitementcsv/pisteType_10km_lesMenuires.csv";
    String filepathPiste = new java.io.File("").getAbsolutePath() + "/src/main/java/CSV/pisteType_10km_lesMenuires.csv";
    String filepathAerialWay = new java.io.File("").getAbsolutePath() + "/src/main/java/CSV/aerialways_ways_10km_lesMenuires.csv";
    //String filepath = new java.io.File("").getAbsolutePath() + "/test100.csv";
    private static final String DELIMITER = ",";
    private static final String SEPARATOR = "\n";
    private static final String HEADERPOINT = "Latitude,Longitude";
    private static final String HEADERARC = "Latitude,Longitude";

    public CSVReader(){}

    public ArrayList<ArrayList<String>> readCSVAll() throws IOException {
        ArrayList<ArrayList<String>> fileline = new ArrayList<>();
        fileline.addAll(readCSV(filepathPiste));
        fileline.addAll(readCSV(filepathAerialWay));
        return fileline;
    }

    public ArrayList<ArrayList<String>> readCSV(String file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));


        String line = "";
        int cpt = 0;
        ArrayList<ArrayList<String>> fileline = new ArrayList<>();

        Map<String, Integer> test = new HashMap<>();

        while ((line =br.readLine())!=null){

            ArrayList<String> listLine = new ArrayList<>();


            String[] values = line.split("\"");
            if(cpt>0) {

                values[2] = values[2].concat("test");

                String[] aerialWay0 = values[0].split(",");
                String[] aerialWay2 = values[2].substring(1).split(",");


                if(aerialWay0[3].equals("LineString")){

                    for(String x : aerialWay0){
                        listLine.add(x);
                    }

                    listLine.add(values[1]);

                    for(String x : aerialWay2){
                        if(x.equals("test")){
                            listLine.add("");
                        }
                        else{
                            listLine.add(x);
                        }
                    }

                    if(test.containsKey(aerialWay2[1])){
                        test.put(aerialWay2[1], test.get(aerialWay2[1])+1);
                    }
                    else{
                        test.put(aerialWay2[1], 1);
                    }

                    fileline.add(listLine);
                }
            }
            cpt++;
        }

        for (Map.Entry<String, Integer> entry : test.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        return fileline;
    }
}

