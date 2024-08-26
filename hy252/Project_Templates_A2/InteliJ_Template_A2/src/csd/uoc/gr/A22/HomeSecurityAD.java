package csd.uoc.gr.A22;

import csd.uoc.gr.A21.SensorLine;

/**
 * this is a simulation of HomesecurityAD
 */
abstract public class HomeSecurityAD {

    SensorLine[] indoor;
    SensorLine[] outdoor;

    /**
     * Constructor
     * @param indoor SensorLine
     * @param outdoor SensorLine
     */
    HomeSecurityAD(SensorLine[] indoor, SensorLine[] outdoor){
        this.indoor = indoor;
        this.outdoor = outdoor;
    }
    /**
     * @param password
     * Pre-condition: password should be equal with private password
     * @return true if equal
     */
    abstract boolean matchPassword(int password);
    /**
     * Observer
     * @return enum mode
     * @throws modeException wrong mode
     */
    abstract mode getMode() throws modeException;
    /**
     * Transformer
     * @throws modeException
     * Pre-condition: sensors are off and not violated
     * Post-condition: sets Armed mode
     */

    abstract void setArmed() throws modeException;
    /**
     * Transformer
     * @throws ExternalSensorViolationException when sens are violated
     * @throws NotDisarmedException
     * Pre-condition: mode = disarmed and outdoor sens are not violated
     * Post-condition: sets Stay mode
     */
    abstract void setStay() throws ExternalSensorViolationException, NotDisarmedException;
    /**
     *Transformer
     * @param password int pass for disarm
     * @throws WrongPasswordException
     * Pre-condition: input pass matches password
     * Post-condition: sets Disarmed
     */
    abstract void setDisarmed(int password) throws WrongPasswordException;

    /**
     *Transformer
     * @param password int new pass
     * @throws NotDisarmedException
     * Pre-condition: mode = Disarmed
     * Post-condition: changes private password
     */
    abstract void changePass(int password) throws NotDisarmedException;
}