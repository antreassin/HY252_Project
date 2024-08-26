package csd.uoc.gr.A21;

public class SensorTESTERsimple {
    public static void main(String[] lala) {
        SensorLine a = new SensorLine();
        for(int i =2; i<100;i+=3){
            Sensor S = new Sensor("SD"+(i-1),false,false);
            TemperatureSensor S1 = new TemperatureSensor("SD"+(i),false,false);
            LaserSensor S2 = new LaserSensor("SD"+(i+1),false,false,(float)i);
            if(i<=23){
                S.setViolation(true);
                S1.setViolation(true);
                S2.setViolation(true);}
            if(i==26){
                S.setViolation(true);
            }
            a.add(S);
            a.add(S1);
            a.add(S2);
        }
        Sensor S = new Sensor("SD"+100,false,false);
        a.add(S);
        System.out.println(a);
    }
}
