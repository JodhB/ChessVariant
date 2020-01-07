package chessvariant.domain;

import javax.swing.ImageIcon;

public class Queen extends Piece {

    public Queen(boolean isWhite) {
        super(isWhite);
        this.symbol = 'Q';
        if (isWhite) {
            this.value = 115;
            this.image = new ImageIcon("src/chessvariant/gui/pieceimages/wQ.png");
        } else {
            this.value = -115;
            this.image = new ImageIcon("src/chessvariant/gui/pieceimages/bQ.png");
        }
    }

    @Override
    public boolean isValidMove(Move move) {
        return new Rook(this.isWhite()).isValidMove(move) || new Bishop(this.isWhite()).isValidMove(move);
    }

    @Override
    public boolean isValidCapture(Move move) {
        return new Rook(this.isWhite()).isValidCapture(move) || new Bishop(this.isWhite()).isValidCapture(move);
    }

}
