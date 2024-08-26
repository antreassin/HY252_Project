package csd.uoc.gr.A25.creatures;
import csd.uoc.gr.A25.weapon.Weapon;

abstract public class WeaponCarrier {
    Weapon weapon;
    Weapon getWeapon(){
        return weapon;
    };
    boolean hasWeapon(){
        return weapon != null;
    }
    public void setWeapon(Weapon weapon){
        this.weapon = weapon;
    }
}
