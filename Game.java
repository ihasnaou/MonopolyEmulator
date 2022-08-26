import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

/**
* @author for m4: Ilyes, Sara, Sam
*/

public class Game implements Serializable{

    /**
     * Number of turns in Game.
     */
    public static int turn = 0;

    /**
     * Players in the Game.
     */
    public ArrayList<Player> players = new ArrayList<Player>();

    private static int count = 0;
    
    
    /**
     * Squares in the Game.
     */
    private ArrayList<Square> squares = new ArrayList<>();


    /**
     * Arraylist that holds dice value
     */
    private ArrayList<Integer> number;

    /**
     * Arraylist of views following MVC pattern
     */
    private Collection<MonopolyView> views;


    private String currency;


    /**
     * Constructor for Game class
     */

    public Game(int numberOfPlayers, int numberOfAI){

        numberOfPlayers = numberOfPlayers;

        this.currency = "USD";

        for(int i = 0; i < numberOfPlayers; i++) {
            Player player = new Player(i+1, false);
            players.add(player);
        }

        for(int i = numberOfPlayers; i < (numberOfPlayers + numberOfAI); i++) {
            Player player = new Player(i+1, true);
            players.add(player);
        }

        //Go square
        this.squares.add(new Square(new Property(100000000, 0, "GO", "undecided"), "undecided"));

        //Square one to 4
        for (int i = 1; i < 4; i++) {
            this.squares.add(new Square(new Property(300,50, String.valueOf(i), "yellow"),"yellow" ));
        }

        //BO RailRoad
        this.squares.add(new Square(new RailRoad("BO RailRoad", "undecided"), "undecided"));

        //Square 5
        this.squares.add(new Square(new Property(300,50, "5", "yellow"), "yellow"));

        //Water Works
        this.squares.add(new Square(new Utility("Water Works", this, "undecided"),"undecided"));

        //Square 7
        this.squares.add(new Square(new Property(300,50, "7", "yellow"), "yellow"));

        //Jail
        this.squares.add(new Jail(this));

        //Squares 9 to 11
        for (int i = 9; i < 12; i++) {
            this.squares.add(new Square(new Property(300,50, String.valueOf(i), "red"), "red"));
        }

        //Short Line
        this.squares.add(new Square(new RailRoad("Short Line", "undecided"),"undecided"));

        //Squares 13 to 15
        for (int i = 13; i < 16; i++) {
            this.squares.add(new Square(new Property(300,50, String.valueOf(i), "red"),"red"));
        }

        //Free Parking
        this.squares.add(new Square(new Property(100000000, 0, "Free Parking", "undecided"),"undecided"));

        //Square 17 to 19
        for (int i = 17; i < 20; i++) {
            this.squares.add(new Square(new Property(300,50, String.valueOf(i), "blue"),"blue"));
        }

        //Reading Railroad
        this.squares.add(new Square(new RailRoad("Reading Railroad", "undecided"),"undecided"));

        //Squares 21 to 23
        for (int i = 21; i < 24; i++) {
            this.squares.add(new Square(new Property(300,50, String.valueOf(i), "blue"), "blue"));
        }

        //Go to jail square
        this.squares.add(new Square(new Property(100000000, 0, "Go to jail", "undecided"), "undecided"));

        //Square 25
        this.squares.add(new Square(new Property(300,50, String.valueOf(25), "brown"), "brown"));

        //Electric company
        this.squares.add(new Square(new Utility("Electric Company", this, "undecided"), "undecided"));

        //Square 27
        this.squares.add(new Square(new Property(300,50, String.valueOf(27), "brown"),"brown"));

        //Pennsylvania Railroad
        this.squares.add(new Square(new RailRoad("Pennsylvania Railroad", "yellow"),"undecided"));

        //Squares 29 to 31
        for (int i = 29; i < 32; i++) {
            this.squares.add(new Square(new Property(300,50, String.valueOf(i), "brown"),"brown"));
        }

        this.views = new ArrayList<>();

        this.number = new ArrayList<>();
    }

    /**
     *Add a view
     * @param view
     */
    public void addView(MonopolyView view){
        this.views.add(view);
    }

    /**
     * Updates the view
     */
    public void updateViews(){
        for (MonopolyView view: this.views){
            view.handleUpdate(this,this.players,this.squares);
        }
    }

    /**
     *Getter method for player attribute
     * @return
     */
    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    /**
     * Method that moves the player around the board and updates the view
     * @param player
     */
    public void movePlayer(Player player){
        roll_dice();
        player.setLocation((this.number.get(0) + this.number.get(1)));
        updateViews();
    }

    /**
     * Method that controls if a player lost and removes players who can no longer play and updates the view
     * @param player of type player
     * @return false or true based on if there's a winner
     */
    public boolean losePlayer(Player player){
        this.players.remove(player);
        turn --;
        updateViews();
        return players.size() == 1;
    }

    /**
     * Method to buy property and updates the view
     * @param player of type player
     * @return true or false if a player has bought the property or not
     */
    public boolean buy(Player player){
        boolean a = this.squares.get(player.getLocation()).buyProperty(player);
        updateViews();
        return a;
    }

    /**
     * Method to breakout the player from jail and updates the view
     * @param player of type player
     */
    public void breakOut(Player player) {
        this.squares.get(8).playerOnProperty(player);
        updateViews();
    }

    /**
     * Method that rents a property and updates the view
     * @param player
     * @return true or false if the player was able to rent
     */
    public boolean rent(Player player){
        boolean a =  this.squares.get(player.getLocation()).payRent(player);
        updateViews();
        return a;
    }

    public void jailPlayer(Player p) {
        this.squares.get(8).sendToJail(p);
        updateViews();
    }

    /**
     * Roll two dice. If the first die and the second die were of the same value then player rolled a double
     * @return   an Array of integers with the indicies int[0] = first roll, int[1] = second roll, int[2] = rolled double
     */

    public void roll_dice() {

        //roll two dice
        Random r = new Random();
        int die1 = r.nextInt(6) + 1;
        int die2 = r.nextInt(6) + 1;


        int roll_double;
        if(die1 == die2) { // player rolled a double
            roll_double = 1; //roll_double = 1 signifies the player has rolled a double
        }
        else roll_double = 0; // 0 if the player did not roll a double

        ArrayList<Integer> out = new ArrayList<>();
        out.add(0,die1);
        out.add(1,die2);
        out.add(2,roll_double);


        this.number = out;
    }

    /**
     * Getter method of number of dice rolls
     * @return arrayList of rolls you have made
     */
    public ArrayList<Integer> getNumber() {
        return number;
    }

    /**
     * Getter method of squares
     * @return arrayList of squares
     */
    public ArrayList<Square> getSquares() {
        return squares;
    }

    /**
     * Method that converts an object to type XML
     * @return XML of type String
     */
    public String toXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<Game>\n");
        sb.append("     ".repeat(Math.max(0, 1)));
        sb.append("<turn>").append(turn).append("</turn>\n");
        sb.append("     ".repeat(Math.max(0, 1)));
        sb.append("<currency>").append(currency).append("</currency>\n");
        count += 1;
        for (Player p : players) {
            sb.append(p.toXML(count));
        }
        count -= 1;
        count += 1;
        for (Square sq : squares) {
            sb.append(sq.toXML(count));
        }
        count -= 1;
        sb.append("</Game>");

        return sb.toString();
    }



    /**
     * Method to save the game
     * @param fileName as name of the file 
     * @throws IOException
     */
    public void saveGame(String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(this.toXML());

        writer.flush();
        writer.close();
    }

    /**
     * Set player method to set the number of players
     * @param p arrayList of type Player
     */
    public void setPlayers(ArrayList<Player> p) {
        this.players = p;
    }

    /**
     * Setter method of squares
     * @param sq arrayList of type Square
     */
    public void setSquares(ArrayList<Square> sq) {
        this.squares = sq;
    }

    public static ArrayList<Player> removePlayers(ArrayList<Player> players) {
        ArrayList<Player> players4 = new ArrayList<>();
        for (Player player : players) {
            if (player.getID() == -1) {
                players4.add(player);
            }
        }
        players.removeAll(players4);
        return players;

    }

    /**
     * Method to read the SAX
     * @param fileName of type String for FileName
     * @return a loaded game
     */
    public static Game readSAX(File fileName) throws Exception {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser s = spf.newSAXParser();
        Game loadedGame = new Game(0,0);
        ArrayList<Player> players = new ArrayList<>();
        ArrayList<Square> squares = new ArrayList<>();



        DefaultHandler dh = new DefaultHandler(){
            boolean checkPlayer=false, checkSquare=false,checkProperty=false,checkCurrency = false, checkTurn = false;
            String currentAttribute = "";
            private Player player;
            private Square square;
            private Property property;

            public void startElement(String u, String ln, String qName, Attributes a) {
                if (qName.equals("Player")) {
                    if(!(checkProperty)) {
                        checkPlayer = true;
                        players.add(new Player(-1, true));
                    }
                }
                else if(qName.equals("RailRoad")){
                    checkProperty = true;
                    squares.get(squares.size() - 1).setProperty(new RailRoad("","undecided"));
                }
                else if(qName.equals("Utility")){
                    checkProperty = true;
                    squares.get(squares.size() - 1).setProperty(new Utility("",loadedGame,"undecided"));
                }
                else if (qName.equals("Square")) {
                    checkSquare = true;
                    squares.add(new Square(new Property(0, 0, "none", "undecided"), "undecided"));
                }
                else if(qName.equals("Property")) {
                    checkProperty = true;
                }
                else if(qName.equals("currency")){
                    checkCurrency = true;
                }
                else if(qName.equals("turn")){
                    checkTurn = false;
                }
                else{
                    currentAttribute = qName;
                }
                System.out.println("StartElement: " + qName);
            }
            public void endElement(String uri, String localName, String qName) {

                if(qName.equals("Property")){
                    checkProperty = false;
                }
                if (qName.equals("Square")){
                    checkSquare = false;
                }
                if(qName.equals("Player")){
                    checkPlayer = false;
                }
                if(qName.equals("Utility")){
                    checkProperty = false;
                }
                if(qName.equals("RailRoad")){
                    checkProperty = false;
                }
                if(qName.equals("Jail")){
                    squares.add(new Jail(loadedGame));
                }
                if(qName.equals("currency")){
                    checkCurrency = false;
                }

                if (qName.equals("Game")) {
                    ArrayList<Player> newPlayers = removePlayers(players);
                    loadedGame.setPlayers(newPlayers);
                    loadedGame.setSquares(squares);
                }
                System.out.println("EndElement: " + qName);
            }
            public void characters(char[] ch, int start, int length) {

                String s1 = new String(ch, start, length);

                System.out.println("Character: " + new String(ch, start, length));


                if(checkPlayer) {
                    playerCase(currentAttribute, s1);
                }
                if(checkSquare) {
                    squareCase(currentAttribute,s1);
                }
                if(checkProperty) {
                    propertyCase(currentAttribute,s1);
                }
                if(checkCurrency){
                    loadedGame.setCurrency(s1);
                }
                if(checkTurn){
                    turn = Integer.parseInt(s1);

                }

            }

            private void propertyCase(String s, String s1) {
                switch (s) {
                    case "name":
                        squares.get(squares.size() - 1).getProperty().setName(s1);
                        currentAttribute = "";
                        break;
                    case "rent":
                        squares.get(squares.size() - 1).getProperty().setRent(Integer.parseInt(s1));
                        currentAttribute = "";
                        break;
                    case "color":
                        squares.get(squares.size() - 1).getProperty().setColor(s1);
                        currentAttribute = "";
                        break;
                    case"price":
                        squares.get(squares.size() - 1).getProperty().setPrice(Integer.parseInt(s1));
                        currentAttribute = "";
                        break;
                    case "ID":
                        int id = Integer.parseInt(s1);
                        Player p = new Player(-1,true);
                        if(id != -1){ // if id of player in the property is not -1
                            for(Player player:players){ // find player with id
                                if(player.getID() == id){
                                    p = player;
                                    break;
                                }
                            }
                            squares.get(squares.size() -  1).getProperty().setOwner(p);
                        }
                        currentAttribute = "";
                }

            }

            private void squareCase(String s, String s1) {
                switch (s) {
                    case "owned":
                        squares.get(squares.size() - 1).setOwned(Boolean.parseBoolean(s1));
                        currentAttribute= "";
                        break;
                    case "ownedInSquare":
                        squares.get(squares.size() - 1).setOwnedInSquare(Integer.parseInt(s1));
                        currentAttribute= "";
                        break;
                    case "color":
                        squares.get(squares.size() - 1).setColor(s1);
                        currentAttribute= "";
                        break;
                }
            }

            private void playerCase(String s, String s1) {
                switch (s) {
                    case "ID":
                        players.get(players.size() - 1).setID(Integer.parseInt(s1));
                        currentAttribute = "";
                        break;
                    case "location":
                        players.get(players.size() - 1).makeLocation(Integer.parseInt(s1));
                        currentAttribute = "";
                        break;
                    case "balance":
                        players.get(players.size() - 1).setBalance(Integer.parseInt(s1));
                        currentAttribute = "";
                        break;
                    case "turnsInJail":
                        players.get(players.size() - 1).setTurnsInJail(Integer.parseInt(s1));
                        currentAttribute = "";
                        break;
                    case "AI":
                        players.get(players.size() - 1).setAI(Boolean.parseBoolean(s1));
                        currentAttribute = "";
                        break;
                }

            }

        };

        s.parse(fileName, dh);

        return loadedGame;
    }
    
    /**
     * Setter method of the currency attribute
     * @param currency as a String version of the currency i.e USD, CAD..
     */
    private void setCurrency(String currency) {

        this.currency = currency;
    }
     /**
     * Getter method of the currency attribute
     * @return currency as type String
     */
    public String getCurrency() {
        return currency;
    }

    

}
