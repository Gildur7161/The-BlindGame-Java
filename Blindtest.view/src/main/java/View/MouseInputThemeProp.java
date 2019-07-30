/*
 *
 */
package View;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * The Class MouseInputGame.
 *
 * @author Amaury Chabane
 */
public class MouseInputThemeProp implements MouseListener, MouseMotionListener {

    /** The view panel. */
    private ThemePropPanel themePropPanel = null;

    /**
     * Instantiates a new mouse input game.
     *
     * @param viewPanel
     *                      the view panel
     */
    public MouseInputThemeProp(ThemePropPanel themePropPanel) {
        this.themePropPanel = themePropPanel;
    }

    /**
     * Mouse clicked.
     *
     * @param e
     *              the e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    /**
     * Mouse pressed.
     *
     * @param e
     *              the e
     */
    @Override
    public void mousePressed(MouseEvent e) {

        int mx = e.getX();
        int my = e.getY();

        int quitX = (int) (this.getThemePropPanel().getWidth() / 51.2);
        int btnY = (int) (this.getThemePropPanel().getHeight() - (this.getThemePropPanel().getWidth() / 51.2)
                - (this.getThemePropPanel().getHeight() / 10.2857143));
        int btnW = this.getThemePropPanel().getWidth() / 10;
        int btnH = this.getThemePropPanel().getHeight() / 12;
        int menuX = (int) (this.getThemePropPanel().getWidth() / 51.2) + quitX + btnW;
        if ((my >= btnY) && (my <= (btnY + btnH))) {
            if ((mx >= quitX) && (mx <= (quitX + btnW))) {
                System.exit(0);
            } else if ((mx >= menuX) && (mx <= (menuX + btnW))) {
                this.getThemePropPanel().getViewFrame().getModel().loadTypes();
                this.getThemePropPanel().getViewFrame().getModel().loadFolders();
                this.getThemePropPanel().getViewFrame().getModel().fillThemesList();
                this.getThemePropPanel().getViewFrame()
                        .setContentPane(new MenuPanel(this.getThemePropPanel().getViewFrame()));
                this.getThemePropPanel().getViewFrame().revalidate();
            }
        }
    }

    /**
     * Gets the view panel.
     *
     * @return the view panel
     */
    private ThemePropPanel getThemePropPanel() {
        return this.themePropPanel;
    }

    /**
     * Mouse released.
     *
     * @param e
     *              the e
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    /**
     * Mouse entered.
     *
     * @param e
     *              the e
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    /**
     * Mouse exited.
     *
     * @param e
     *              the e
     */
    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    /**
     * Mouse dragged.
     *
     * @param e
     *              the e
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    /**
     * Mouse moved.
     *
     * @param e
     *              the e
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        int btnY = (int) ((this.getThemePropPanel().getHeight() - (this.getThemePropPanel().getWidth() / 51.2)
                - (this.getThemePropPanel().getHeight() / 10.2857143)) - 200);
        if (e.getY() >= btnY) {
            this.getThemePropPanel().repaint(0, btnY, this.getThemePropPanel().getWidth(),
                    this.getThemePropPanel().getHeight());
        }
    }
}