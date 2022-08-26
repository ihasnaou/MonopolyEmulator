import java.io.Serializable;

/**
* @author for m4: Ilyes, Sam
*/
public class RailRoad extends Property implements Serializable{

    /**
     * Constructor of rail road class
     * @param name of type String
     * @param color of type String
     */
    public RailRoad(String name, String color) {
        super(200,0,name, color); // price of each rail road is 200
    }


    /**
     * Getter method of rent
     * @return rent as an int
     */
    @Override
    public int getRent() {
        switch(getOwner().getNumRailroads()){ // depending on number of railroads owned by a player rent value changes
            case 1:
                return 25;
            case 2:
                return 50;
            case 3:
                return 100;
            case 4:
                return 200;
            default:
                return 0;
        }
    }

    /**
     * Takes an object and converts it to XML
     * @param count as type int
     * @return sb of type String
     */
    @Override
    public String toXML(int count) {
        StringBuilder sb = new StringBuilder();
        sb.append("     ".repeat(Math.max(0, count)));
        sb.append("<RailRoad>\n");
        sb.append("     ".repeat(Math.max(0, count + 1)));
        sb.append("<name>").append(this.getName()).append("</name>\n");
        sb.append("     ".repeat(Math.max(0, count + 1)));
        sb.append("<price>").append(getPrice()).append("</price>\n");
        sb.append("     ".repeat(Math.max(0, count + 1)));
        sb.append("<color>").append(this.getColor()).append("</color>\n");
        sb.append(this.getOwner().toXML(count + 1));
        sb.append("     ".repeat(Math.max(0, count)));
        sb.append("</RailRoad>\n");
        return sb.toString();
    }
}
