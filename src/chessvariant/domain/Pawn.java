package chessvariant.domain;

import javax.swing.ImageIcon;

public class Pawn extends Piece {

    public Pawn(boolean isWhite) {
        super(isWhite);
        this.symbol = 'P';
        if (isWhite) {
            this.value = 10;
            this.image = new ImageIcon("src/chessvariant/gui/pieceimages/wP.png");
        } else {
            this.value = -10;
            this.image = new ImageIcon("src/chessvariant/gui/pieceimages/bP.png");
        }
    }

    @Override
    public boolean isValidMove(Move move) {
        Square from = move.from();
        Square to = move.to();

        if (to.getPiece() != null) {
            return false;
        }

        if (this.isWhite()) {
            if (from.getY() == 6 && to.getY() == 4 && to.getX() == from.getX()) {
                return true;
            }
            return to.getY() == from.getY() - 1 && to.getX() == from.getX();
        } else {
            if (from.getY() == 1 && to.getY() == 3 && to.getX() == from.getX()) {
                return true;
            }
            return to.getY() == from.getY() + 1 && to.getX() == from.getX();
        }
    }

    @Override
    public boolean isValidCapture(Move move) {
        Square from = move.from();
        Square to = move.to();

        if (to.getPiece() == null || to.getPiece().isWhite() == from.getPiece().isWhite()) { // no piece or piece of same colour
            return false;
        }

        if (this.isWhite()) {
            return to.getY() - from.getY() == -1 && Math.abs(to.getX() - from.getX()) == 1;
        } else {
            return to.getY() - from.getY() == 1 && Math.abs(to.getX() - from.getX()) == 1;
        }
    }

}
