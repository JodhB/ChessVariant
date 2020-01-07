package chessvariant.domain;

import javax.swing.ImageIcon;

public class Prince extends Piece {

    public Prince(boolean isWhite) {
        super(isWhite);
        this.symbol = 'k';
        if (isWhite) {
            this.value = 30;
            this.image = new ImageIcon("src/chessvariant/gui/pieceimages/wPK.png");
        } else {
            this.value = -30;
            this.image = new ImageIcon("src/chessvariant/gui/pieceimages/bPK.png");
        }
    }

    @Override
    public boolean isValidMove(Move move) {
        Square from = move.from();
        Square to = move.to();

        if (to.getPiece() != null) {
            return false;
        }

        if ((from.getX() + from.getY()) % 2 == 0) { // on light square
            return new King(this.isWhite()).isValidMove(move);
        }
        else {
            return (Math.abs(from.getY() - to.getY()) == 2 && Math.abs(from.getX() - to.getX()) <= 2)
            || (Math.abs(from.getX() - to.getX()) == 2 && Math.abs(from.getY() - to.getY()) <= 2);
        }
    }

    @Override
    public boolean isValidCapture(Move move) {
        Square from = move.from();
        Square to = move.to();

        if (to.getPiece() == null || to.getPiece().isWhite() == from.getPiece().isWhite()) {
            return false;
        }

        if ((from.getX() + from.getY()) % 2 == 0) { // on light square
            return new King(this.isWhite()).isValidCapture(move);
        }
        else {
            return (Math.abs(from.getY() - to.getY()) == 2 && Math.abs(from.getX() - to.getX()) <= 2)
                    || (Math.abs(from.getX() - to.getX()) == 2 && Math.abs(from.getY() - to.getY()) <= 2);
        }
    }
}
