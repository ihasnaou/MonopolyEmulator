import static org.junit.Assert.assertEquals;

/**
* @author for m4: Ilyes
*/
public class SquareTest {

    /**
     * Player of type player
     */
    Player player1;
    /**
     * a square of type square
     */
    Square square;

    /**
     * Tests if buy property method works as intended
     */
    @org.junit.Test
    public void testBuyProperty() {
        player1 = new Player(1, false);

        square = new Square(new Property(200, 50, "property", "red"), "red");

        assertEquals(0, square.getOwnedInSquare());
        assertEquals(false, square.isOwned());

        square.buyProperty(player1);

        assertEquals(true, square.isOwned());
        assertEquals(1, square.getOwnedInSquare());
        assertEquals(50, square.getProperty().getRent());

        square.buyProperty(player1);

        assertEquals(2, square.getOwnedInSquare());
        assertEquals(100, square.getProperty().getRent());
    }
    
    @org.junit.Test
    public void testSquareToXML() {
        player1 = new Player(1, false);

        square = new Square(new Property(200, 50, "property", "red"), "red");

        System.out.println(square.toXML(1));
    }
}
