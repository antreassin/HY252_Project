package csd.uoc.gr.A25.creatures;

import csd.uoc.gr.A25.weapon.Lightsaber;

public class Sith extends Humanoid implements Combatant{
    public Sith(String lastName) {
        super("Darth", lastName);
    }

    @Override
    public String toString() {
        return "Name: "+getCallsign()+" Weapon: "+weapon.getPower();
    }

    @Override
    public void attack(Object adversary) {
        if(adversary instanceof Jedi||adversary instanceof BattleDroid){
            int dmg = 1;
            if(hasWeapon()){
                dmg+= weapon.getPower();
            }
            if(adversary instanceof Jedi){
                if(((Jedi)adversary).getHealth()>0) {
                    System.out.println(getCallsign() + " attacked:" + ((Jedi) adversary).getCallsign());
                    ((Jedi) adversary).setCondition((-1) * dmg);
                    System.out.println(((Jedi) adversary).getCallSign() + "'s condition: " + ((Jedi) adversary).getHealth());
                }
            }
            else{
                if(((BattleDroid)adversary).getCondition()>0) {
                    System.out.println(getCallsign() + " attacked:" + ((BattleDroid) adversary).getCallName());
                    ((BattleDroid) adversary).setCondition((-1) * dmg);
                    System.out.println(((BattleDroid) adversary).getCallName() + "'s condition: " + ((BattleDroid) adversary).getCondition());

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
