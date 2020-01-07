package chessvariant.domain;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final int COLUMNS = 10;
    private final int ROWS = 8;
    private final Piece[] BLACKPIECES = {new Rook(false), new Knight(false), new Bishop(false), new Princess(false), new Queen(false),
            new King(false), new Prince(false), new Bishop(false), new Knight(false), new Rook(false)};
    private final Piece[] WHITEPIECES = {new Rook(true), new Knight(true), new Bishop(true), new Princess(true), new Queen(true),
            new King(true), new Prince(true), new Bishop(true), new Knight(true), new Rook(true)};

    private Square[][] squares;
    private boolean whiteToMove;

    public Board() {
        squares = new Square[COLUMNS][ROWS];
        addSquares();
        addPieces();
        whiteToMove = true;
    }

    private void addSquares() {
        for (int x = 0; x < COLUMNS; x++) {
            for (int y = 0; y < ROWS; y++) {
                squares[x][y] = new Square(x, y, null);
            }
        }
    }

    private void addPieces() {
        for (int x = 0; x < COLUMNS; x++) {
            squares[x][0].setPiece(BLACKPIECES[x]);
            squares[x][1].setPiece(new Pawn(false));
            squares[x][6].setPiece(new Pawn(true));
            squares[x][7].setPiece(WHITEPIECES[x]);
        }
    }

    public Square[][] getSquares() {
        return squares;
    }

    public Square getSquare(int x, int y) {
        return squares[x][y];
    }

    public boolean isWhiteToMove() {
        return whiteToMove;
    }

    public void setWhiteToMove(boolean whiteToMove) {
        this.whiteToMove = whiteToMove;
    }

    public boolean isInCheck(boolean white) {
        Square kingSquare = getKingSquare(white);

        return isSquareTargeted(kingSquare);
    }

    private Square getKingSquare(boolean white) {
        for (int x = 0; x < COLUMNS; x++) {
            for (int y = 0; y < ROWS; y++) {
                Piece piece = squares[x][y].getPiece();
                if (piece != null && piece.getSymbol() == 'K' && piece.isWhite == white) {
                    return squares[x][y];
                }
            }
        }
        return null;
    }

    private boolean isSquareTargeted(Square square) {
        for (int x = 0; x < COLUMNS; x++) {
            for (int y = 0; y < ROWS; y++) {
                Piece piece = squares[x][y].getPiece();
                if (piece != null && piece.isValidCapture(new Move(squares[x][y], square)) && !isPieceBetween(squares[x][y], square)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isValidMove(Move move) {
        Piece piece = move.from().getPiece();

        if (piece == null ||
                piece.isWhite() != whiteToMove ||
                (!piece.isValidMove(move) && !piece.isValidCapture(move)) ||
                isPieceBetween(move.from(), move.to()) ||
                moveResultsInCheck(move)) {
            return false;
        }

        return true;
    }

    public boolean isPieceBetween(Square from, Square to) {
        if (from.getX() == to.getX() && from.getY() == to.getY()) {
            return false;
        }

        if (from.getX() == to.getX()) {
            return isPieceBetweenVertical(from, to);
        }

        if (from.getY() == to.getY()) {
            return isPieceBetweenHorizontal(from, to);
        }

        if (Math.abs(from.getX() - to.getX()) == Math.abs(from.getY() - to.getY())) {
            return isPieceBetweenDiagonal(from, to);
        }

        return false;
    }

    private boolean isPieceBetweenVertical(Square from, Square to) {
        int fromY = from.getY();
        int toY = to.getY();

        if (toY < fromY) {
            fromY = to.getY();
            toY = from.getY();
        }

        for (int y = fromY + 1; y < toY; y++) {
            Square square = squares[from.getX()][y];
            if (square.getPiece() != null) {
                return true;
            }
        }

        return false;
    }

    private boolean isPieceBetweenHorizontal(Square from, Square to) {
        int fromX = from.getX();
        int toX = to.getX();

        if (toX < fromX) {
            fromX = to.getX();
            toX = from.getX();
        }

        for (int x = fromX + 1; x < toX; x++) {
            Square square = squares[x][from.getY()];
            if (square.getPiece() != null) {
                return true;
            }
        }

        return false;
    }

    private boolean isPieceBetweenDiagonal(Square from, Square to) {
        int xDirection = (to.getX() - from.getX()) / (Math.abs(to.getX() - from.getX())); // + 1 or - 1
        int yDirection = (to.getY() - from.getY()) / (Math.abs(to.getY() - from.getY()));

        int x = from.getX() + xDirection;
        int y = from.getY() + yDirection;
        while (x != to.getX() && y != to.getY()) {
            Square square = squares[x][y];
            if (square.getPiece() != null) {
                return true;
            }
            x += xDirection;
            y += yDirection;
        }

        return false;
    }

    private boolean moveResultsInCheck (Move move) {
        Square from = move.from();
        Square to = move.to();
        Piece fromPiece = from.getPiece();
        Piece toPiece = to.getPiece();
        boolean resultsInCheck = false;

        from.setPiece(null);
        to.setPiece(fromPiece); // make move to see if it results in check

        if (isInCheck(fromPiece.isWhite())) {
            resultsInCheck = true;
        }

        from.setPiece(fromPiece); // put pieces back
        to.setPiece(toPiece);

        return resultsInCheck;
    }

    public void executeMove(Move move) {
        if (move == null) {
            return;
        }

        Square from = move.from();
        Square to = move.to();

        to.setPiece(from.getPiece());
        from.setPiece(null);

        if (to.getY() == 0 || to.getY() == 7) { // pawn promotion
            if (to.getPiece().getSymbol() == 'P') {
                to.setPiece(new Queen(to.getPiece().isWhite()));
            }
        }

        whiteToMove = !whiteToMove;
    }

    public void undoMove(Move move, Piece fromPiece, Piece toPiece) {
        move.from().setPiece(fromPiece);
        move.to().setPiece(toPiece);
        whiteToMove = !whiteToMove;
    }

    public List<Move> allPossibleMoves() {
        List<Move> moves = new ArrayList<>();

        for (int x = 0; x < COLUMNS; x++) {
            for (int y = 0; y < ROWS; y++) {
                Square square = squares[x][y];
                moves.addAll(allPossibleMovesFromSquare(square));
            }
        }

        return moves;
    }

    public List<Move> allPossibleMovesFromSquare(Square square) {
        List<Move> moves = new ArrayList<>();

        for (int x = 0; x < COLUMNS; x++) {
            for (int y = 0; y < ROWS; y++) {
                Move move = new Move(square, squares[x][y]);
                if (isValidMove(move)) {
                    moves.add(move);
                }
            }
        }

        return moves;
    }

    public int evaluatePosition() {
        int value = 0;

        for (int x = 0; x < COLUMNS; x++) {
            for (int y = 0; y < ROWS; y++) {
                Square square = squares[x][y];
                if (square.getPiece() != null) {
                    value += square.getPiece().getValue();
                }
            }
        }

        return value;
    }

}
