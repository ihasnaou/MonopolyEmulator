import java.io.Serializable;
/**
* @author for m4: Ilyes, Sam, Sara
*/

public class Square {
    /**
     * Owned as type boolean
     */
    private boolean owned;
    /**
     * Property of type property
     */
    private Property property;
    /**
     * Owned in squares
     */
    private int ownedInSquare = 0;
    /**
     * Color of square
     */
    private String color;


    /**
     * Pay rent
     * @param player of type player
     * @return True or false whether a player rented a property or not
     */
    public boolean payRent(Player player){

        return this.property.payRent(player);
    }

    /**
     * Getter method of property attribute
     * @return the property
     */
    public Property getProperty() {
        return this.property;
    }

    /**
     * Setter method of property attribute
     * @return the property
     */
    public void setProperty(Property property) {
        this.property = property;
    } // new setter

    /**
     * If a player is on property
     * @param p of type player
     */
    public void playerOnProperty(Player p) {}

    /**
     * Buy property method
     * @param p of type property
     * @return true or false whether it's available to buy or not
     */
    public boolean buyProperty(Player p) {
        if (!owned) {
            if (p.getBalance() > this.property.getPrice()) {
                owned = true;
                ownedInSquare ++;
                return this.property.offerBuy(p);
            }
        }
        else if (this.property.getOwner().equals(p)) {
            if (p.getBalance() > this.property.getPrice()) {
                ownedInSquare ++;
                this.property.setRent(this.property.getRent() * 2);
                return true;
            }
        }
        return false;
    }

    /**
     * Constructor of square class
     * @param property of type property
     * @param color of type String
     */
    public Square(Property property, String color){
        this.property = property;
        this.owned = false;
        this.color = color;
    }

    /**
     * Getter method of Owner attribute
     * @return the property's owner
     */
    public Player getOwner() {
        return this.property.getOwner();
    }
  
    /**
     * Setter method of owned attribute
     * @param owned
     */
    public void setOwned(Boolean owned){
        this.owned = owned;
    }
    
    /**
     * Setter method of Owned in Square attribute
     * @param ownedInSquares
     */
    public void setOwnedInSquare(int ownedInSquares){
        this.ownedInSquare = ownedInSquares;
    }
    /**
     * Setter method of color attribute
     * @param color
     */
    public void setColor(String color){
        this.color = color;
    }

    /**
     * Method to send players to jail
     * @param player as type player
     */
    public void sendToJail(Player player){ }

    /**
     * Getter method of owned in square
     * @return owned in square
     */
    public int getOwnedInSquare() {
        return ownedInSquare;
    }

    /**
     * Getter method of color attribute
     * @return color of square
     */
    public String getColor() {
        return this.color;
    }

    /**
     * Checks to see if a square is owned or not
     * @return true or false based on whether it's owned or not
     */
    public boolean isOwned() {
        return owned;
    }

    /**
     * Takes an object and converts it to XML
     * @param count as an int
     * @return sb as type String
     */
    public String toXML(int count) {
        StringBuilder sb = new StringBuilder();
        sb.append("     ".repeat(Math.max(0, count)));
        sb.append("<Square>\n");
        sb.append("     ".repeat(Math.max(0, count + 1)));
        sb.append("<owned>").append(this.owned).append("</owned>\n");
        sb.append("     ".repeat(Math.max(0, count + 1)));
        sb.append("<ownedInSquare>").append(this.ownedInSquare).append("</ownedInSquare>\n");
        sb.append("     ".repeat(Math.max(0, count + 1)));
        sb.append("<color>").append(this.color).append("</color>\n");
        sb.append(this.getProperty().toXML(count + 1));
        sb.append("     ".repeat(Math.max(0, count)));
        sb.append("</Square>\n");

        return sb.toString();
    }
}
