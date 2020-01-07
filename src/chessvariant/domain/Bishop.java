package chessvariant.domain;

import javax.swing.ImageIcon;

public class Bishop extends Piece {

    public Bishop(boolean isWhite) {
        super(isWhite);
        this.symbol = 'B';
        if (isWhite) {
            this.value = 38;
            this.image = new ImageIcon("src/chessvariant/gui/pieceimages/wB.png");
        } else {
            this.value = -38;
            this.image = new ImageIcon("src/chessvariant/gui/pieceimages/bB.png");
        }
    }

    @Override
    public boolean isValidMove(Move move) {
        Square from = move.from();
        Square to = move.to();
        if (to.getPiece() != null) {
            return false;
        }

        return Math.abs(from.getX() - to.getX()) == Math.abs(from.getY() - to.getY());
    }

    @Override
    public boolean isValidCapture(Move move) {
        Square from = move.from();
        Square to = move.to();
        if (to.getPiece() == null || to.getPiece().isWhite() == from.getPiece().isWhite()) {
            return false;
        }

        return Math.abs(from.getX() - to.getX()) == Math.abs(from.getY() - to.getY());
    }

}
