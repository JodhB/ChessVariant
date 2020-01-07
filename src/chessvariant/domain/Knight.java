package chessvariant.domain;

import javax.swing.ImageIcon;

public class Knight extends Piece {

    public Knight(boolean isWhite) {
        super(isWhite);
        this.symbol = 'N';
        if (isWhite) {
            this.value = 31;
            this.image = new ImageIcon("src/chessvariant/gui/pieceimages/wN.png");
        } else {
            this.value = -31;
            this.image = new ImageIcon("src/chessvariant/gui/pieceimages/bN.png");
        }
    }

    @Override
    public boolean isValidMove(Move move) {
        Square from = move.from();
        Square to = move.to();

        if (to.getPiece() != null) {
            return false;
        }

        return (Math.abs(from.getX() - to.getX()) == 1 && Math.abs(from.getY() - to.getY()) == 2)
                || (Math.abs(from.getX() - to.getX()) == 2 && Math.abs(from.getY() - to.getY()) == 1);
    }

    @Override
    public boolean isValidCapture(Move move) {
        Square from = move.from();
        Square to = move.to();

        if (to.getPiece() == null || to.getPiece().isWhite() == from.getPiece().isWhite()) { // no piece or piece of same colour
            return false;
        }

        return (Math.abs(from.getX() - to.getX()) == 1 && Math.abs(from.getY() - to.getY()) == 2)
                || (Math.abs(from.getX() - to.getX()) == 2 && Math.abs(from.getY() - to.getY()) == 1);
    }

}