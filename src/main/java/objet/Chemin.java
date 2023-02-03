package objet;

import java.util.ArrayList;

public class Chemin {
    private String latitude;
    private String longitude;
    private String altitude;
    private String geometry;
    private ArrayList<Point> coordinates;
    private String id;
    private String pisteDifficulty;
    private String pisteType;
    private String type;
    private String access;
    private String area;
    private String leisure;
    private String name;
    private String route;
    private String note;
    private String source;
    private String pisteGrooming;
    private String website;
    private String fixme;
    private String lit;
    private String comment;
    private String pisteRef;
    private String bridge;
    private String layer;
    private String pisteName;
    private String pisteAltName;
    private String highway;
    private String sac_scale;
    private String service;
    private String pisteOldName;
    private String tunnel;
    private String abandonned;
    private String material;
    private String old_name;
    private String pisteOld_ref;
    private String trail_visibility;
    private String oneway;
    private String tracktype;
    private String pisteAbandoned;
    private String closed;
    private String pistePatrolled;
    private String cyclewayRight;
    private String description;
    private String lane_markings;
    private String surface;
    private String short_name;
    private String pisteOneway;
    private String onewayTrack;
    private String lanes;
    private String railway;
    private String eleLocal;
    private String bicycle;
    private String foot;
    private String sidewalk;
    private String fixme2;
    private String structure;
    private String relations;
    private String sport;


    /*public Chemin(ArrayList<String> ligne) {
        
        this.latitude = ligne.get(0);
        this.longitude = ligne.get(1);
        this.altitude = ligne.get(2);
        this.geometry = ligne.get(3);

        this.coordinates = this.creerChemin(ligne.get(4));

        this.id = ligne.get(5);
        this.pisteDifficulty = ligne.get(6);
        this.pisteType = ligne.get(7);
        this.type = ligne.get(8);
        this.access = ligne.get(9);
        this.area = ligne.get(10);
        this.leisure = ligne.get(11);
        this.name = ligne.get(12);
        this.route = ligne.get(13);
        this.note = ligne.get(13);
        this.source = ligne.get(14);
        this.pisteGrooming = ligne.get(15);
        this.website = ligne.get(16);
        this.fixme = ligne.get(17);
        this.lit = ligne.get(18);
        this.comment = ligne.get(19);
        this.pisteRef = ligne.get(20);
        this.bridge = ligne.get(21);
        this.layer = ligne.get(22);
        this.pisteName = ligne.get(23);
        this.pisteAltName = ligne.get(24);
        this.highway = ligne.get(25);
        this.sac_scale = ligne.get(26);
        this.service = ligne.get(27);
        this.pisteOldName = ligne.get(28);
        this.tunnel = ligne.get(29);
        this.abandonned = ligne.get(30);
        this.material = ligne.get(31);
        this.old_name = ligne.get(32);
        this.pisteOld_ref = ligne.get(33);
        this.trail_visibility = ligne.get(34);
        this.oneway = ligne.get(35);
        this.tracktype = ligne.get(36);
        this.pisteAbandoned = ligne.get(37);
        this.closed = ligne.get(38);
        this.pistePatrolled = ligne.get(39);
        this.cyclewayRight = ligne.get(40);
        this.description = ligne.get(41);
        this.lane_markings = ligne.get(42);
        this.surface = ligne.get(43);
        this.short_name = ligne.get(44);
        this.pisteOneway = ligne.get(45);
        this.onewayTrack = ligne.get(46);
        this.lanes = ligne.get(47);
        this.railway = ligne.get(48);
        this.eleLocal = ligne.get(49);
        this.bicycle = ligne.get(50);
        this.foot = ligne.get(51);
        this.sidewalk = ligne.get(52);
        this.fixme2 = ligne.get(53);
        this.structure = ligne.get(54);
        this.relations = ligne.get(55);
        this.sport = ligne.get(56);
    }*/

    public Chemin(ArrayList<String> ligne){

        this.coordinates = this.creerChemin(ligne.get(4));
        this.pisteDifficulty = ligne.get(6);
        if(ligne.get(12).isEmpty()){
            this.name = ligne.get(5);
        }
        else{
            this.name = ligne.get(12);
        }
    }

    private ArrayList<Point> creerChemin(String s) {
        ArrayList<Point> res = new ArrayList<>();

        String[] split = s.split(",");

        for(int i =0; i<split.length;i+=2){
            Point p = new Point(Double.parseDouble(split[i]),Double.parseDouble(split[i+1]));
            res.add(p);
        }

        return res;
    }

    public ArrayList<Point> getCoordinates() {
        return coordinates;
    }

    public String getPisteDifficulty() {return pisteDifficulty;}

    public String getName() {return name;}
}
