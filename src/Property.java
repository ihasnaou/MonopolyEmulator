import java.io.Serializable;

/**
* @author for m4: Ilyes, Sara, Sam
*/
public class Property implements Serializable {

    /**
     * Price of a property
     */
    private int price;
    /**
     * Rent of the property
     */
    private int rent;
    /**
     * Name of a property
     */
    private String name;
    /**
     * Owner of the property
     */
    private Player owner;
    /**
     * Color of the property
     */
    private String color;


    /**
     * Constructor of property class
     * @param price as an int
     * @param rent as an int
     * @param name as a String
     * @param color as a String
     */
    public Property(int price, int rent, String name, String color) {

        this.name = name;
        this.price = price;
        this.rent = rent;
        this.owner = new Player(-1, true);
        this.color = color;
    }

    /**
     * Getter method for owner attribute
     * @return
     */
    public Player getOwner() {
        return this.owner;
    }

    /**
     * Setter method for rent attribute
     * @param rent
     */
    public void setRent(int rent) {
        this.rent = rent;
    }

    /**
     * Getter method of price attribute
     * @return the price of the property
     */
    public int getPrice() {
        return this.price;
    }

    /**
     * Getter method of rent attribute
     * @return the property's rent
     */
    public int getRent() {
        return this.rent;
    }

    /**
     * Setter method of owner attribute
     * @param owner of the property
     */
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    /**
     * Offers the property to the player
     * @param player as type player
     * @return true or false whether a player wants to buy the property or not
     */
    public boolean offerBuy(Player player){
        return player.buyProperty(this);

    }

    /**
     * Getter method of color
     * @return the color as a String
     */
    public String getColor() { return this.color;}

    /**
     * Pay rent method
     * @param p as a player
     * @return true or false whether a player can afford the property or not
     */
    public boolean payRent(Player p) {
        if (p.payRent(this.getRent())) { // CANT AFFORD
            owner.receiveIncome(this.getRent());
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Converts an object to XML
     * @param count as type int
     * @return sb of type String
     */
    public String toXML(int count) {
        StringBuilder sb = new StringBuilder();
        sb.append("     ".repeat(Math.max(0, count)));
        sb.append("<Property>\n");
        sb.append("     ".repeat(Math.max(0, count + 1)));
        sb.append("<name>").append(this.name).append("</name>\n");
        sb.append("     ".repeat(Math.max(0, count + 1)));
        sb.append("<rent>").append(this.rent).append("</rent>\n");
        sb.append("     ".repeat(Math.max(0, count + 1)));
        sb.append("<price>").append(this.price).append("</price>\n");
        sb.append("     ".repeat(Math.max(0, count + 1)));
        sb.append("<color>").append(this.color).append("</color>\n");
        sb.append(this.getOwner().toXML(count + 1));
        sb.append("     ".repeat(Math.max(0, count)));
        sb.append("</Property>\n");

        return sb.toString();
    }

    /**
     * Getter method of Name
     * @return name
     */
    public String getName() {
        return name;
    }
    /**
     * Setter method of the name attribute
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter method of the color attribute
     * @param color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Setter method of the price attribute
     * @param price
     */
    public void setPrice(int price) {
        this.price = price;
    }
}
