import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
* @author for m4: Ilyes
*/

public class JailTest {

    /**
    * Method that tests the basic functions of jail
    */
    Jail jail;
    /**
    * Method that tests the basic functions of game
    */
    Game game;

    /**
    * Method that tests the basic jail functions
    */
    @org.junit.Test
    public void testJail() {
        game = new Game(1, 0);
        jail = new Jail(game);
        game.getPlayers().get(0).setLocation(24);
        jail.sendToJail(game.getPlayers().get(0));

        assertEquals(8, game.getPlayers().get(0).getLocation());
        assertEquals(true, game.getPlayers().get(0).getJailed());

        jail.playerOnProperty(game.getPlayers().get(0));

        assertNotEquals(8, game.getPlayers().get(0));
        assertEquals(false, game.getPlayers().get(0).getJailed());
    }
    
     /**
     * Method that tests the Jail to XML functionality
     */
    @org.junit.Test
    public void testJailToXML() {
        game = new Game(1, 0);
        jail = new Jail(game);

        System.out.println(jail.toXML(1));
    }
}
