import jdk.dynalink.beans.StaticClass;

public class Point {

    private static long idCpt=0;

    private double latitude;
    private double longitude;

    private long id;
//TODO Faire un id au point sans changer le hashcode pour pouvoir stocké l'id du point dans l'arc pour le csv
    public Point(double latitude,double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
        this.id = idCpt;
        idCpt+=1;
    }

    public Point(String s, String s1) {

    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public long getId(){ return this.id;}

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (Double.compare(point.latitude, latitude) != 0) return false;
        return Double.compare(point.longitude, longitude) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(latitude);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString(){
        return ""+this.getLongitude()+","+this.getLatitude();
    }
}