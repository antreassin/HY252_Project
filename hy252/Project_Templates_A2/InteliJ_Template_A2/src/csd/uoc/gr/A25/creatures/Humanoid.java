package csd.uoc.gr.A25.creatures;
import csd.uoc.gr.A25.weapon.Lightsaber;

abstract public class Humanoid extends WeaponCarrier{
    private String firstName, lastName;
    private int health;
    public Humanoid(String firstName, String lastName,int health){
        this.firstName = firstName;
        this.lastName = lastName;
        this.health = health;
    }
    public Humanoid(String firstName, String lastName){
        this(firstName,lastName,10);
    }
    void setHealth(int health){
        if(health<=10&&health>=0){
            this.health = health;
        } else{
            try {
                throw new Exception("Invalid health, out of 0-10");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    int getHealth(){
        return health;
    }
    public void setCondition(int cond){
        this.health += cond;
        if(this.health<0){
            health = 0;
        }
    }
    public int getCondition(){
        return health;
    }
    boolean isDefeated(){
        return (health <= 0);
    }
    public String getCallSign(){
        return firstName+' '+lastName;
    }
    public void setWeapon(){
        Lightsaber L = new Lightsaber();
        super.setWeapon(L);
    }
    abstract public String toString();
}
