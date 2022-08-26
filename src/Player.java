import java.util.ArrayList;
import java.io.Serializable;

/**
* @author for m4: Ilyes, Sam
*/
public class Player implements Serializable{

    /**
     * ID of player
     */
    private int ID;
    /**
     * Location of player
     */
    private int location;
    /**
     * Player's money balance
     */
    private int balance;
    /**
     * ArrayList of properties that are bought properties
     */
    private ArrayList<Property> boughtProperties;
    /**
     * True or false based on whether the player is jailed or not
     */
    private boolean jailed;
    /**
     * Number of turns a player spends in jail
     */
    private int turnsInJail = -1;
    /**
     * true or false based on if a player is an AI
     */
    private boolean AI;


    /**
     * Custructor of player class
     * @param ID as an int
     * @param AI as a boolean value
     */
    public Player(int ID, boolean AI){
        this.ID = ID;
        this.location = 0;
        this.balance = 1700;
        this.boughtProperties = new ArrayList<>();
        this.jailed = false;
        this.turnsInJail = 0;
        this.AI = AI;
    }

    /**
     * Checks to see if a player is an AI
     * @return true or false
     */
    public boolean isAI() { return AI; }

    /**
     * Getter method of jailed attribute
     * @return true or false whether or not a player is jailed
     */
    public boolean getJailed(){
        return this.jailed;
    }

    /**
     * Setter method of jailed attribute
     * @param status of type boolean
     */
    public void setJailed(boolean status) {
        this.jailed = status;
    }

    /**
     * Method to pay rent based off of rent price
     * @param rentValue of type int
     * @return true or false
     */
    public boolean payRent(int rentValue){
        if (this.getBalance() < rentValue) {
            return false;
        }
        else {
            this.balance -= rentValue;
            return true;
        }
    }

    /**
     * Getter method turns in jail attribute
     * @return int value of turns in jail
     */
    public int getTurnsInJail() {
        return turnsInJail;
    }

    /**
     * Setter method of turns in jail
     * @param turnsInJail as an int value
     */
    public void setTurnsInJail(int turnsInJail) {
        this.turnsInJail = turnsInJail;
    }

    /**
     * Method that manages the players income
     * @param income as an int
     */
    public void receiveIncome(int income) {
        this.balance += income;
    }

    /**
     * Getter method of bought properties attribute
     * @return arrayList of bought properties
     */
    public ArrayList<Property> getBoughtProperties() {
        return boughtProperties;
    }

    /**
     * Method to buy property
     * @param property of type property
     * @return true or false whether the player has enough money to buy the property
     */
    public boolean buyProperty(Property property){
        if (this.balance < property.getPrice()) {
            return false;
        }
        else {
            this.boughtProperties.add(property);
            this.balance -= property.getPrice();
            property.setOwner(this);
            return true;
        }

    }

    /**
     * Getter method of number of rail roads attribute
     * @return the number of rail roads
     */
    public int getNumRailroads(){ //iterates thru players property to see if property is instance of utility or railroad
        int numRailroads = 0;
        for(Property p : this.boughtProperties){
            if(p instanceof RailRoad){
                numRailroads++;
            }
        }

        return numRailroads;
    }

    /**
     * Getter method of number of utilities attributes
     * @return the number of number of utilities
     */
    public int getNumUtilities() {
        int numUtilities = 0;
        for(Property p : this.boughtProperties){
            if(p instanceof Utility){
                numUtilities++;
            }
        }
        return numUtilities;
    }

    /**
     * Setter method of location attribute
     * @param diceRoll as an int
     */
    public void setLocation(int diceRoll) {
        int oldLocation = this.location;
        this.location = (this.location + diceRoll) % 32;
        if ((this.location < oldLocation) && (this.location != 12)) {
            this.receiveIncome(200);
        }
    }

    /**
     * Getter method of number of properties by color attribute
     * @param color as a String
     * @return number as an int
     */
    public int getNbOfPropertiesByColor(String color) {
        int nb = 0;
        for (Property p : boughtProperties) {
            if (p.getColor().equals(color)) {
                nb ++;
            }
        }
        return nb;
    }

    /**
     * Getter method of location attribute
     * @return the players location
     */
    public int getLocation() {
        return this.location;
    }


    /**
     * Getter method of the player's balance
     * @return the player's balance
     */
    public int getBalance() {
        return this.balance;
    }

    /**
     * Getter method of the ID attribute
     * @return the ID
     */
    public int getID() {
        return this.ID;
    }

    /**
     * Method that converts object to XML
     * @param count of type int
     * @return sb of type String
     */
    public String toXML(int count) {
        StringBuilder sb = new StringBuilder();
        sb.append("     ".repeat(Math.max(0, count)));
        sb.append("<Player>\n");
        sb.append("     ".repeat(Math.max(0, count + 1)));
        sb.append("<ID>").append(this.getID()).append("</ID>\n");
        sb.append("     ".repeat(Math.max(0, count + 1)));
        sb.append("<location>").append(this.getLocation()).append("</location>\n");
        sb.append("     ".repeat(Math.max(0, count + 1)));
        sb.append("<balance>").append(this.getBalance()).append("</balance>\n");
        sb.append("     ".repeat(Math.max(0, count + 1)));
        sb.append("<jailed>").append(this.jailed).append("</jailed>\n");
        sb.append("     ".repeat(Math.max(0, count + 1)));
        sb.append("<turnsInJail>").append(this.turnsInJail).append("</turnsInJail>\n");
        sb.append("     ".repeat(Math.max(0, count + 1)));
        sb.append("<AI>").append(this.AI).append("</AI>\n");
        sb.append("     ".repeat(Math.max(0, count)));
        sb.append("</Player>\n");

        return sb.toString();
    }

    /**
     * Setter method of ID
     * @param ID as int
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Setter method of balance
     * @param balance as int
     */
    public void setBalance(int balance) {
        this.balance = balance;
    }

    /**
     * Setter method of location
     * @param location as type int
     */
    public void makeLocation(int location) {
        this.location = location;
    }

    /**
     * Setter method of the AI
     * @param AI of type boolean
     */
    public void setAI(boolean AI){
        this.AI = AI;
    }

    public boolean getAI() {
        return this.AI;
    }
}
