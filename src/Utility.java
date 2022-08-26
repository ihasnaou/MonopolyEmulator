import java.util.ArrayList;
import java.io.Serializable;

/**
* @author for m4: Ilyes, Sam
*/
public class Utility extends Property implements Serializable{ // bc utility is a property

    /**
     * A game of type game
     */
    private Game game;

    /**
     *A constructor method of utility class
     * @param name
     * @param game
     */
    public Utility(String name, Game game, String color) {
        super(150, 0, name, color); // fixed price of 150, rent depends on # of utilities
        this.game = game;
    }


    /**
     * Getter method of rent attribute
     * @return int of the rent of the utility
     */
    @Override
    public int getRent() {
        ArrayList<Integer> rolls = this.game.getNumber();

        switch(getOwner().getNumUtilities()){ // rent of utility depends on number of utilities a player already owns (switch statement)
            case 1:
                return 4 * (rolls.get(0) + rolls.get(1)); // and depending on number of utilies, rent of utility is dice roll * 4 or 10
            case 2:
                return 10 * (rolls.get(0) + rolls.get(1));
            default:
                return 0;
        }
    }
    
    @Override
    public String toXML(int count) {
        StringBuilder sb = new StringBuilder();
        sb.append("     ".repeat(Math.max(0, count)));
        sb.append("<Utility>\n");
        sb.append("     ".repeat(Math.max(0, count + 1)));
        sb.append("<name>").append(this.getName()).append("</name>\n");
        sb.append("     ".repeat(Math.max(0, count + 1)));
        sb.append("<price>").append(getPrice()).append("</price>\n");
        sb.append("     ".repeat(Math.max(0, count + 1)));
        sb.append("<color>").append(this.getColor()).append("</color>\n");
        sb.append(this.getOwner().toXML(count + 1));
        sb.append("     ".repeat(Math.max(0, count)));
        sb.append("</Utility>\n");
        return sb.toString();
    }
}
