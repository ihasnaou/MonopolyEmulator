import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

/**
* @author for m4: Ilyes, Sara
*/
public class GameTest {
    /**
    * Game object to be tested
    */
    Game game;

    /**
    * Method that tests the move player function in game
    */
    @org.junit.Test
    public void testMovePlayer() {
        game = new Game(4, 0);

        game.movePlayer(game.getPlayers().get(0));

        assertEquals(game.getNumber().get(0) + game.getNumber().get(1), game.getPlayers().get(0).getLocation());

    }

    /**
    * Method that tests the lose player function in game
    */
    @org.junit.Test
    public void testLosePlayer() {
        game = new Game(4, 0);

        Player p3 = game.getPlayers().get(2);

        assertEquals(4, game.getPlayers().size());

        game.losePlayer(game.getPlayers().get(2));

        assertEquals(3, game.getPlayers().size());
        assertFalse(game.getPlayers().contains(p3));
    }

    /**
    * Method that tests the buy and rent function in game
    */
    @org.junit.Test
    public void testBuyAndRent() {
        game = new Game(4, 0);

        game.movePlayer(game.getPlayers().get(0));

        assertEquals(game.getNumber().get(0) + game.getNumber().get(1), game.getPlayers().get(0).getLocation());

        game.getPlayers().get(1).setLocation(1);

        game.buy(game.getPlayers().get(1));

        assertEquals(game.getPlayers().get(1), game.getSquares().get(1).getOwner());

        assertEquals(1400, game.getPlayers().get(1).getBalance());

        game.getPlayers().get(2).setLocation(1);

        game.rent(game.getPlayers().get(2));

        assertEquals(1450, game.getPlayers().get(1).getBalance());

        assertEquals(1650, game.getPlayers().get(2).getBalance());
    }

    /**
    * Method that tests the functionallity of jail in game
    */
    @org.junit.Test
    public void testJail() {
        game = new Game(4, 0);

        game.getPlayers().get(0).setLocation(24);

        game.jailPlayer(game.getPlayers().get(0));

        assertTrue(game.getPlayers().get(0).getJailed());

        assertEquals(8, game.getPlayers().get(0).getLocation());

        game.breakOut(game.getPlayers().get(0));

        assertFalse(game.getPlayers().get(0).getJailed());
    }
    
    /**
     * Method that tests the game to XML functionality
     */
    @org.junit.Test
    public void testGameToXML() {
        game = new Game(1, 0);

        System.out.println(game.toXML());
    }

    /**
     * Method that tests the save and load functionality
     * @throws Exception
     */
    @org.junit.Test
    public void testSaveLoad() throws Exception {
        game = new Game(2,0);

        System.out.println(game.toXML());

        game.saveGame("save.xml");

    }
    
    /**
     * Method that tests the read SAX
     * @throws Exception
     */
    @Test
    public void testReadSax() throws Exception {
        game = new Game(2,0);
        game.saveGame("save.xml");
        File file = new File("save.xml");
        Game.readSAX(file);

    }

}
