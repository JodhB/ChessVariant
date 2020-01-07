package chessvariant.domain;

import javax.swing.ImageIcon;

public class Princess extends Piece {

    public Princess(boolean isWhite) {
        super(isWhite);
        this.symbol = 'q';
        if (isWhite) {
            this.value = 65;
            this.image = new ImageIcon("src/chessvariant/gui/pieceimages/wPQ.png");
        } else {
            this.value = -65;
            this.image = new ImageIcon("src/chessvariant/gui/pieceimages/bPQ.png");
        }
    }

    @Override
    public boolean isValidMove(Move move) {
        Square from = move.from();

        if ((from.getX() + from.getY()) % 2 == 0) { // on light square
            return new King(this.isWhite()).isValidMove(move) || new Bishop(this.isWhite()).isValidMove(move);
        }
        else {
            return new King(this.isWhite()).isValidMove(move) || new Rook(this.isWhite()).isValidMove(move);
        }
    }

    @Override
    public boolean isValidCapture(Move move) {
        Square from = move.from();

        if ((from.getX() + from.getY()) % 2 == 0) { // on light square
            return new King(this.isWhite()).isValidCapture(move) || new Bishop(this.isWhite()).isValidCapture(move);
        }
        else {
            return new King(this.isWhite()).isValidCapture(move) || new Rook(this.isWhite()).isValidCapture(move);
        }
    }
}