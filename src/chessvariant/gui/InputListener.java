package chessvariant.gui;

import chessvariant.game.Game;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class InputListener implements MouseListener {
    private Component component;
    private Game game;

    public InputListener(Component component, Game game) {
        this.component = component;
        this.game = game;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        game.sendInput((e.getX() - 9) / 50, (e.getY() - 38) / 50);
        component.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
