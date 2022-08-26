import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
* @author for m4: Ilyes, Sam
*/
public class MonopolyController implements ActionListener {

    /**
     * a game of type game
     */
    private Game game;

    /**
     * A monopoly frame
     */
    private MonopolyFrame MonopolyFrame;

    /**
     * Monopoly Controller following the MVC pattern
     * @param game of type game
     * @param monopolyFrame of type monopoly frame
     */
    public MonopolyController(Game game, MonopolyFrame monopolyFrame){
        this.game = game;
        this.MonopolyFrame = monopolyFrame;
    }

    /**
     * Method that executes the intended action of each button
     * @param e of type action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equals("Quit")){
            int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?");
            switch (result) {
                case JOptionPane.YES_OPTION:
                System.exit(1);
                break;
                case JOptionPane.NO_OPTION:
                JOptionPane.getRootFrame().dispose();
                break;
                case JOptionPane.CANCEL_OPTION:
                JOptionPane.getRootFrame().dispose();
                break;
                case JOptionPane.CLOSED_OPTION:
                JOptionPane.getRootFrame().dispose();
                break;
      }
            
        }
        else if(e.getActionCommand().equals("Help")){
            JOptionPane.showMessageDialog(this.MonopolyFrame, "Click on Quit to stop the game.\nClick on Roll to roll the dice.\nYou must roll the dice to enable either the Buy or Rent Buttons.\nEither Buy or give your turn when the House you land on is not owned.\nYou must pay your due rent before proceeding if the House is owned.");
        }
        else if(e.getActionCommand().equals("Rent")){

            if(this.game.rent(this.game.getPlayers().get(Game.turn))){
                this.MonopolyFrame.getTextField().setText("<html>Player " + this.game.getPlayers().get(Game.turn).getID() + " rented the House for "
                + this.game.getSquares().get(this.game.getPlayers().get(Game.turn).getLocation()).getProperty().getRent() + " " + game.getCurrency()+ ".</html>");
                this.MonopolyFrame.getButtons()[2].setEnabled(false);
                this.MonopolyFrame.getButtons()[5].setEnabled(true);
                
            }
            else {
                this.MonopolyFrame.getTextField().setText("<html>Player " + this.game.getPlayers().get(0).getID() + " was not able to pay rent" +
                        "and therefore is kicked from the game.</html>");
                this.game.losePlayer(this.game.getPlayers().get(Game.turn));
            }

        }
        else if(e.getActionCommand().equals("Buy")){

            if(this.game.buy(this.game.getPlayers().get(Game.turn))){
                if (this.game.getSquares().get(this.game.getPlayers().get(Game.turn).getLocation()).getOwnedInSquare() == 5) {
                    this.MonopolyFrame.getTextField().setText("<html>Player " + this.game.getPlayers().get(Game.turn).getID() + " bought a hotel at "
                            + this.game.getPlayers().get(Game.turn).getLocation() +  ".<br>You can no longer buy properties there.");
                }
                this.MonopolyFrame.getTextField().setText("<html>Player " + this.game.getPlayers().get(Game.turn).getID() + " bought a house at "
                        + this.game.getPlayers().get(Game.turn).getLocation() + " for " + this.game.getSquares().get(this.game.getPlayers().get(Game.turn).getLocation()).getProperty().getPrice() + " " + game.getCurrency() + ".<br>There are now " + this.game.getSquares().get(this.game.getPlayers().get(Game.turn).getLocation()).getOwnedInSquare() + " properties owned there.</html>");
            }
            else{
                this.MonopolyFrame.getTextField().setText("<html>Player " + this.game.getPlayers().get(Game.turn).getID() + " cannot afford a new house at "
                        + this.game.getPlayers().get(Game.turn).getLocation() + ".</html>");
            }
            this.MonopolyFrame.enableButtons(false,false,false,true);

        }

        else if (e.getActionCommand().equals("Roll")){


            if (this.game.players.get(Game.turn).getJailed()) {
                this.game.players.get(Game.turn).setTurnsInJail(this.game.players.get(Game.turn).getTurnsInJail() + 1);
                this.MonopolyFrame.enableButtons(false,false,false,true);


                if (this.game.players.get(Game.turn).getTurnsInJail() >= 3) {
                    this.game.breakOut(this.game.players.get(Game.turn));
                    this.MonopolyFrame.getTextField().setText("<html>Player " + this.game.getPlayers().get(Game.turn).getID() + " has rolled a " + this.game.getNumber().get(0) + " and a " + this.game.getNumber().get(1) + " and has to pay the fine.<br>Better luck next time!</html>");
                    this.MonopolyFrame.enableButtons(false,false,true,false);

                }
                else {
                    this.MonopolyFrame.getTextField().setText("<html>Player " + this.game.getPlayers().get(Game.turn).getID() + " is still in jail. Only " + Integer.toString((this.game.getPlayers().get(Game.turn).getTurnsInJail() - 3) * -1) + " turn(s) left!");
                    this.MonopolyFrame.enableButtons(false,false,false,true);

                }
            }
            else {
                this.game.movePlayer(this.game.players.get(Game.turn));

                if(this.game.getNumber().get(2) == 1){
                    this.MonopolyFrame.getTextField().setText("<html>Player " + this.game.getPlayers().get(Game.turn).getID() + " has rolled a "
                            + this.game.getNumber().get(0) + " and a " + this.game.getNumber().get(1) + ".<br>You rolled a double, you can roll again!</html>");
                    this.MonopolyFrame.enableButtons(false,true,true,false);

                }
                else {
                    this.MonopolyFrame.getTextField().setText("<html>Player " + this.game.getPlayers().get(Game.turn).getID() + " has rolled a "
                            + this.game.getNumber().get(0) + " and a " + this.game.getNumber().get(1) + "</html>");
                    if (this.game.players.get(Game.turn).getLocation() == 24) {
                        this.game.jailPlayer(this.game.players.get(Game.turn));
                    }
                    else if (this.game.getSquares().get(this.game.getPlayers().get(Game.turn).getLocation()).getOwner().getID() == -1) {
                        this.MonopolyFrame.enableButtons(false,true,false,true);

                    }
                    else if (this.game.getSquares().get(this.game.getPlayers().get(Game.turn).getLocation()).getOwner().equals(this.game.players.get(Game.turn))) {
                        this.MonopolyFrame.enableButtons(false,true,false,true);

                    }
                    else {
                        this.MonopolyFrame.enableButtons(true,false,false,false);

                    }
                }
            }

        }

        else if (e.getActionCommand().equals("Give Turn")){
            Game.turn += 1;
            Game.turn %= this.game.players.size();
            this.game.updateViews();
            if (this.game.getPlayers().get(Game.turn).isAI()) {
                this.game.movePlayer(this.game.players.get(Game.turn)); //Start by moving  AI player

                if(this.game.getNumber().get(2) == 1){ //If AI rolls a double
                    int totalRoll = this.game.getNumber().get(0) + this.game.getNumber().get(1);
                    this.game.movePlayer(this.game.players.get(Game.turn));
                    totalRoll += this.game.getNumber().get(0) + this.game.getNumber().get(1);
                    this.MonopolyFrame.getTextField().setText("<html>AI Player " + this.game.getPlayers().get(Game.turn).getID() + " has rolled a total of " + totalRoll + " because of a double</html>");
                }
                else { //State what the AI rolled
                    this.MonopolyFrame.getTextField().setText("<html>AI Player " + this.game.getPlayers().get(Game.turn).getID() + " has rolled a " + this.game.getNumber().get(0) + " and a " + this.game.getNumber().get(0) + ".</html>");
                }

                if (this.game.getSquares().get(this.game.getPlayers().get(Game.turn).getLocation()).getOwner().getID() == -1 || (this.game.getSquares().get(this.game.getPlayers().get(Game.turn).getLocation()).getOwner().equals(this.game.getPlayers().get(Game.turn)) && this.game.getSquares().get(this.game.getPlayers().get(Game.turn).getLocation()).getOwnedInSquare() < 5)) { //AI player lands on unowned House
                    if (this.game.buy(this.game.getPlayers().get(Game.turn))) { //If it can be afforded, it is bought, else nothing happens
                        this.MonopolyFrame.getTextField().setText("<html>AI Player " + this.game.getPlayers().get(Game.turn).getID() + " bought the House at "
                                + this.game.getPlayers().get(Game.turn).getLocation() + " for " + this.game.getSquares().get(this.game.getPlayers().get(Game.turn).getLocation()).getProperty().getPrice() + " " + game.getCurrency() + ".<br>There are now " + this.game.getSquares().get(this.game.getPlayers().get(Game.turn).getLocation()).getOwnedInSquare() + " properties owned there.</html>");
                    }
                }
                else if( (this.game.getSquares().get(this.game.getPlayers().get(Game.turn).getLocation()).getOwner().getID() != Game.turn)) { //AI lands on owned House that is not his
                    if(this.game.rent(this.game.getPlayers().get(Game.turn))){ //If AI player can afford rent
                        this.MonopolyFrame.getTextField().setText("<html>AI Player " + this.game.getPlayers().get(Game.turn).getID() + " rented the House for "
                                + this.game.getSquares().get(this.game.getPlayers().get(Game.turn).getLocation()).getProperty().getRent() + " " + game.getCurrency() + "</html>");
                    }
                    else {//AI player cannot afford rent and loses the game
                        this.game.losePlayer(this.game.getPlayers().get(Game.turn));
                        this.MonopolyFrame.getTextField().setText("<html>AI Player " + this.game.getPlayers().get(0).getID() + " was not able to pay rent" +
                                "and therefore is kicked from the game</html>");
                    }
                }
                
                this.MonopolyFrame.enableButtons(false,false,false,true);

            }
            else {
                this.MonopolyFrame.enableButtons(false,false,true,false);
                this.MonopolyFrame.getTextField().setText("<html>It's Player "
                        + this.game.getPlayers().get(Game.turn).getID()
                        + "'s turn."
                        + "<br>Please roll your dice!<br></html>");
            }
        }
        else if (e.getActionCommand().equals("Save")){
            try {
                // the code below allows to you save multiple games, if you ever need to load more than one saved game
                this.MonopolyFrame.getGame().saveGame(JOptionPane.showInputDialog(MonopolyFrame,"What would you like to name your saved file (please add '.xml')"));
                this.MonopolyFrame.getTextField().setText("<html>The game has been saved.</html>");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

    }
}
