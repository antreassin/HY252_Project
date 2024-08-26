package csd.uoc.gr.A22;
import csd.uoc.gr.A21.SensorLine;
import csd.uoc.gr.A21.Sensor;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HomeSecurityMyTest {
    @Test
    public void testDisarm() throws modeException {
        SensorLine[] indoor = new SensorLine[]{new SensorLine("Int1"),new SensorLine("Int2")};
        SensorLine[] outdoor = new SensorLine[]{new SensorLine("Ext2"),new SensorLine("Ext2")};
        for(int i = 0;i< indoor.length;i++) {
            for(int j=0;j<5;j++)
            {
                Sensor s = new Sensor("S0" + i, true, false);
                indoor[i].add(s);
                outdoor[i].add(s);
            }
        }
        HomeSecurity myHome = new HomeSecurity(indoor,outdoor);
        myHome.setDisarmed(1111);
        assertEquals(myHome.getMode(),mode.disarmed);
    }
    @Test
    public void testConstruct() throws modeException {
        SensorLine[] indoor = new SensorLine[]{new SensorLine()};
        SensorLine[] outdoor = new SensorLine[]{new SensorLine()};
        for(int i = 0;i< indoor.length;i++){
            Sensor s = new Sensor("S0"+i,false,false);
            indoor[i].add(s);
            outdoor[i].add(s);
        }
        HomeSecurity myHome = new HomeSecurity(indoor,outdoor);
        assertEquals(myHome.getMode(),mode.disarmed);
    }
    @Test
    public void testChangePass(){
        SensorLine[] indoor = new SensorLine[]{new SensorLine()};
        SensorLine[] outdoor = new SensorLine[]{new SensorLine()};
        for(int i = 0;i< indoor.length;i++){
            Sensor s = new Sensor("S0"+i,false,false);
            indoor[i].add(s);
            outdoor[i].add(s);
        }
        HomeSecurity myHome = new HomeSecurity(indoor,outdoor);
        assertTrue(myHome.matchPassword(1111));
        myHome.changePass(4444);
        assertTrue(myHome.matchPassword(4444));
    }
    @Test
    public void testarm(){
        SensorLine[] indoor = new SensorLine[]{new SensorLine()};
        SensorLine[] outdoor = new SensorLine[]{new SensorLine()};
        for(int i = 0;i< indoor.length;i++){
            Sensor s = new Sensor("S0"+i,false,false);
            indoor[i].add(s);
            outdoor[i].add(s);
        }
        HomeSecurity myHome = new HomeSecurity(indoor,outdoor);
        myHome.setArmed();
        assertEquals(myHome.getMode(),mode.armed);
    }
}
