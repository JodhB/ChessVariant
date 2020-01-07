package chessvariant.domain;

import javax.swing.ImageIcon;

public class Chancellor extends Piece {

    public Chancellor(boolean isWhite) {
        super(isWhite);
        this.symbol = 'C';
        if (isWhite) {
            this.value = 101;
            this.image = new ImageIcon("src/chessvariant/gui/pieceimages/wC.png");
        } else {
            this.value = -101;
            this.image = new ImageIcon("src/chessvariant/gui/pieceimages/bC.png");
        }
    }

    @Override
    public boolean isValidMove(Move move) {
        return new Knight(this.isWhite()).isValidMove(move) || new Rook(this.isWhite()).isValidMove(move);
    }

    @Override
    public boolean isValidCapture(Move move) {
        return new Knight(this.isWhite()).isValidCapture(move) || new Rook(this.isWhite()).isValidCapture(move);
    }

}
