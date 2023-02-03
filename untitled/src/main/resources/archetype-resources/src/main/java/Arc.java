public class Arc {

    private Point fromTo;
    private Point goTo;
    private float poids;

    private String niveau;

    private String nom;


    private long idWay;

    public Arc(Point fromTo, Point goTo, float poids,String niveau,String nom) {
        this.fromTo = fromTo;
        this.goTo = goTo;
        this.poids = poids;
        this.niveau = niveau;
        this.nom = nom;
    }

    public Arc() {
    }

    public Point getFromTo() {
        return fromTo;
    }

    public Point getGoTo() {
        return goTo;
    }

    public float getPoids() {
        return poids;
    }

    public long getIdWay() {
        return idWay;
    }

    public String getNiveau() {
        return niveau;
    }

    public String getNom() {
        return nom;
    }

    public void setFromTo(Point fromTo) {
        this.fromTo = fromTo;
    }

    public void setGoTo(Point goTo) {
        this.goTo = goTo;
    }

    public void setPoids(float poids) {
        this.poids = poids;
    }

    public void setIdWay(long idWay) {
        this.idWay = idWay;
    }

    public void incrPoids() {
        this.poids++;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Arc arc = (Arc) o;

        if (!fromTo.equals(arc.fromTo)) return false;
        return goTo.equals(arc.goTo);
    }

    @Override
    public int hashCode() {
        int result = fromTo.hashCode();
        result = 31 * result + goTo.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Depart : " + this.getFromTo() + " , fin : " + this.getGoTo() + " poids  : " + this.getPoids() + " id : " + this.getIdWay()+" niveau : " + this.getNiveau() + " nom : " + this.getNom();
    }

}