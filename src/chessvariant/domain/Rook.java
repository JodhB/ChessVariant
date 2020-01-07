package chessvariant.domain;

import javax.swing.ImageIcon;

public class Rook extends Piece {

    public Rook(boolean isWhite) {
        super(isWhite);
        this.symbol = 'R';
        if (isWhite) {
            this.value = 59;
            this.image = new ImageIcon("src/chessvariant/gui/pieceimages/wR.png");
        } else {
            this.value = -59;
            this.image = new ImageIcon("src/chessvariant/gui/pieceimages/bR.png");
        }
    }

    @Override
    public boolean isValidMove(Move move) {
        Square from = move.from();
        Square to = move.to();

        if (to.getPiece() != null) {
            return false;
        }

        return from.getX() == to.getX() || from.getY() == to.getY();
    }

    @Override
    public boolean isValidCapture(Move move) {
        Square from = move.from();
        Square to = move.to();

        if (to.getPiece() == null || to.getPiece().isWhite() == from.getPiece().isWhite()) { // no piece or piece of same colour
            return false;
        }

        return from.getX() == to.getX() || from.getY() == to.getY();
    }

}
