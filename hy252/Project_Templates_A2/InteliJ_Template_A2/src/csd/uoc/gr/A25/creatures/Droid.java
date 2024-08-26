package csd.uoc.gr.A25.creatures;
import csd.uoc.gr.A25.creatures.Humanoid;
import java.util.Random;
abstract public class Droid {
    private String name;
    Humanoid master;
    String availableCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    Droid(){
        Random random = new Random();
        StringBuilder randomString = new StringBuilder();
        for(int i =0;i<4;i++){
            int randomIndex = random.nextInt(availableCharacters.length());
            char randomChar = availableCharacters.charAt(randomIndex);
            randomString.append(randomChar);
        }
        this.name = randomString.toString();
        master = null;
    }
    Droid(Humanoid master){
        this();
        this.master = master;
    }
    public boolean isFree(){
        if(master == null){
            return true;
        }
        else{
            if(master.getHealth()==0)
                return true;
        }
        return false;
    }
    public void setMaster(Object master){
        if(isFree()) {
            if (!(master instanceof Droid))
                this.master = (Humanoid) master;
            else {
                try {
                    throw new Exception("Obj not Humanoid");
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
        else{
            try {
                throw new Exception("Humanoid is not free");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    Humanoid getMaster(){
        return master;
    }
    String getName(){
        return name;
    }
    public String toString(){
        return "Name: "+name+" Humanoid master "+master.getCallSign();
    }

}
