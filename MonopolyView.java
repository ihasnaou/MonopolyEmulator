import java.util.ArrayList;

public interface MonopolyView {

    /**
     * handles the update of the model view as per the MVC pattern
     * @param game of type game
     * @param players of type arrayList of players
     * @param squares of type arrayList of squares
     */
    void handleUpdate(Game game, ArrayList<Player> players, ArrayList<Square> squares);
}
