package chessvariant.domain;

import javax.swing.ImageIcon;

public abstract class Piece {
    protected boolean isWhite;
    protected char symbol;
    protected int value;
    protected ImageIcon image;

    public Piece(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public char getSymbol() {
        return symbol;
    }

    public int getValue() {
        return value;
    }

    public ImageIcon getImage() {
        return image;
    }

    public abstract boolean isValidMove(Move move);

    public abstract boolean isValidCapture(Move move);
}
