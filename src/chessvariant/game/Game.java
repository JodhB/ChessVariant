package chessvariant.game;

import chessvariant.domain.Board;
import chessvariant.domain.Move;
import chessvariant.domain.Square;

import java.awt.*;

public class Game {
    private Board board;
    private Square selected;
    private Component component;

    public Game() {
        board = new Board();
        selected = null;
    }

    public void sendInput(int x, int y) {
        Square clicked = board.getSquare(x, y);
        if (selected == null) {
            if (clicked.getPiece() != null && clicked.getPiece().isWhite() == board.isWhiteToMove()) {
                selected = clicked;
            }
        }
        else {
            Move move = new Move(selected, clicked);
            if (board.isValidMove(move)) {
                board.executeMove(move);
                selected = null;
                component.paint(component.getGraphics());
                Move aiMove = new AI(this).bestMove();
                board.executeMove(aiMove);
            } else if (clicked.getPiece() != null && clicked.getPiece().isWhite() == board.isWhiteToMove()) {
                selected = clicked;
            }
        }
    }

    public Board getBoard() {
        return board;
    }

    public Square getSelected() {
        return selected;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

}
