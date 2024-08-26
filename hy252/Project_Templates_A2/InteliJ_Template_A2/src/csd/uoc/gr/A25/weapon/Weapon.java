package csd.uoc.gr.A25.weapon;

import csd.uoc.gr.A25.creatures.WeaponCarrier;

import java.util.Random;

abstract public class Weapon{
    private int power;
    WeaponCarrier holder;
    Weapon(){
        Random random = new Random();
        power = random.nextInt(5) + 1;
        holder = null;
    }
    void setHolder(Object holder){
        if(holder instanceof WeaponCarrier){
            this.holder = (WeaponCarrier)holder;
        }
    }
    WeaponCarrier getHolder(){
        return holder;
    }
    public int getPower(){
        return power;
    }
    public abstract String toString();
}
