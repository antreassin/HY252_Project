package csd.uoc.gr.A25.creatures;

public class BattleDroid extends Droid{
    int condition;
    public BattleDroid(){
        super();
        condition = 10;
    }
    BattleDroid(Humanoid master){
        super(master);
        condition = 10;
    }
    int getCondition(){
        return condition;
    }
    void setCondition(int condition){
        this.condition+=condition;
        if(this.condition < 0){
            this.condition = 0;
        }
    }
    String getCallName(){
        return getName();
    }
    public void attack(Object adversary) {
            if(adversary instanceof Sith){
                System.out.println(getCallName()+"was just protecting its master "+getMaster().getCallSign()+", attacked:"+((Sith)adversary).getCallsign());
                ((Sith)adversary).setCondition(-1);
                System.out.println(((Sith)adversary).getCallSign()+"'s condition: "+((Sith)adversary).getHealth());
            }
            else if(adversary instanceof BattleDroid){
                System.out.println(getCallName()+"was just protecting its master "+getMaster().getCallSign()+", attacked:"+((BattleDroid)adversary).getCallName());
                ((BattleDroid)adversary).setCondition((-1));
                System.out.println(((BattleDroid)adversary).getCallName()+"'s condition: "+((BattleDroid)adversary).getCondition());
            }
            else if(adversary instanceof Jedi){
                System.out.println(getCallName()+"was just protecting its master "+getMaster().getCallSign()+", attacked:"+((Jedi)adversary).getCallSign());
                ((Jedi)adversary).setCondition((-1));
                System.out.println(((Jedi)adversary).getCallSign()+"'s condition: "+((Jedi)adversary).getHealth());
            }
            else{
                try {
                    throw new Exception("Exception!!");
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
    }
    boolean isDefeated(){
        return condition <= 0;
    }
    public void protectMasterFrom(Object adversary){
        if(master!=null&&!master.isDefeated())
        {
            if(!(adversary.getClass() == master.getClass())){
                attack(adversary);
            }
        }else{
            try {
                throw new Exception("Exception@protectMaster!!");
            } catch (Exception ex) {}
        }
    }
}