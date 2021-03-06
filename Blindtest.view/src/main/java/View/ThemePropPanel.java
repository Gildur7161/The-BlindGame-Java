/*
 * @author Amaury Chabane
 */
package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.swing.JTextField;

import Contract.IEntity;

/**
 * The Class ThemePropPanel.
 *
 * @author Amaury Chabane
 */
public class ThemePropPanel extends MyPanel {

    /** The title field. */
    private JTextField titleField = null;

    /** The composer field. */
    private JTextField composerField = null;

    /** The type field. */
    private JTextField typeField = null;

    /** The time code field. */
    private JTextField timeCodeField = null;

    /** The release field. */
    private JTextField releaseField = null;

    /** The infos field. */
    private JTextField infosField = null;

    private JTextField ytUrlField = null;

    /** The activate. */
    boolean activate = false;

    /** The theme. */
    private int theme = 0;

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -359666814951181068L;

    /** The audio input stream. */
    private AudioInputStream audioInputStream = null;

    /** The original show index. */
    private int originalShowIndex = 0;

    /**
     * Instantiates a new theme prop panel.
     *
     * @param viewFrame
     *                              the view frame
     * @param themeIndex
     *                              the theme index
     * @param originalShowIndex
     *                              the original show index
     */
    public ThemePropPanel(ViewFrame viewFrame, int themeIndex, int originalShowIndex) {
        super(viewFrame);
        MouseInputThemeProp mouseInput = new MouseInputThemeProp(this);
        this.setTheme(themeIndex);
        this.setOriginalShowIndex(originalShowIndex);

        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.addMouseListener(mouseInput);
        this.addMouseMotionListener(mouseInput);

        this.setLayout(null);
        int fieldHeightFactor = ((this.getViewFrame().getHeight() / 16) * 2);
        this.setTitleField(this.createTextField(this.getTheme().getTitle(),
                (int) (this.getViewFrame().getWidth() / 51.2), (int) (this.getViewFrame().getHeight() / 14.4),
                (int) (this.getViewFrame().getWidth() / 1.8), this.getViewFrame().getHeight() / 18));
        this.setComposerField(this.createTextField(this.getTheme().getComposer(),
                (int) (this.getViewFrame().getWidth() / 51.2), this.getTitleField().getBounds().y + fieldHeightFactor,
                (int) (this.getViewFrame().getWidth() / 1.8), this.getViewFrame().getHeight() / 18));
        this.setTypeField(this.createTextField(this.getTheme().getType(), (int) (this.getViewFrame().getWidth() / 51.2),
                this.getComposerField().getBounds().y + fieldHeightFactor, (int) (this.getViewFrame().getWidth() / 1.8),
                this.getViewFrame().getHeight() / 18));
        this.setTimeCodeField(this.createTextField(Integer.toString(this.getTheme().getTimecode()),
                (int) (this.getViewFrame().getWidth() / 51.2), this.getTypeField().getBounds().y + fieldHeightFactor,
                (int) (this.getViewFrame().getWidth() / 1.8), this.getViewFrame().getHeight() / 18));
        this.setReleaseField(
                this.createTextField(this.getTheme().getReleaseDate(), (int) (this.getViewFrame().getWidth() / 51.2),
                        this.getTimeCodeField().getBounds().y + fieldHeightFactor,
                        (int) (this.getViewFrame().getWidth() / 1.8), this.getViewFrame().getHeight() / 18));
        this.setInfosField(this.createTextField(this.getTheme().getInfos(),
                (int) (this.getViewFrame().getWidth() / 51.2), this.getReleaseField().getBounds().y + fieldHeightFactor,
                (int) (this.getViewFrame().getWidth() / 1.8), this.getViewFrame().getHeight() / 18));

        if (this.getTheme().isHasError()) {
            this.setYtUrlField(this.createTextField("",
                    (int) ((this.getViewFrame().getWidth() / 51.2) + (this.getViewFrame().getWidth() / 7.2)),
                    this.getInfosField().getBounds().y + (fieldHeightFactor / 2),
                    (int) ((this.getViewFrame().getWidth() / 2.4)), this.getViewFrame().getHeight() / 18));
        }
    }

    /**
     * Draw theme.
     *
     * @param graphics
     *                     the graphics
     * @param theme
     *                     the theme
     */
    public void drawTheme(Graphics2D graphics, IEntity theme) {
        int metaXStart = (int) (this.getWidth() / 51.2);
        int titleHeight = this.getTitleField().getBounds().y - graphics.getFont().getSize();
        int composerHeight = this.getComposerField().getBounds().y - graphics.getFont().getSize();
        int typeHeight = this.getTypeField().getBounds().y - graphics.getFont().getSize();
        int timecodeHeight = this.getTimeCodeField().getBounds().y - graphics.getFont().getSize();
        int dateHeight = this.getReleaseField().getBounds().y - graphics.getFont().getSize();
        int infosHeight = this.getInfosField().getBounds().y - graphics.getFont().getSize();
        Font font = new Font(this.getFontName(), Font.BOLD, (int) (this.getHeight() / 28.8));
        graphics.setFont(font);
        graphics.setColor(Color.BLACK);

        int border = this.getHeight() / 240;
        int rectX = (int) (this.getWidth() - (this.getWidth() / 51.2)
                - ((this.getHeight() - (this.getWidth() / 25.6)) * 0.75));
        int rectY = (int) (this.getWidth() / 51.2);
        int rectW = (int) ((this.getHeight() - (this.getWidth() / 25.6)) * 0.75);
        int rectH = (int) (this.getHeight() - (this.getWidth() / 25.6));
        if (new File(theme.getCover()).exists()) {
            Dimension imgDim = this.scaleImageDimensions(theme.getCoverImage(), rectW, rectH);
            if ((theme.getResizedCoverImage() == null)
                    || (theme.getResizedCoverImage().getWidth(null) != (int) imgDim.getWidth())
                    || (theme.getResizedCoverImage().getHeight(null) != (int) imgDim.getHeight())) {
                theme.setResizedCoverImage(theme.getCoverImage().getScaledInstance((int) imgDim.getWidth(),
                        (int) imgDim.getHeight(), Image.SCALE_SMOOTH));
            }
            rectX = (int) ((rectX + (rectW / 2)) - (imgDim.getWidth() / 2));
            rectY = (int) ((rectY + (rectH / 2)) - (imgDim.getHeight() / 2));
            rectW = (int) imgDim.getWidth();
            rectH = (int) imgDim.getHeight();

            this.drawImageRoundedCorners(graphics, theme.getResizedCoverImage(), 15, rectX, rectY, rectW, rectH);
            // graphics.drawImage(theme.getResizedCoverImage(), rectX, rectY, rectW, rectH,
            // null);
        } else {
            graphics.setColor(Color.BLACK);
            graphics.fill(new Rectangle(rectX - border, rectY - border, rectW + (border * 2), rectH + (border * 2)));
            graphics.setBackground(Color.GRAY);
            graphics.clearRect(rectX, rectY, rectW, rectH);
            graphics.setColor(Color.BLACK);
            this.drawCenteredString(graphics, "Image Not Found", rectX, rectY, rectW, rectH);
        }
        graphics.drawString("Title :", metaXStart, titleHeight);
        graphics.drawString("Composer :", metaXStart, composerHeight);
        graphics.drawString("Type :", metaXStart, typeHeight);
        graphics.drawString("Timecode :", metaXStart, timecodeHeight);
        graphics.drawString("Release Date :", metaXStart, dateHeight);
        graphics.drawString("Infos :", metaXStart, infosHeight);

        if (this.getTheme().isHasError()) {
            int ytUrlHeight = this.getYtUrlField().getBounds().y + graphics.getFont().getSize();
            graphics.drawString("Yt Video Url :", metaXStart, ytUrlHeight);
        }
    }

    /**
     * Paint component.
     *
     * @param g
     *              the g
     */
    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        int menuX = (int) (this.getWidth() / 51.2);
        int btnY = (int) (this.getHeight() - (this.getWidth() / 51.2) - (this.getHeight() / 10.2857143));
        int btnW = this.getWidth() / 12;
        int btnH = this.getHeight() / 14;
        int playX = (int) (this.getWidth() / 51.2) + menuX + btnW;
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        this.drawGradientPaint(graphics);
        this.drawTheme(graphics, this.getTheme());

        this.drawButton(graphics, "Menu", menuX, btnY, btnW, btnH);

        if (this.getTheme().isHasError()) {
            this.drawButton(graphics, " Download ", playX, btnY, btnW + (this.getWidth() / 50), btnH);
        } else {
            if ((this.getViewFrame().getClip() == null) || !this.getViewFrame().getClip().isActive()) {
                this.drawButton(graphics, "Play", playX, btnY, btnW, btnH);
            } else {
                this.drawButton(graphics, "" + (this.getViewFrame().getClip().getMicrosecondPosition() / 1000000),
                        playX, btnY, btnW, btnH);
            }
        }

        try {
            Thread.sleep((long) 16.6666667);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.repaint();
    }

    /**
     * Gets the theme.
     *
     * @return the theme
     */
    public IEntity getTheme() {
        return this.getViewFrame().getModel().getThemes().get(this.theme);
    }

    /**
     * Sets the theme.
     *
     * @param theme
     *                  the new theme
     */
    public void setTheme(int theme) {
        this.theme = theme;
    }

    /**
     * Gets the title field.
     *
     * @return the title field
     */
    public JTextField getTitleField() {
        return this.titleField;
    }

    /**
     * Sets the title field.
     *
     * @param titleField
     *                       the new title field
     */
    public void setTitleField(JTextField titleField) {
        this.titleField = titleField;
    }

    /**
     * Gets the composer field.
     *
     * @return the composer field
     */
    public JTextField getComposerField() {
        return this.composerField;
    }

    /**
     * Sets the composer field.
     *
     * @param composerField
     *                          the new composer field
     */
    public void setComposerField(JTextField composerField) {
        this.composerField = composerField;
    }

    /**
     * Gets the type field.
     *
     * @return the type field
     */
    public JTextField getTypeField() {
        return this.typeField;
    }

    /**
     * Sets the type field.
     *
     * @param typeField
     *                      the new type field
     */
    public void setTypeField(JTextField typeField) {
        this.typeField = typeField;
    }

    /**
     * Gets the time code field.
     *
     * @return the time code field
     */
    public JTextField getTimeCodeField() {
        return this.timeCodeField;
    }

    /**
     * Sets the time code field.
     *
     * @param timeCodeField
     *                          the new time code field
     */
    public void setTimeCodeField(JTextField timeCodeField) {
        this.timeCodeField = timeCodeField;
    }

    /**
     * Gets the release field.
     *
     * @return the release field
     */
    public JTextField getReleaseField() {
        return this.releaseField;
    }

    /**
     * Sets the release field.
     *
     * @param releaseField
     *                         the new release field
     */
    public void setReleaseField(JTextField releaseField) {
        this.releaseField = releaseField;
    }

    /**
     * Gets the infos field.
     *
     * @return the infos field
     */
    public JTextField getInfosField() {
        return this.infosField;
    }

    /**
     * Sets the infos field.
     *
     * @param infosField
     *                       the new infos field
     */
    public void setInfosField(JTextField infosField) {
        this.infosField = infosField;
    }

    /**
     * Gets the audio input stream.
     *
     * @return the audio input stream
     */
    public AudioInputStream getAudioInputStream() {
        return this.audioInputStream;
    }

    /**
     * Sets the audio input stream.
     *
     * @param audioInputStream
     *                             the new audio input stream
     */
    public void setAudioInputStream(AudioInputStream audioInputStream) {
        this.audioInputStream = audioInputStream;
    }

    /**
     * Gets the original show index.
     *
     * @return the original show index
     */
    public int getOriginalShowIndex() {
        return this.originalShowIndex;
    }

    /**
     * Sets the original show index.
     *
     * @param originalShowIndex
     *                              the new original show index
     */
    public void setOriginalShowIndex(int originalShowIndex) {
        this.originalShowIndex = originalShowIndex;
    }

    public JTextField getYtUrlField() {
        return this.ytUrlField;
    }

    public void setYtUrlField(JTextField ytUrlField) {
        this.ytUrlField = ytUrlField;
    }

}
