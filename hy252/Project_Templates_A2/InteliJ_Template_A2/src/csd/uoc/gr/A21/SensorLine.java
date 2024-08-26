package csd.uoc.gr.A21;
public class SensorLine {
    public SensorLine(){name = "Anonymous sensorLine";}
    public SensorLine(String name){this.name = name;}
    String name;
    public String getName(){return name;}
    private Sensor[] arrSensor = new Sensor[1000];
    private int activeCapacity = 0;
    public void add(Sensor s){
        if(activeCapacity<1000){
            arrSensor[activeCapacity]=s;
            activeCapacity++;
        }
        else System.out.println("Ανεπαρκής χώρος");
    }
    public void setOn(Boolean on){
        for(int i = 0; i<activeCapacity;i++){
            arrSensor[i].setOn(on);
        }
    }
    public boolean isState(boolean state){
        for(int i=0;i<activeCapacity;i++){
            if(arrSensor[i].getOn()!=state) return false;
        }
        return true;
    }
    public boolean isViolated(){
        boolean ret_arg = false;
        for(int i=0;i<activeCapacity;i++){
            ret_arg = ret_arg||arrSensor[i].getViolation();
        }
        return ret_arg;
    }
    public Sensor[] getSensors(){
        Sensor[] ret_arg = new Sensor[activeCapacity];
        for(int i = 0;i < activeCapacity;i++){
            ret_arg[i] = arrSensor[i];
        }
        return ret_arg;
    }
    public String toString(){
        int noViolated = 0;
        String names="";
        for(int i=0;i<activeCapacity;i++){
            if(arrSensor[i].getViolation()){
                noViolated++;
                names+=arrSensor[i].getId()+" ";
            }
        }
        return (name+"\n" +"Number of sensors="+activeCapacity+"\n" +"Violated = "+isViolated()+"\n" +"# of violated sensors="+noViolated+"\n" +"Ids of violated: "+names);
    }
}
