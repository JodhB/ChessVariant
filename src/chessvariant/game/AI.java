package chessvariant.game;

import chessvariant.domain.Board;
import chessvariant.domain.Move;
import chessvariant.domain.Piece;

public class AI {
    private Game game;
    private Board board;

    public AI(Game game) {
        this.game = game;
        this.board = game.getBoard();
    }

    public Move bestMove() {
        Move bestMove = null;
        int bestValue = Integer.MAX_VALUE;

        for (Move move: board.allPossibleMoves()) {
            Piece fromPiece = move.from().getPiece();
            Piece toPiece = move.to().getPiece();
            board.executeMove(move); // make move to evaluate position from there

            int moveValue = moveValue(2, Integer.MIN_VALUE, Integer.MAX_VALUE);
            if (moveValue < bestValue) {
                bestValue = moveValue;
                bestMove = move;
            }

            board.undoMove(move, fromPiece, toPiece);
        }

        return bestMove;
    }

    //minimax with alpha beta pruning
    private int moveValue(int depth, int alpha, int beta) {
        if (depth == 0) {
            return game.getBoard().evaluatePosition();
        }

        if (board.isWhiteToMove()) {
            int bestValue = Integer.MIN_VALUE;

            for (Move move: board.allPossibleMoves()) {
                Piece fromPiece = move.from().getPiece();
                Piece toPiece = move.to().getPiece();
                board.executeMove(move); // make move to evaluate position from there

                bestValue = Math.max(bestValue, moveValue(depth - 1, alpha, beta));

                board.undoMove(move, fromPiece, toPiece);

                alpha = Math.max(alpha, bestValue);
                if (beta <= alpha) {
                    return bestValue;
                }
            }

            return bestValue;
        } else {
            int bestValue = Integer.MAX_VALUE;

            for (Move move: board.allPossibleMoves()) {
                Piece fromPiece = move.from().getPiece();
                Piece toPiece = move.to().getPiece();
                board.executeMove(move); // make move to evaluate position from there

                bestValue = Math.min(bestValue, moveValue(depth - 1, alpha, beta));

                board.undoMove(move, fromPiece, toPiece);

                beta = Math.min(beta, bestValue);
                if (beta <= alpha) {
                    return bestValue;
                }
            }

            return bestValue;
        }
    }
}
