package chessvariant.domain;

import javax.swing.ImageIcon;

public class Archbishop extends Piece {

    public Archbishop(boolean isWhite) {
        super(isWhite);
        this.symbol = 'A';
        if (isWhite) {
            this.value = 98;
            this.image = new ImageIcon("src/chessvariant/gui/pieceimages/wA.png");
        } else {
            this.value = -98;
            this.image = new ImageIcon("src/chessvariant/gui/pieceimages/bA.png");
        }
    }

    @Override
    public boolean isValidMove(Move move) {
        return new Knight(this.isWhite()).isValidMove(move) || new Bishop(this.isWhite()).isValidMove(move);
    }

    @Override
    public boolean isValidCapture(Move move) {
        return new Knight(this.isWhite()).isValidCapture(move) || new Bishop(this.isWhite()).isValidCapture(move);
    }

}
