package chessvariant.domain;

import javax.swing.ImageIcon;

public class King extends Piece {

    public King(boolean isWhite) {
        super(isWhite);
        this.symbol = 'K';
        if (isWhite) {
            this.value = 792;
            this.image = new ImageIcon("src/chessvariant/gui/pieceimages/wK.png");
        } else {
            this.value = -792;
            this.image = new ImageIcon("src/chessvariant/gui/pieceimages/bK.png");
        }
    }

    @Override
    public boolean isValidMove(Move move) {
        Square from = move.from();
        Square to = move.to();
        if (to.getPiece() != null) {
            return false;
        }

        return Math.abs(from.getX() - to.getX()) <= 1 && Math.abs(from.getY() - to.getY()) <= 1;
    }

    @Override
    public boolean isValidCapture(Move move) {
        Square from = move.from();
        Square to = move.to();
        if (to.getPiece() == null || to.getPiece().isWhite() == from.getPiece().isWhite()) { // no piece or piece of same colour
            return false;
        }

        return Math.abs(from.getX() - to.getX()) <= 1 && Math.abs(from.getY() - to.getY()) <= 1;
    }

}