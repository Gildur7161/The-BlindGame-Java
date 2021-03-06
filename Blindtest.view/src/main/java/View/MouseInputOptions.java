/*
 * @author Amaury Chabane
 */
package View;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JCheckBox;

/**
 * The Class MouseInputOptions.
 *
 * @author Amaury Chabane
 */
public class MouseInputOptions implements MouseListener, MouseMotionListener {

    /** The options panel. */
    private OptionsPanel optionsPanel = null;

    /**
     * Instantiates a new mouse input options.
     *
     * @param optionsPanel
     *                         the options panel
     */
    public MouseInputOptions(OptionsPanel optionsPanel) {
        this.optionsPanel = optionsPanel;
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
        ViewFrame viewframe = this.getOptionsPanel().getViewFrame();

        int mx = e.getX();
        int my = e.getY();

        int backX = (int) (this.getOptionsPanel().getWidth() - (this.getOptionsPanel().getWidth() / 51.2)
                - (this.getOptionsPanel().getWidth() / 10));
        int btnY = (int) (this.getOptionsPanel().getHeight() - (this.getOptionsPanel().getWidth() / 51.2)
                - (this.getOptionsPanel().getHeight() / 10.2857143));
        int btnW = this.getOptionsPanel().getWidth() / 12;
        int btnH = this.getOptionsPanel().getHeight() / 14;
        int nextX = (int) (this.getOptionsPanel().getWidth() - ((this.getOptionsPanel().getWidth() / 51.2) * 2)
                - (this.getOptionsPanel().getWidth() / 10) - btnW) + (btnW / 2);
        int previousX = (int) (this.getOptionsPanel().getWidth() - ((this.getOptionsPanel().getWidth() / 51.2) * 3)
                - (this.getOptionsPanel().getWidth() / 10) - btnW);

        int addX = (int) (this.getOptionsPanel().getWidth() - ((this.getOptionsPanel().getWidth() / 51.2) * 4)
                - (this.getOptionsPanel().getWidth() / 10) - (btnW * 2));

        if ((my >= btnY) && (my <= (btnY + btnH))) {
            if ((mx >= nextX) && (mx <= (nextX + btnW))) {
                if (viewframe.getController().getModel().getTypes()
                        .size() > ((this.getOptionsPanel().getShowIndex() + 16))) {
                    for (JCheckBox checkboxe : this.getOptionsPanel().getCheckboxes()) {
                        if (checkboxe.isSelected()) {
                            viewframe.getController().removeType(checkboxe.getText());
                        } else {
                            boolean checked = false;
                            System.out.println(viewframe.getController().getNotChoosenTypes());
                            for (String type : viewframe.getController().getNotChoosenTypes()) {
                                if (type.equalsIgnoreCase(checkboxe.getText())) {
                                    checked = true;
                                    break;
                                }
                            }
                            if (!checked) {
                                viewframe.getController().addType(checkboxe.getText());
                            }
                        }
                    }
                    this.getOptionsPanel().removeCheckboxes();
                    this.getOptionsPanel().setShowIndex(this.getOptionsPanel().getShowIndex() + 16);
                    this.getOptionsPanel().createCheckboxes();
                    this.getOptionsPanel().repaint();
                }
            } else if ((mx >= previousX) && (mx <= (previousX + btnW))) {
                if (this.getOptionsPanel().getShowIndex() > 0) {
                    for (JCheckBox checkboxe : this.getOptionsPanel().getCheckboxes()) {
                        if (checkboxe.isSelected()) {
                            viewframe.getController().removeType(checkboxe.getText());
                        } else {
                            boolean checked = false;
                            for (String type : viewframe.getController().getNotChoosenTypes()) {
                                if (type.equalsIgnoreCase(checkboxe.getText())) {
                                    checked = true;
                                    break;
                                }
                            }
                            if (!checked) {
                                viewframe.getController().addType(checkboxe.getText());
                            }
                        }
                    }
                    this.getOptionsPanel().removeCheckboxes();
                    this.getOptionsPanel().setShowIndex(this.getOptionsPanel().getShowIndex() - 16);
                    this.getOptionsPanel().createCheckboxes();
                    this.getOptionsPanel().repaint();
                }
            } else if ((mx >= backX) && (mx <= (backX + btnW))) {
                try {
                    viewframe.getController()
                            .setAllowedTime(Integer.parseInt(this.getOptionsPanel().getTimeField().getText()));
                } catch (NumberFormatException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                for (JCheckBox checkboxe : this.getOptionsPanel().getCheckboxes()) {
                    if (checkboxe.isSelected()) {
                        viewframe.getController().removeType(checkboxe.getText());
                    } else {
                        boolean checked = false;
                        for (String type : viewframe.getController().getNotChoosenTypes()) {
                            if (type.equalsIgnoreCase(checkboxe.getText())) {
                                checked = true;
                                break;
                            }
                        }
                        if (!checked) {
                            viewframe.getController().addType(checkboxe.getText());
                        }
                    }
                }
                viewframe.setContentPane(new MenuPanel(viewframe, 0));
                viewframe.revalidate();
            } else if ((mx >= addX) && (mx <= (addX + addX))) {
                viewframe.setContentPane(new AddThemePanel(viewframe));
                viewframe.revalidate();
            }
        }

    }

    /**
     * Gets the options panel.
     *
     * @return the options panel
     */
    private OptionsPanel getOptionsPanel() {
        return this.optionsPanel;
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
        int btnY = (int) ((this.getOptionsPanel().getHeight() - (this.getOptionsPanel().getWidth() / 51.2)
                - (this.getOptionsPanel().getHeight() / 10.2857143)) - 200);
        if (e.getY() >= btnY) {
            this.getOptionsPanel().repaint(0, btnY, this.getOptionsPanel().getWidth(),
                    this.getOptionsPanel().getHeight());
        }
    }
}
