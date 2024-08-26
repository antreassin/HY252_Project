package csd.uoc.gr.A22;
import csd.uoc.gr.A21.SensorLine;
import org.jfugue.Player;
public class HomeSecurity extends HomeSecurityAD{
    public HomeSecurity(SensorLine[] indoor, SensorLine[] outdoor) {
        super(indoor, outdoor);
    }
    private int password = 1111;
    public boolean matchPassword(int password){
        return password == this.password;
    }
    public boolean isVioltaed(){
        boolean  ret = false;
        for(int i=0;i< indoor.length&&i<outdoor.length;i++){
            ret = ret||indoor[i].isViolated()||outdoor[i].isViolated();
        }
        return ret;
    }
    public mode getMode() {
        mode result = mode.invalid;
        for(int i=0;i<indoor.length&&i< outdoor.length;i++){
            if (indoor[i].isState(false) && outdoor[i].isState(false)) {
                result = mode.disarmed;
            }
            else if (indoor[i].isState(false) && outdoor[i].isState(true)) {
                result = mode.stay;
            }
            else if (indoor[i].isState(true) && outdoor[i].isState(true)) {
                result = mode.armed;
            }
            else {
                try {
                    throw new modeException();
                } catch (modeException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
        return result;
    }
    public void setArmed(){
        Player p = new Player();
        for(int i=0;i<indoor.length&&i< outdoor.length;i++){
            if (indoor[i].isState(false) && outdoor[i].isState(false)) {
                if (!indoor[i].isViolated() && !outdoor[i].isViolated()) {
                    p.play("[60]");
                    indoor[i].setOn(true);
                    outdoor[i].setOn(true);
                } else {
                    try {
                        throw new modeException();
                    } catch (modeException ex) {
                        System.out.println("Sensors are violated");
                        System.out.println(ex.getMessage());
                    }
                }
            } else {
                try {
                    throw new modeException();
                } catch (modeException ex) {
                    System.out.println("indoor or outdoor have wrong state");
                    System.out.println(ex.getMessage());
                }
            }
        }
    }
    public void setStay() {
        Player p = new Player();
        for (int i = 0; i < indoor.length && i < outdoor.length; i++) {
            if (getMode() == mode.disarmed) {
                if (!outdoor[i].isViolated()&&!indoor[i].isViolated()) {
                    p.play("[44]");
                    outdoor[i].setOn(true);
                } else {
                    try {
                        throw new ExternalSensorViolationException();
                    } catch (ExternalSensorViolationException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            } else {
                try {
                    throw new NotDisarmedException();
                } catch (NotDisarmedException ex) {
                    System.out.println("indoor or outdoor have wrong state");
                    System.out.println(ex.getMessage());
                }
            }
        }
    }
    public void setDisarmed(int password) {
        Player p = new Player();
        for (int i = 0; i < outdoor.length && i < indoor.length; i++) {
            if (matchPassword(password)) {
                p.play("[69]");
                indoor[i].setOn(false);
                outdoor[i].setOn(false);
            } else {
                try {
                    throw new WrongPasswordException();
                } catch (WrongPasswordException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }
    public void changePass(int pass) {
            if (getMode() == mode.disarmed) {
                    password = pass;
            } else {
                try {
                    throw new NotDisarmedException();
                } catch (NotDisarmedException ex) {
                    System.out.println("Unable to change password");
                    System.out.println(ex.getMessage());
                }
            }
        }
}