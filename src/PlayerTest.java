import static org.junit.Assert.assertEquals;

/**
* @author for m4: Ilyes, javadoc Sara
*/

public class PlayerTest {

    Player player;
    /**
     * Method that tests the Jail status functionality
     */
    @org.junit.Test
    public void testJailStatus() {
        player = new Player(1, false);

        assertEquals(player.getJailed(), false); //Tests that every player starts off not in jail

        player.setJailed(true);
        assertEquals(player.getJailed(), true); //Tests that setJailed() sets player's status to jailed

        player.setJailed(false);
        assertEquals(player.getJailed(), false); //Tests the freeFromJail() method to make sure it sets player status back to free
    }
    /**
     * Method that tests the rent functionality
     */
    @org.junit.Test
    public void testRent() {
        player = new Player(1, false);

        assertEquals(1700, player.getBalance()); //Makes sure that every player starts with $1500

        assertEquals(true, player.payRent(700)); //Tests that the payRent() method does not return true when a player can afford to pay rent
        assertEquals(1000, player.getBalance()); //Tests that the payRent() method removes the rent amount from a player's balance

        assertEquals(false, player.payRent(1200)); //Tests that the payRent() method returns true when a player cannot afford to pay rent

        player.payRent(200);
        assertEquals(800, player.getBalance());

        player.receiveIncome(200);
        assertEquals(1000, player.getBalance()); //Tests that the recieveIncome() method adds the proper amount of money to a player's balance
    }

     /**
     * Method that tests the property status functionality
     */
    @org.junit.Test
    public void testProperty() {
        player = new Player(1, false);

        assertEquals(0, player.getBoughtProperties().size()); //Makes sure that players start with 0 owned properties

        Game game = new Game(1, 0);


        Property p1 = new Property(100,150, "Test", "red");
        Property p2 = new RailRoad( "Test", "undecided");
        Property p3 = new Utility( "water works", game,"undecided");

        player.buyProperty(p1);
        assertEquals(1, player.getNbOfPropertiesByColor("red")); //Tests that a property has been added to a player's bought properties

        player.buyProperty(p2);
        assertEquals(1, player.getNumRailroads()); //Tests that a property is not added to a player's bought properties if player cannot afford to buy it

        player.buyProperty(p3);
        assertEquals(1, player.getNumUtilities());
    }

     /**
     * Method that tests the location of a player functionality
     */
    @org.junit.Test
    public void testLocation() {
        player = new Player(1, false);

        assertEquals(0, player.getLocation()); //Makes sure that every player starts off at location 0

        player.setLocation(30);
        assertEquals(30, player.getLocation()); //Tests that setLocation() works as intended
    }
    /**
     * Method that tests the XML functionality
     */
    @org.junit.Test
    public void testToXML() {
        player = new Player(1, false);
        System.out.println(player.toXML(1));
    }
    //getnumutilities,
}
