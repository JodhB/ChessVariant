package chessvariant;

import chessvariant.game.Game;
import chessvariant.gui.UserInterface;
import javax.swing.SwingUtilities;

public class ChessVariant {
    public static void main(String[] args) {
        Game game = new Game();

        UserInterface ui = new UserInterface(game);
        SwingUtilities.invokeLater(ui);
    }
}
