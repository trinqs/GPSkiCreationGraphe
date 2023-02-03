import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        CSVReader reader =new CSVReader();

        ArrayList<ArrayList<String>> aerialWay = reader.readCSV();

        ArrayList<Chemin> chemins= new ArrayList<>();
        //System.out.println(aerialWay.size());

        for(ArrayList<String> chemin : aerialWay){

            chemins.add(new Chemin(chemin));
        }

        System.out.println("nb de chemins ");

        CreationGraph creationGraph = new CreationGraph();

        Set<Point> points = creationGraph.GetAllPoint(chemins);

        ArrayList<Arc> arcs = creationGraph.getAllArc(chemins);

        System.out.println("nb de points "+points.size()+" nb d'arcs "+arcs.size());

        int cpt2depoids=0;
        for(Arc arc : arcs){
            System.out.println(arc.toString());
            if (arc.getPoids()>1)
                cpt2depoids++;
        }
        System.out.println("Il y a " + cpt2depoids + " doublons");


        Map<Long, List<Arc>> arcsMap = new HashMap<>();
        for (Point point : points) {
            ArrayList<Arc> arcs_depuis_point = new ArrayList<>();
            for (Arc arc : arcs){
                if (arc.getFromTo().equals(point)){
                    arcs_depuis_point.add(arc);
                }
            }
            arcsMap.put(point.getId(),arcs_depuis_point);
        }

        Map<Long,Point> pointsMap = new HashMap<>();
        for (Point point: points ) {
            pointsMap.put(point.getId(), point);
        }

        Graphe graphe = new Graphe(pointsMap,arcsMap);
        graphe.clear();
        graphe.showDetails();

    }



}
