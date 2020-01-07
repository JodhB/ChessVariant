package chessvariant.gui;

import chessvariant.game.Game;

import javax.swing.*;
import java.awt.*;

public class UserInterface implements Runnable {

    private JFrame frame;
    private Game game;
    private DrawingBoard drawingBoard;

    public UserInterface(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        frame = new JFrame();
        frame.setPreferredSize(new Dimension(515, 445));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());
        addListeners();

        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents(Container container) {
        drawingBoard = new DrawingBoard(game);
        container.add(drawingBoard);
        game.setComponent(drawingBoard);
    }

    private void addListeners() {
        frame.addMouseListener(new InputListener(drawingBoard, game));
    }

    public JFrame getFrame() {
        return frame;
    }

}
