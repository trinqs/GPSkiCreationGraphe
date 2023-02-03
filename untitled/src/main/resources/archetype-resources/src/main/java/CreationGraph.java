import java.util.*;

public class CreationGraph {


    public Set<Point> GetAllPoint(ArrayList<Chemin> chemins){
        Set<Point> res= new HashSet<Point>();

        for(Chemin chemin : chemins){
            ArrayList<Point> listePoint = chemin.getCoordinates();
            res.addAll(listePoint);
        }
        return res;
    }



    public ArrayList<Arc> getAllArc(ArrayList<Chemin> chemins){
        Map<Arc, Float> poidArc = getPoidArc(chemins);
        long cptid = 0;

        ArrayList<Arc> res = new ArrayList<>();

        for (Arc arc : poidArc.keySet()){
            arc.setPoids(poidArc.get(arc));
            arc.setIdWay(cptid);
            cptid++;
            res.add(arc);
        }
        return res;
    }

    private Map<Arc, Float> getPoidArc(ArrayList<Chemin> chemins) {
        Map<Arc,Float> res = new HashMap<>();

        for(Chemin chemin : chemins){
            ArrayList<Point> listePoint = chemin.getCoordinates();

            for(int i = 0; i<listePoint.size()-1;i++){
                Arc arc = new Arc(listePoint.get(i),listePoint.get(i+1),0,chemin.getPisteDifficulty(), chemin.getName());

                if(res.containsKey(arc)){
                    res.replace(arc,res.get(arc)+1);
                }
                else{
                    res.put(arc,1f);
                }
            }
        }

        return res;
    }

}
