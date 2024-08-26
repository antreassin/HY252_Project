package csd.uoc.gr.A25.creatures;

import java.util.Random;

public class MedicalDroid extends Droid{
    private int medicalSkills;
    public MedicalDroid(){
        super();
        Random random = new Random();
        medicalSkills = random.nextInt(3) + 1;
    }
    MedicalDroid(Humanoid master){
        super(master);
        Random random = new Random();
        medicalSkills = random.nextInt(3) + 1;
    }
    int getMedicalSkills(){
        return medicalSkills;
    }
    public void healMaster(){
        if(getMaster().getHealth()>0)
        {
            getMaster().setCondition(medicalSkills);
            System.out.println("5F2G healed: "+getMedicalSkills()+" units of its master "+getMaster().getCallSign());
            System.out.println(getMaster().getCallSign()+"'s condition: "+getMaster().getHealth());
        }
    }
}
