package chessvariant.gui;

import chessvariant.domain.Move;
import chessvariant.domain.Piece;
import chessvariant.domain.Square;
import chessvariant.game.Game;

import javax.swing.*;
import java.awt.*;

public class DrawingBoard extends JPanel {
    private Game game;

    public DrawingBoard(Game game) {
        this.game = game;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Square[][] squares = game.getBoard().getSquares();
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 8; y++) {
                drawSquare(g, x, y, new Color(240, 217, 181), new Color(181, 136, 99));
            }
        }

        if (game.getSelected() != null) {
            int x = game.getSelected().getX();
            int y = game.getSelected().getY();
            drawSquare(g, x, y, new Color(120, 120, 120), new Color(60, 60, 60));

            for(Move move: game.getBoard().allPossibleMovesFromSquare(game.getSelected())) {
                drawSquare(g, move.to().getX(), move.to().getY(), new Color(120, 120, 120), new Color(40, 40, 40));
            }
        }
    }

    private void drawSquare(Graphics g, int x, int y, Color lightColor, Color darkColor) {
        Square[][] squares = game.getBoard().getSquares();

        if ((x + y) % 2 == 0) {
            g.setColor(lightColor);
        } else  {
            g.setColor(darkColor);
        }

        g.fillRect(x * 50, y * 50, 50, 50);

        Piece piece = squares[x][y].getPiece();
        if (piece != null) {
            piece.getImage().paintIcon(this, g, x * 50, y * 50);
        }
    }
}
