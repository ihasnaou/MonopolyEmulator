import java.io.Serializable;

/**
* @author for m4: Ilyes, Sara, Sam
*/

public class Jail extends Square implements Serializable {

    /**
     * A game of type game
     */
    private Game game;


    /**
     * A constructor of Jail class
     * @param game
     */
    public Jail(Game game) {
        super(new Property(10000, 0, "Jail","undecided" ), "undecided");
        this.game = game;
    }

    /**
     * Method that sends a player to jail if they land on jail
     * @param player as type player
     */
    @Override
    public void sendToJail(Player player){
        player.setJailed(true);
        player.setLocation(-16);
    }

    /**
     * Method player on property 
     * @param p of type player
     */
    @Override
    public void playerOnProperty(Player p) {
        game.roll_dice();
        if(game.getNumber().get(2) == 0){
            p.receiveIncome(-50);
        }
        p.setJailed(false);
    }



    /**
     * Method that converts an object to XML 
     * @param count
     * @return sb of type String
     */
    @Override
    public String toXML(int count) {
        StringBuilder sb = new StringBuilder();
        sb.append("     ".repeat(Math.max(0, count)));
        sb.append("<Jail></Jail>\n");
        return sb.toString();
    }
    
   

}
