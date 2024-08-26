package csd.uoc.gr.A21;

public class LaserSensor extends Sensor{
    private float range;
    LaserSensor(String id, Boolean on, boolean violation, float range){
        super(id, on, violation);
        this.range = range;
    }
    public String toString(){
        String superS = super.toString();
        return superS+", range = "+range+",  (laser sensor)";
    }
}
