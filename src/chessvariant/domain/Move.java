package chessvariant.domain;

public class Move {
    private Square from;
    private Square to;

    public Move(Square from, Square to) {
        this.from = from;
        this.to = to;
    }

    public Square from() {
        return from;
    }

    public Square to() {
        return to;
    }
}
