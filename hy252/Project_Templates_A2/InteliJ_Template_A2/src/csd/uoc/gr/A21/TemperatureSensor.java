package csd.uoc.gr.A21;

public class TemperatureSensor extends Sensor{
    TemperatureSensor(String id, Boolean on, boolean violation) {
        super(id, on, violation);
    }

    public String toString() {
        String superS = super.toString();
        return superS + "  (temp.sensor)";
    }
}


