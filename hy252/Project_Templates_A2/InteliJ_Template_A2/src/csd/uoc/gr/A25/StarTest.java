package csd.uoc.gr.A25;
import csd.uoc.gr.A25.creatures.BattleDroid;
import csd.uoc.gr.A25.creatures.Jedi;
import csd.uoc.gr.A25.creatures.MedicalDroid;
import csd.uoc.gr.A25.creatures.Sith;
import csd.uoc.gr.A25.weapon.Lightsaber;
import csd.uoc.gr.A25.weapon.Weapon;

import java.util.Random;
/*
*   STO COND APLA KANW RETURN TO GET HEALTH*/
public class StarTest {
    public static void main(String[] args) {
        Jedi obiwan = new Jedi("Obiwan", "Kenobi");
        obiwan.setWeapon();
        System.out.println(obiwan);
        Sith vader = new Sith("Vader");
        vader.setWeapon(new Lightsaber());
        System.out.println(vader);
        MedicalDroid docDroid = new MedicalDroid();
        docDroid.setMaster(vader);
        System.out.println(docDroid);
        BattleDroid battleDroid = new BattleDroid();
        battleDroid.setMaster(obiwan);
        System.out.println(battleDroid);
        System.out.println();
//battle between jedi and sith
        try {
            while (!obiwan.isDefeated() && !vader.isDefeated()) {
                if (new Random().nextBoolean()) {
                    obiwan.attack(vader);
                    if (!vader.isDefeated()) {
                        vader.attack(obiwan);
                    }
                } else {
                    vader.attack(obiwan);
                    if (!obiwan.isDefeated()) {
                        obiwan.attack(vader);
                    }
                }
                if (!docDroid.isFree()) {
                    docDroid.healMaster();
                }
//random turn
                if (!battleDroid.isFree() && !vader.isDefeated()) {
                    battleDroid.protectMasterFrom(vader);
                }
                System.out.println(); //print a new line for a new round
            }
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            System.out.print(obiwan.isDefeated() ? vader.getCallSign() : obiwan.getCallSign());
            System.out.println(" won!");
        }
    }

}