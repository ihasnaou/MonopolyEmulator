import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
/**
* @author for m4: Ilyes, Sara, Sam
*/
public class MonopolyFrame extends JFrame implements MonopolyView{

    /**
     * an array of Jbuttons
     */
    private JButton[] buttons = new JButton[7];

    /**
     * An arrayList of squares that appear on the board
     */
    private ArrayList<JPanel> squares = new ArrayList<>();

    /**
     * An arrayList of player labels
     */
    private ArrayList<JLabel> playerLabels = new ArrayList<>();

    /**
     * A JPanel that hold the squares that appear on top of board ranging from 0 to 8
     */
    private JPanel squares0to8 = new JPanel(); //JPanel that holds Squares 0 to 8
    /**
     * A JPanel that hold the squares that appear on top of board ranging from 9 to 15
     */
    private JPanel squares9to15 = new JPanel(); //JPanel that holds Squares 9 to 15
    /**
     * A JPanel that hold the squares that appear on top of board ranging from 16 to 24
     */
    private JPanel squares16to24 = new JPanel(); //JPanel that holds Squares 16 to 24
    /**
     * A JPanel that hold the squares that appear on top of board ranging from 25 to 31
     */
    private JPanel squares25to31 = new JPanel(); //JPanel that holds Squares 25 to 31
    /**
     * A game representing the model following the MVC pattern of type game
     */
    private Game game;
    /**
     * A JLabel at the bottom right of the game that updates the player info
     */
    private JLabel currentPlayerInfo;

    /**
     * A JLabel of the textField
     */
    private JLabel textField;

    /**
     * Monopoly Frame constructor that follows the MVC pattern
     * @param nbOfP representing the number of players
     * @param nbOfAI representing the number of AI players
     */
    public MonopolyFrame(int nbOfP, int nbOfAI, Game game) {
        super("Monopoly Game!");
        JButton help = new JButton("Help");
        JButton quit = new JButton("Quit");
        JButton rent = new JButton("Rent");
        JButton buy = new JButton("Buy");
        JButton roll = new JButton("Roll");
        JButton giveTurn = new JButton("Give Turn");
        JButton save = new JButton("Save");

        help.setActionCommand("Help");
        quit.setActionCommand("Quit");
        rent.setActionCommand("Rent");
        buy.setActionCommand("Buy");
        roll.setActionCommand("Roll");
        giveTurn.setActionCommand("Give Turn");
        save.setActionCommand("Save");

        this.textField = new JLabel("Welcome to Monopoly, to start the game, please press Roll", SwingConstants.CENTER);

        buttons[0] = help;
        buttons[1] = quit;
        buttons[2] = rent;
        buttons[3] = buy;
        buttons[4] = roll;
        buttons[5] = giveTurn;
        buttons[6] = save;

        buttons[2].setEnabled(false);
        buttons[3].setEnabled(false);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        this.game = game;
        this.game.addView(this);

        buildJLabels();
        buildPlayers();

        JPanel board = new JPanel(); //Creates board object that will hold all squares
        board.setLayout(new BorderLayout());

        squares0to8.setPreferredSize(new Dimension(900, 100));
        squares0to8.setLayout(new GridLayout());
        for (int i = 0; i < 9; i++) {
            squares0to8.add(squares.get(i));
        } //JPanel on top of frame that will hold squares 0 to 8
        board.add(squares0to8, BorderLayout.NORTH);
        board.setPreferredSize(new Dimension(900, 600));

        JPanel middlePanel = new JPanel();
        middlePanel.setPreferredSize(new Dimension(900, 800));
        middlePanel.setBackground(new Color(102,255,102));
        middlePanel.setLayout(new BorderLayout());
        board.add(middlePanel, BorderLayout.CENTER); //Middle panel that will hold JPanels that will hold: squares 25 to 31 on the left, 9 to 15 on the right, and green in the middle

        squares9to15.setPreferredSize(new Dimension(100, 700));
        squares9to15.setLayout(new BoxLayout(squares9to15, BoxLayout.PAGE_AXIS));
        for (int i = 9; i < 16; i++) {
            squares9to15.add(squares.get(i));
        } //JPanel
        middlePanel.add(squares9to15, BorderLayout.EAST);

        squares16to24.setPreferredSize(new Dimension(900, 100));
        squares16to24.setLayout(new GridLayout());
        for (int i = 24; i > 15; i--) {
            squares16to24.add(squares.get(i));
        }
        board.add(squares16to24, BorderLayout.SOUTH);

        squares25to31.setPreferredSize(new Dimension(100, 700));
        squares25to31.setLayout(new BoxLayout(squares25to31, BoxLayout.PAGE_AXIS));
        for (int i = 31; i > 24; i--) {
            squares25to31.add(squares.get(i));
        }
        middlePanel.add(squares25to31, BorderLayout.WEST);

        for(int i = 0; i < this.playerLabels.size(); i ++){
            this.squares.get(game.getPlayers().get(i).getLocation()).add(this.playerLabels.get(i));
        }

        JPanel commands = new JPanel();

        commands.setLayout( new BorderLayout());

        MonopolyController controller = new MonopolyController(this.game, this);

        JPanel b1 = new JPanel();
        b1.setLayout(new GridLayout(2, 4));

        for(JButton button: this.buttons){
            button.addActionListener(controller);
            b1.add(button);
        }

        textField.setMaximumSize(new Dimension(500, 500));
        textField.setPreferredSize(new Dimension(500, 500));
        textField.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        commands.add(b1, BorderLayout.PAGE_START);
        commands.add(textField, BorderLayout.CENTER);

        currentPlayerInfo = new JLabel("", SwingConstants.CENTER);
        currentPlayerInfo.setPreferredSize(new Dimension(500, 400));
        currentPlayerInfo.setMaximumSize(new Dimension(500, 400));
        currentPlayerInfo.setBackground(new Color(200, 200, 100));

        commands.add(currentPlayerInfo, BorderLayout.PAGE_END);

        commands.setPreferredSize(new Dimension(500, 900));
        commands.setMaximumSize(new Dimension(500, 900));
        commands.setBorder(BorderFactory.createLineBorder(Color.BLACK));


        this.setLayout(new BorderLayout());
        this.add(board, BorderLayout.WEST);
        this.add(commands, BorderLayout.EAST);

        this.setVisible(true);


        this.setSize(1400,900);
    }

    /**
     * A frame that appears to ask how many players in the game
     */
    private static void askNumberOfPlayers() {
        JFrame nbOfPlayers = new JFrame("Set number of players");
        nbOfPlayers.setSize(new Dimension(250, 250));
        nbOfPlayers.getContentPane().setLayout(new GridLayout(3,1));

        JLabel q = new JLabel("<html>How many players do you want?<br>Note that the minimum number of players is 2</html>", SwingConstants.CENTER);
        q.setSize(new Dimension(250, 150));

        JLabel buttons = new JLabel();
        buttons.setSize(new Dimension(200, 75));
        buttons.setLayout(new BorderLayout());

        JButton less = new JButton(" - ");
        less.setSize(new Dimension(75, 75));
        JLabel nb = new JLabel(String.valueOf(1), SwingConstants.CENTER);
        nb.setSize(new Dimension(100, 75));
        JButton more = new JButton(" + ");
        more.setSize(new Dimension(75, 75));

        less.addActionListener(new ActionListener() {

            /**
             * method that decrements the number of players
             * @param actionEvent
             */
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int newNB = Integer.parseInt(nb.getText()) - 1;
                if (newNB < 1) {
                    JOptionPane.showMessageDialog(nbOfPlayers, "You need at least 1 player to play this game!","Error", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    nb.setText(String.valueOf(newNB));
                    nb.repaint();
                }

            }
        });
        more.addActionListener(new ActionListener() {

            /**
             * A method that increments the number of players
             * @param actionEvent
             */
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int newNB = Integer.parseInt(nb.getText()) + 1;
                if (newNB > 5) {
                    JOptionPane.showMessageDialog(nbOfPlayers, "You cannot have more than 5 players at once!","Error", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    nb.setText(String.valueOf(newNB));
                    nb.repaint();
                }
            }
        });


        buttons.add(less, BorderLayout.WEST);
        buttons.add(nb, BorderLayout.CENTER);
        buttons.add(more, BorderLayout.EAST);

        JButton okay = new JButton("Confirm");
        okay.setSize(new Dimension(50, 25));

        okay.addActionListener(new ActionListener() {

            /**
             * Saves the number players and opens a new frame that sets the number of AI players
             * @param actionEvent
             */
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                askNumberOfAI(Integer.parseInt(nb.getText()));
                nbOfPlayers.dispose();
            }
        });

        nbOfPlayers.add(q);
        nbOfPlayers.add(buttons);
        nbOfPlayers.add(okay);

        nbOfPlayers.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        nbOfPlayers.setVisible(true);

    }

    /**
     * A frame that appears to ask how many players in the game
     * @param numberOfPlayers
     */
    private static void askNumberOfAI(int numberOfPlayers) {
        JFrame nbOfPlayers = new JFrame("Set number of AI");
        nbOfPlayers.setSize(new Dimension(250, 250));
        nbOfPlayers.getContentPane().setLayout(new GridLayout(3,1));

        JLabel q = new JLabel("How many AIs do you want?", SwingConstants.CENTER);
        q.setSize(new Dimension(250, 150));

        JLabel buttons = new JLabel();
        buttons.setSize(new Dimension(200, 75));
        buttons.setLayout(new BorderLayout());

        JButton less = new JButton(" - ");
        less.setSize(new Dimension(75, 75));
        JLabel nb = new JLabel(String.valueOf(0), SwingConstants.CENTER);
        nb.setSize(new Dimension(100, 75));
        JButton more = new JButton(" + ");
        more.setSize(new Dimension(75, 75));

        less.addActionListener(new ActionListener() {

            /**
             * method that decrements the number of players
             * @param actionEvent
             */
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int newNB = Integer.parseInt(nb.getText());
                if (newNB < 0) {
                    JOptionPane.showMessageDialog(nbOfPlayers, "You cannot have a negative number of AIs!","Error", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    nb.setText(String.valueOf(newNB));
                    nb.repaint();
                }

            }
        });
        more.addActionListener(new ActionListener() {
            /**
             * method that increments the number of players
             * @param actionEvent
             */
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int newNB = Integer.parseInt(nb.getText()) + 1;
                if (newNB + numberOfPlayers > 5) {
                    JOptionPane.showMessageDialog(nbOfPlayers, "You cannot have more than 5 players at once!","Error", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    nb.setText(String.valueOf(newNB));
                    nb.repaint();
                }
            }
        });


        buttons.add(less, BorderLayout.WEST);
        buttons.add(nb, BorderLayout.CENTER);
        buttons.add(more, BorderLayout.EAST);

        JButton okay = new JButton("Confirm");
        okay.setSize(new Dimension(50, 25));

        okay.addActionListener(new ActionListener() {
            /**
             * method that creates a new monopoly frame with given settings
             * @param actionEvent
             */
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new MonopolyFrame(numberOfPlayers, Integer.parseInt(nb.getText()), new Game(numberOfPlayers,Integer.parseInt(nb.getText())));
                nbOfPlayers.dispose();
            }
        });

        nbOfPlayers.add(q);
        nbOfPlayers.add(buttons);
        nbOfPlayers.add(okay);

        nbOfPlayers.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        nbOfPlayers.setVisible(true);
    }

    /**
     * Getter method for the textField attribute
     * @return the textField attribute
     */
    public JLabel getTextField() {
        return this.textField;
    }

    /**
     * Method that updates the frame based on an input of an action
     * @param game as type game
     * @param players as type arrayList of players
     * @param squares as type arrayList of squares
     */
    @Override
    public void handleUpdate(Game game, ArrayList<Player> players, ArrayList<Square> squares) {


        Container parent = playerLabels.get(Game.turn).getParent();

        parent.remove(this.playerLabels.get(Game.turn));

        this.squares.get(this.game.getPlayers().get(Game.turn).getLocation()).add(this.playerLabels.get(Game.turn));

        this.currentPlayerInfo.setText("<html>Current turn:<br>Player " + this.game.getPlayers().get(Game.turn).getID()
                + "<br>AI : " + this.game.getPlayers().get(Game.turn).isAI()
                + "<br>Jailed status : " + this.game.getPlayers().get(Game.turn).getJailed()
                + "<br>Current Balance : " +  this.game.getPlayers().get(Game.turn).getBalance()
                + "<br>Current Location : " + this.game.getPlayers().get(Game.turn).getLocation()
                + "<br>Number of houses owned in yellow: " + this.game.getPlayers().get(Game.turn).getNbOfPropertiesByColor("yellow")
                + "<br>Number of houses owned in red: " + this.game.getPlayers().get(Game.turn).getNbOfPropertiesByColor("red")
                + "<br>Number of houses owned in blue: " + this.game.getPlayers().get(Game.turn).getNbOfPropertiesByColor("blue")
                + "<br>Number of houses owned in brown: " + this.game.getPlayers().get(Game.turn).getNbOfPropertiesByColor("brown")
                + "<br>Number of utilities / railroads owned: " + this.game.getPlayers().get(Game.turn).getNbOfPropertiesByColor("undecided")
                + "</html>");

        this.repaint();
        this.revalidate();
    }



    /**
     * Getter method for button attributes
     * @return the buttons
     */
    public JButton[] getButtons() {
        return this.buttons;
    }

    /**
     * The main method
     * @param args
     */
    public static void main(String[] args) {
        newGameOrLoad();
    }

    /**
     * Method that builds the player attribute
     */
    private void buildPlayers() {
        for(Player player: this.game.getPlayers()){
            JLabel p = new JLabel(String.valueOf(player.getID()), SwingConstants.CENTER);
            p.setPreferredSize(new Dimension(100,100));
            this.playerLabels.add(p);
        }
    }

    /**
     * Method that builds the square attribute
     */
    private void buildJLabels() {

        squares.add(new JPanel());


        squares.get(0).add(new JLabel( game.getSquares().get(0).getProperty().getName(), SwingConstants.CENTER));
        squares.get(0).setLayout(new GridLayout(6,1));
        squares.get(0).setBorder(BorderFactory.createLineBorder(Color.BLACK));



        for (int i = 1; i < 4; i++) {
            squares.add(new JPanel());
            JLabel n = new JLabel( game.getSquares().get(i).getProperty().getName(), SwingConstants.CENTER);
            n.setBackground(new Color(255, 255, 153));
            n.setOpaque(true);
            n.setSize(new Dimension(100, 25));
            squares.get(i).setLayout(new GridLayout(6,1));
            squares.get(i).add(n);
            squares.get(i).setBorder(BorderFactory.createLineBorder(Color.BLACK));
            squares.get(i).setPreferredSize(new Dimension(100,100));
        }

        squares.add(new JPanel());
        squares.get(4).add(new JLabel( game.getSquares().get(4).getProperty().getName(), SwingConstants.CENTER));
        squares.get(4).setLayout(new GridLayout(6,1));
        squares.get(4).setBorder(BorderFactory.createLineBorder(Color.BLACK));


        squares.add(new JPanel());
        JLabel n2 = new JLabel( game.getSquares().get(5).getProperty().getName(), SwingConstants.CENTER);
        n2.setBackground(new Color(255, 255, 153));
        n2.setOpaque(true);
        n2.setSize(new Dimension(100, 25));
        squares.get(5).setLayout(new GridLayout(6,1));
        squares.get(5).add(n2);
        squares.get(5).setBorder(BorderFactory.createLineBorder(Color.BLACK));
        squares.get(5).setPreferredSize(new Dimension(100,100));

        squares.add(new JPanel());
        squares.get(6).add(new JLabel( game.getSquares().get(6).getProperty().getName() , SwingConstants.CENTER));
        squares.get(6).setLayout(new GridLayout(6,1));
        squares.get(6).setBorder(BorderFactory.createLineBorder(Color.BLACK));

        squares.add(new JPanel());
        JLabel n3 = new JLabel( game.getSquares().get(7).getProperty().getName(), SwingConstants.CENTER);
        n3.setBackground(new Color(255, 255, 153));
        n3.setOpaque(true);
        n3.setSize(new Dimension(100, 25));
        squares.get(7).setLayout(new GridLayout(6,1));
        squares.get(7).add(n3);
        squares.get(7).setBorder(BorderFactory.createLineBorder(Color.BLACK));
        squares.get(7).setPreferredSize(new Dimension(100,100));


        squares.add(new JPanel());
        squares.get(8).add(new JLabel( game.getSquares().get(8).getProperty().getName(), SwingConstants.CENTER));
        squares.get(8).setLayout(new GridLayout(6,1));
        squares.get(8).setBorder(BorderFactory.createLineBorder(Color.BLACK));
        squares.get(8).setOpaque(true);

        for (int i = 9; i < 12; i++) {
            squares.add(new JPanel());
            JLabel n = new JLabel( game.getSquares().get(i).getProperty().getName(), SwingConstants.CENTER);
            n.setBackground(new Color(255, 102, 102));
            n.setOpaque(true);
            n.setSize(new Dimension(100, 25));
            squares.get(i).setLayout(new GridLayout(6,1));
            squares.get(i).add(n, BorderLayout.NORTH);
            squares.get(i).setBorder(BorderFactory.createLineBorder(Color.BLACK));
            squares.get(i).setPreferredSize(new Dimension(100,100));
            squares.get(i).setOpaque(true);
        }

        squares.add(new JPanel());
        squares.get(12).add(new JLabel( game.getSquares().get(12).getProperty().getName(), SwingConstants.CENTER));
        squares.get(12).setLayout(new GridLayout(6,1));
        squares.get(12).setBorder(BorderFactory.createLineBorder(Color.BLACK));


        for (int i = 13; i < 16; i++) {
            squares.add(new JPanel());
            JLabel n = new JLabel( game.getSquares().get(i).getProperty().getName(), SwingConstants.CENTER);
            n.setBackground(new Color(255, 102, 102));
            n.setOpaque(true);
            n.setSize(new Dimension(100, 25));
            squares.get(i).setLayout(new GridLayout(6,1));
            squares.get(i).add(n, BorderLayout.NORTH);
            squares.get(i).setBorder(BorderFactory.createLineBorder(Color.BLACK));
            squares.get(i).setPreferredSize(new Dimension(100,100));
        }

        squares.add(new JPanel());
        squares.get(16).add(new JLabel( game.getSquares().get(16).getProperty().getName(), SwingConstants.CENTER));
        squares.get(16).setLayout(new GridLayout(6,1));
        squares.get(16).setBorder(BorderFactory.createLineBorder(Color.BLACK));

        for (int i = 17; i < 20; i++) {
            squares.add(new JPanel());
            JLabel n = new JLabel( game.getSquares().get(i).getProperty().getName(), SwingConstants.CENTER);
            n.setBackground(new Color(51, 204, 255));
            n.setOpaque(true);
            n.setSize(new Dimension(100, 25));
            squares.get(i).setLayout(new GridLayout(6,1));
            squares.get(i).add(n, BorderLayout.NORTH);
            squares.get(i).setBorder(BorderFactory.createLineBorder(Color.BLACK));
            squares.get(i).setPreferredSize(new Dimension(100,100));
        }

        squares.add(new JPanel());
        squares.get(20).add(new JLabel( game.getSquares().get(20).getProperty().getName(), SwingConstants.CENTER));
        squares.get(20).setLayout(new GridLayout(6,1));
        squares.get(20).setBorder(BorderFactory.createLineBorder(Color.BLACK));

        for (int i = 21; i < 24; i++) {
            squares.add(new JPanel());
            JLabel n = new JLabel( game.getSquares().get(i).getProperty().getName(), SwingConstants.CENTER);
            n.setBackground(new Color(51, 204, 255));
            n.setOpaque(true);
            n.setSize(new Dimension(100, 25));
            squares.get(i).setLayout(new GridLayout(6,1));
            squares.get(i).add(n, BorderLayout.NORTH);
            squares.get(i).setBorder(BorderFactory.createLineBorder(Color.BLACK));
            squares.get(i).setPreferredSize(new Dimension(100,100));
        }

        squares.add(new JPanel());
        squares.get(24).add(new JLabel( game.getSquares().get(24).getProperty().getName(), SwingConstants.CENTER));
        squares.get(24).setLayout(new GridLayout(6,1));
        squares.get(24).setBorder(BorderFactory.createLineBorder(Color.BLACK));

        for (int i = 25; i < 26; i++) {
            squares.add(new JPanel());
            JLabel n = new JLabel( game.getSquares().get(i).getProperty().getName(), SwingConstants.CENTER);
            n.setBackground(new Color(153, 102, 0));
            n.setOpaque(true);
            n.setSize(new Dimension(100, 25));
            squares.get(i).setLayout(new GridLayout(6,1));
            squares.get(i).add(n, BorderLayout.NORTH);
            squares.get(i).setBorder(BorderFactory.createLineBorder(Color.BLACK));
            squares.get(i).setPreferredSize(new Dimension(100,100));
        }

        squares.add(new JPanel());
        System.out.println(game.getSquares().get(26).getProperty().getName());
        squares.get(26).add(new JLabel( game.getSquares().get(26).getProperty().getName(), SwingConstants.CENTER));
        squares.get(26).setLayout(new GridLayout(6,1));
        squares.get(26).setBorder(BorderFactory.createLineBorder(Color.BLACK));

        for (int i = 27; i < 28; i++) {
            squares.add(new JPanel());
            JLabel n = new JLabel(game.getSquares().get(i).getProperty().getName(), SwingConstants.CENTER);
            n.setBackground(new Color(153, 102, 0));
            n.setOpaque(true);
            n.setSize(new Dimension(100, 25));
            squares.get(i).setLayout(new GridLayout(6,1));
            squares.get(i).add(n, BorderLayout.NORTH);
            squares.get(i).setBorder(BorderFactory.createLineBorder(Color.BLACK));
            squares.get(i).setPreferredSize(new Dimension(100,100));
        }

        squares.add(new JPanel());
        squares.get(28).add(new JLabel( game.getSquares().get(28).getProperty().getName(), SwingConstants.CENTER));
        squares.get(28).setLayout(new GridLayout(6,1));
        squares.get(28).setBorder(BorderFactory.createLineBorder(Color.BLACK));

        for (int i = 29; i < 32; i++) {
            squares.add(new JPanel());
            JLabel n = new JLabel( game.getSquares().get(i).getProperty().getName(), SwingConstants.CENTER);
            n.setBackground(new Color(153, 102, 0));
            n.setOpaque(true);
            n.setSize(new Dimension(100, 25));
            squares.get(i).setLayout(new GridLayout(6,1));
            squares.get(i).add(n, BorderLayout.NORTH);
            squares.get(i).setBorder(BorderFactory.createLineBorder(Color.BLACK));
            squares.get(i).setPreferredSize(new Dimension(100,100));
        }

    }

    /**
     * Enable Buttons ? not used tho
     * @param b1 boolean value
     * @param b2 boolean value
     * @param b3 boolean value
     * @param b4 boolean value
     */
    public void enableButtons(boolean b1,boolean b2, boolean b3, boolean b4) {
        this.getButtons()[2].setEnabled(b1);
        this.getButtons()[3].setEnabled(b2);
        this.getButtons()[4].setEnabled(b3);
        this.getButtons()[5].setEnabled(b4);
    }

    /**
     * Method that starts a new game or loads a previously saved game
     */
    public static void newGameOrLoad() {
        JFrame newGameOrLoad = new JFrame("Start Window");
        newGameOrLoad.setSize(new Dimension(250, 250));
        newGameOrLoad.getContentPane().setLayout(new GridLayout(2,1));

        JLabel q = new JLabel("Would you like to start a new game, load an international version, or load a previously saved game?", SwingConstants.CENTER);
        q.setSize(new Dimension(250, 150));

        JLabel buttons = new JLabel();
        buttons.setSize(new Dimension(250, 75));
        buttons.setLayout(new GridLayout(1,2));

        JButton newGame = new JButton(" New ");
        newGame.setSize(new Dimension(125, 75));
        JButton load = new JButton(" Load ");
        load.setSize(new Dimension(125, 75));

        newGame.addActionListener(new ActionListener() {

            /**
             * method that decrements the number of players
             * @param actionEvent
             */
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                askNumberOfPlayers();
                newGameOrLoad.dispose();
            }
        });

        load.addActionListener(new ActionListener() {
            /**
             * method that increments the number of players
             * @param actionEvent
             */
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                try {

                    String path = JOptionPane.showInputDialog("Path to game version or load a saved file");
                    Game game = Game.readSAX(new File(path));
                    MonopolyFrame mf = new MonopolyFrame(0 ,0, game);
                    newGameOrLoad.dispose();


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        buttons.add(newGame);
        buttons.add(load);

        newGameOrLoad.add(q);
        newGameOrLoad.add(buttons);

        newGameOrLoad.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        newGameOrLoad.setVisible(true);

    }

    /**
     * Getter method of game
     * @return game of type Game
     */
    public Game getGame() {
        return game;
    }
}
