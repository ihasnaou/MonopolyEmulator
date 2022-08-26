import static org.junit.Assert.assertEquals;

/**
* @author for m4: Ilyes, javadoc Sara
*/
public class PropertyTest {

    Player player1;
    Player player2;
    Property property;
    Utility utility1;
    Utility utility2;
    RailRoad rr1;
    RailRoad rr2;

    /**
    * Method that tests the basic functions of normal property
    */
    @org.junit.Test
    public void testProperty() {
        player1 = new Player(1, false);
        player2 = new Player(2, false);

        property = new Property(200, 50, "Test", "red");
        property.offerBuy(player1);

        assertEquals(player1, property.getOwner());

        property.payRent(player2);
        assertEquals(1650, player2.getBalance());
    }

    /**
    * Method that tests the basic functions of railroad
    */
    @org.junit.Test
    public void testRailRoad() {
        player1 = new Player(1, false);

        rr1 = new RailRoad("rr1", "undecided");
        rr2 = new RailRoad("rr2", "undecided");

        assertEquals(0, rr1.getRent());

        rr1.offerBuy(player1);
        assertEquals(25, rr1.getRent());

        rr2.offerBuy(player1);
        assertEquals(50, rr1.getRent());
    }

    /**
    * Method that tests the basic functions of utilities
    */
    @org.junit.Test
    public void testUtilities() {
        player1 = new Player(1, false);

        Game game = new Game(1, 0);

        utility1 = new Utility("u1", game,  "undecided");
        utility2 = new Utility("u2", game,  "undecided");

        game.roll_dice();

        utility1.offerBuy(player1);
        assertEquals(4 * (game.getNumber().get(0) + game.getNumber().get(1)), utility1.getRent());

        utility2.offerBuy(player1);
        assertEquals(10 * (game.getNumber().get(0) + game.getNumber().get(1)), utility2.getRent());
    }
    
     /**
     * Test method of property to XML functionality
     */
    @org.junit.Test
    public void testPropertyToXML() {
        property = new Property(200, 50, "Test", "red");
        player1 = new Player(1, false);
        property.offerBuy(player1);

        System.out.println(property.toXML(1));
    }
    
    /**
     * Test method of railroad to XML
     */
    @org.junit.Test
    public void testRailRoadToXML() {
        player1 = new Player(1, false);
        rr1 = new RailRoad("rr1", "undecided");
        rr1.offerBuy(player1);

        System.out.println(rr1.toXML(1));
    }

     /**
     * Test method of utility to XML
     */
    @org.junit.Test
    public void testUtilityToXML() {
        player1 = new Player(1, false);

        Game game = new Game(1, 0);

        utility1 = new Utility("u1", game,  "undecided");

        utility1.offerBuy(player1);

        System.out.println(utility1.toXML(1));
    }
}
