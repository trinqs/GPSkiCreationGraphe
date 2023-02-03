import java.util.*;

public class Graphe {
    private Map<Long, List<Arc>> arcs;
    private Map<Long, Point> points;

    public Graphe(ArrayList<Chemin> chemins) {
        CreationGraph creationGraph = new CreationGraph();
        Set<Point> pointSet = creationGraph.GetAllPoint(chemins);
        this.points = new HashMap<>();
        for (Point point : pointSet) {
            points.put(point.getId(),point);
        }
        ArrayList<Arc> res_Arcs = creationGraph.getAllArc(chemins);
        this.arcs = new HashMap<>();
        for (Long longId : points.keySet()) {
            ArrayList<Arc> arcs_depuis_point = new ArrayList<>();
            for (Arc arc : res_Arcs){
                if (arc.getFromTo().equals(points.get(longId))){
                    arcs_depuis_point.add(arc);
                }
            }
            arcs.put(longId,arcs_depuis_point);
        }
    }

    public Graphe(Map<Long, Point> points,  Map<Long, List<Arc>> arcs) {
        this.arcs = arcs;
        this.points = points;
    }

    public Map<Long, List<Arc>>getArcs() {
        return arcs;
    }

    public void setArcs(Map<Long, List<Arc>> arcs) {
        this.arcs = arcs;
    }

    public Map<Long, Point> getPoints() {
        return points;
    }

    public void setPoints(Map<Long, Point> points) {
        this.points = points;
    }
    public void showDetails(){
        int nb_sommet=0, nb_arcs=0;
        nb_sommet = points.size();
        for (Long idPoint : arcs.keySet()){
            nb_arcs += arcs.get(idPoint).size();
        }
        System.out.println("Il y a " + nb_sommet + " sommets et " + nb_arcs + " arcs dans le graphe");
    }

    public void clear(){
        int cptPrblm=0;

        ArrayList<Long> idToDelete = new ArrayList<>();

        for (Long idPoint : points.keySet()) {

            ArrayList<Long> precedents = pointsPrecedents(idPoint);

            //Si c'est un point qui n'est pas une intersection
            if ( precedents.size() == 1 && arcs.get(idPoint).size() == 1 ){

                //il faut réparer les connections
                Point previous = points.get(precedents.get(0));
                Point next = arcs.get(idPoint).get(0).getGoTo();
                Arc previous_to_point = arcs.get(precedents.get(0)).get(0);
                Arc point_to_next = arcs.get(idPoint).get(0);


                if ( !(previous_to_point.getNiveau().equals(point_to_next.getNiveau())) ){
                    System.out.println("Problème à propos des niveaux, " + previous_to_point.getNiveau() + " et " + point_to_next.getNiveau() );
                    cptPrblm++;
                } else if ( !(previous_to_point.getNom().equals(point_to_next.getNom())) ) {
                    System.out.println("Problème à propos des noms");
                    cptPrblm++;
                }


                //on crée le nouvel arc comme la fusion des deux précédents
                Arc newArc = new Arc(previous, next, previous_to_point.getPoids() + point_to_next.getPoids(), previous_to_point.getNiveau(), previous_to_point.getNom());

                arcs.get(precedents.get(0)).clear(); //on enlève l'arc qui mène au point inutile
                arcs.get(precedents.get(0)).add(newArc); //on ajoute le nouvel arc
                arcs.remove(idPoint); //on retire les arcs qui partent du point

                idToDelete.add(idPoint);
            }
        }
        for (Long idPoint : idToDelete ) {
            points.remove(idPoint);
        }

        System.out.println("Il y a " + cptPrblm + " problèmes");
    }


    public ArrayList<Long> pointsPrecedents(long idPointTeste){
        ArrayList<Long> res = new ArrayList<>();
        for (Long idPointDepart : arcs.keySet()) {
            for (Arc arc : arcs.get(idPointDepart)) {
                long idPointArrive = arc.getGoTo().getId();
                if ( idPointArrive == idPointTeste){
                    res.add(idPointDepart);
                }
            }
        }
        return res;
    }




}
