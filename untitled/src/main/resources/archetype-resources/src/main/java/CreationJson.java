import netscape.javascript.JSObject;

import java.io.File;
import java.io.IOException;

public class CreationJson {

    String filepath = new java.io.File("").getAbsolutePath() + "/traitementcsv/Json_graphe_out/graphe.json";

    public void createJson(Graphe graphe) throws IOException {

        JSONObject grapheJson = new JSONObject();

        try{
            mapper.writeValue(new File(filepath), graphe);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }


}
