package csd.uoc.gr.A21;
public class Sensor {
    private String id;
    private boolean violation;
    private Boolean on;
    public Sensor(String id, Boolean on, boolean violation){
        this.id = id;
        this.violation = violation;
        this.on = on;
    }
    public String getId(){return id;}
    public boolean getViolation(){
        return violation;}
    Boolean getOn(){return on;}
    void setId(String id){this.id = id;}
    public void setViolation(boolean violation){
        this.violation = violation;}
    public void setOn(Boolean on){this.on = on;}
    public String toString(){
        return "Sensor with id="+this.id+", On state = "+this.on+", Violation state = "+this.violation;
    }
}
