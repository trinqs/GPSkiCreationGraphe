package objet;

import objet.Arc;
import objet.Chemin;
import objet.Point;
import traitementGraphe.CreationGraph;

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
        ArrayList<Arc> res_Arcs = creationGraph.getAllArc(chemins,pointSet);
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
        int cptNotInter=0;

        ArrayList<Long> idToDelete = new ArrayList<>();

        for (Long idPoint : points.keySet()) {

            Point pointTest = points.get(idPoint);

            ArrayList<Long> idPointsPrecedents = pointsPrecedents(idPoint);

            //Si c'est un point qui n'est pas une intersection ou un départ ou une fin
            if ( idPointsPrecedents.size() == 1 && arcs.get(idPoint).size() == 1 ){

                cptNotInter++;

                //Quelques varaibles pour limiter les get et essayer de rendre le code plus lisible
                Point previous = points.get(idPointsPrecedents.get(0));
                Point next = arcs.get(idPoint).get(0).getGoTo();
                Arc previous_to_point = arcs.get(idPointsPrecedents.get(0)).get(0);
                Arc point_to_next = arcs.get(idPoint).get(0);


                System.out.println("\n\nLe point " + pointTest + "a comme \nprecedents :" + previous + "\nsuivants : " + next);

                if ( !(previous_to_point.getNiveau().equals(point_to_next.getNiveau())) ){
                    System.out.println("Problème à propos des niveaux, " + previous_to_point.getNiveau() + " et " + point_to_next.getNiveau() );
                    cptPrblm++;
                } else if ( !(previous_to_point.getNom().equals(point_to_next.getNom())) ) {
                    System.out.println("Problème à propos des noms");
                    cptPrblm++;
                } else {

                    //on crée le nouvel arc comme la fusion des deux précédents, qui part du point precedent et qui va jusqu'au point suivant
                    Arc newArc = new Arc(previous, next, previous_to_point.getPoids() + point_to_next.getPoids(), previous_to_point.getNiveau(), previous_to_point.getNom());

                    //on récupère l'index de l'arc qu'il faut supprimer parmi ceux partant du point précédent
                    int indexArcDelete=-1;
                    for (Arc arc : arcs.get(idPointsPrecedents.get(0))){
                        if (arc.getGoTo().equals(pointTest)){
                            indexArcDelete = arcs.get(idPointsPrecedents.get(0)).indexOf(arc);
                        }
                    }
                    arcs.get(idPointsPrecedents.get(0)).remove(indexArcDelete);//on delete le bon arc
                    arcs.get(idPointsPrecedents.get(0)).add(newArc); //on ajoute le nouvel arc
                    arcs.remove(idPoint); //on retire les arcs qui partent du point à supprimer

                    idToDelete.add(idPoint);//on ajoute le point à la liste de ceux à supprimer
                }
            }
        }
        //on supprime à la fin de l'opération pour éviter tout null pointer exception
        for (Long idPoint : idToDelete ) {
            points.remove(idPoint);
        }

        System.out.println("\nIl y a "+ cptNotInter+" non intersections");
        System.out.println("Il y a " + cptPrblm + " problèmes");
    }


    public ArrayList<Long> pointsPrecedents(long idPointTeste){
        ArrayList<Long> resultat = new ArrayList<>();
        for (Long idPointDepart : arcs.keySet()) {
            for (Arc arc : arcs.get(idPointDepart)) {
                long idPointArrive = arc.getGoTo().getId();
                if ( idPointArrive == idPointTeste){
                    resultat.add(idPointDepart);
                }
            }
        }
        //Liste des id des points precedents
        return resultat;
    }

    public void largeur(){
        ArrayList<Point> fileAVisiter = new ArrayList<>();
        ArrayList<Point> dejaVisite = new ArrayList<>();
        int cpt=0;
        fileAVisiter.add(points.get(points.keySet().iterator().next()));

        while (!fileAVisiter.isEmpty()){
            Point point = fileAVisiter.get(0);
            fileAVisiter.remove(0);
            dejaVisite.add(point);
            cpt++;
            System.out.println("point au coordonnée : " + point.toString() + " et " + cpt + " points");

            if (arcs.get(point.getId()) != null){
                for (Arc arc : arcs.get(point.getId())){
                    if (!dejaVisite.contains(arc.getGoTo()) && !fileAVisiter.contains(arc.getGoTo()))
                        fileAVisiter.add(arc.getGoTo());
                }
            }else{
                System.out.println("point problèmatique " + point);
            }

        }
    }




}
