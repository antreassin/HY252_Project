package csd.uoc.gr.A25.creatures;
import java.util.Objects;
public interface Combatant {
    void attack(Object adversary);
    boolean isDefeated();
    int getCondition();
    void setCondition(int condition);
    String getCallsign();
}
