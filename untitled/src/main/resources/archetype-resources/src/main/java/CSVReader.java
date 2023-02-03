import java.io.*;
import java.util.*;

public class CSVReader {
    //private String filepath = "D:/ETUDE/M2/S2/DillXp/TraitementCSV/traitementcsv/pisteType_10km_lesMenuires.csv";
    //private String filepath = "C:/Users/thoma/OneDrive/Documents/Etude/M2/S1/DillXP/SupaBase/CSV/traitementcsv/pisteType_10km_lesMenuires.csv";
    //private String filepath = "C:/Users/sylva/Sylvain/OneDrive/Perso/Universite/Master/DILL/traitementcsv/pisteType_10km_lesMenuires.csv";
    String filepath = new java.io.File("").getAbsolutePath() + "/traitementcsv/pisteType_10km_lesMenuires.csv";
    private static final String DELIMITER = ",";
    private static final String SEPARATOR = "\n";
    private static final String HEADERPOINT = "Latitude,Longitude";
    private static final String HEADERARC = "Latitude,Longitude";

    public CSVReader(){}

    public ArrayList<ArrayList<String>> readCSV() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filepath));


        String line = "";
        int cpt = 0;
        ArrayList<ArrayList<String>> fileline = new ArrayList<>();

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

                    fileline.add(listLine);
                }
            }
            cpt++;
        }

        return fileline;
    }


}
