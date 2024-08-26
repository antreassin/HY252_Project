package csd.uoc.gr.A25.creatures;

import csd.uoc.gr.A25.weapon.Lightsaber;
import csd.uoc.gr.A25.ranks;

import java.util.Random;

public class Jedi extends Humanoid implements Combatant{
    private int force_lvl;
    ranks rank;
    public Jedi(String firstName, String lastName) {
        super(firstName, lastName);
        Random r = new Random();
        int random = r.nextInt(100)+1;
        if(random<10)
            rank = ranks.YOUNGLING;
        else if(random<30)
            rank = ranks.PADAWAN;
        else if(random<70)
            rank = ranks.KNIGHT;
        else if(random<90)
            rank = ranks.MASTER;
        else if(random<100)
            rank = ranks.GRAND_MASTER;
        else System.exit(1);
    }
    int getForce_lvl(){
        return force_lvl;}
    ranks getRank(){
        return rank;}
    @Override
    public String toString() {
        return "Name: "+getCallSign()+" Rank: "+getRank()+" Weapon: "+getWeapon();
    }
    @Override
    public void attack(Object adversary) {
        if(adversary instanceof Sith||adversary instanceof BattleDroid){
            int dmg = getRank().ordinal();
            if(hasWeapon()){
                dmg+= weapon.getPower();
            }
            else dmg++;
            if(adversary instanceof Sith){
                if(((Sith)adversary).getHealth()>0){
                System.out.println(getCallsign()+" attacked:"+((Sith)adversary).getCallsign());
                ((Sith)adversary).setCondition((-1)*dmg);
                System.out.println(((Sith)adversary).getCallSign()+"'s condition: "+((Sith)adversary).getHealth());}
            }
            else{
                if(((BattleDroid)adversary).getCondition()>0){
                    System.out.println(getCallsign()+" attacked:"+((BattleDroid)adversary).getCallName());
                    ((BattleDroid)adversary).setCondition((-1)*dmg);
                    System.out.println(((BattleDroid)adversary).getCallName()+"'s condition: 0");
                    System.out.println(((BattleDroid)adversary).getCallName()+"'s condition: "+((BattleDroid)adversary).getCondition());
                }
            }
        }
    }
    @Override
    public boolean isDefeated() {
        return super.isDefeated();
    }
    @Override
    public String getCallsign() {
        return super.getCallSign();
    }
    public void setWeapon(){
        Lightsaber L = new Lightsaber();
        super.setWeapon(L);
        L.setHolder(this);
    }
}
