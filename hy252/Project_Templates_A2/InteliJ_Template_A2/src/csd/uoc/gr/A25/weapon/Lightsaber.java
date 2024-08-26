package csd.uoc.gr.A25.weapon;

import csd.uoc.gr.A25.weapon.Weapon;
import csd.uoc.gr.A25.color;
import csd.uoc.gr.A25.creatures.Jedi;
import csd.uoc.gr.A25.creatures.Sith;

public class Lightsaber extends Weapon {
    color saberColor;
    public Lightsaber(){
        super();
    }
    color getColor(){
        return saberColor;
    }
    public void setHolder(Object holder){
        super.setHolder(holder);
        if(holder instanceof Jedi){
            saberColor = color.BLUE;
        } else if(holder instanceof Sith){
            saberColor = color.RED;
        } else{
            try {
                throw new Exception("Obj not WeaponCarrier");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

    }
    public String toString() {
        return "Color: "+saberColor+" Power: "+super.getPower();
    }
}
