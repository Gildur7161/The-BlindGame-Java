/*
 * @author Amaury Chabane
 */
package View;

import java.awt.HeadlessException;
import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

import Contract.IControllerMain;
import Contract.IModel;

/**
 * The Class ViewFrame.
 *
 * @author Amaury Chabane
 */
public class ViewFrame extends JFrame implements Observer {

    /** The Constant ICON. */
    private final static String ICON = "bin\\icon.png";

    /** The clip. */
    private Clip clip = null;

    /** The current theme index. */
    private int currentThemeIndex = 9999;

    /** The Constant FRAMEWIDTH. */
    private static final int FRAMEWIDTH = 1280;

    /** The Constant FRAMEHEIGHT. */
    private static final int FRAMEHEIGHT = 720;

    /** The model. */
    private IModel model;

    /** The controller. */
    IControllerMain controller;

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -697358409737458175L;

    /**
     * Instantiates a new view frame.
     *
     * @param model the model
     * @param title the title
     * @throws HeadlessException the headless exception
     */
    public ViewFrame(final IModel model, final String title) throws HeadlessException {
        super(title);
        this.buildViewFrame(model);
    }

    /**
     * Start loading screen.
     */
    public void startLoadingScreen() {
        this.setContentPane(new LoadPanel(this));
        this.revalidate();
    }

    /**
     * Stop loading screen.
     *
     * @param panel the panel
     */
    @SuppressWarnings("deprecation")
    public void stopLoadingScreen(MyPanel panel) {
        ((LoadPanel) this.getContentPane()).getRotate().stop();
        this.setContentPane(panel);
        this.revalidate();
    }

    /**
     * Play music.
     *
     * @param filePath the file path
     * @param timeCode the time code
     * @param panel the panel
     */
    public void playMusic(String filePath, int timeCode, MyPanel panel) {
        this.startLoadingScreen();

        AudioInputStream ais = null;
        if (filePath.toLowerCase().endsWith(".mp3")) {

            InputStream inputStream;
            // open stream
            AudioInputStream mp3Stream = null;
            try {
                inputStream = new FileInputStream(filePath);
                BufferedInputStream bis = new BufferedInputStream(inputStream);
                mp3Stream = AudioSystem.getAudioInputStream(bis);
            } catch (UnsupportedAudioFileException | IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            AudioFormat sourceFormat = mp3Stream.getFormat();
            // create audio format object for the desired stream/audio format
            // this is *not* the same as the file format (wav)
            AudioFormat convertFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, sourceFormat.getSampleRate(),
                    16, sourceFormat.getChannels(), sourceFormat.getChannels() * 2, sourceFormat.getSampleRate(),
                    false);
            // create stream that delivers the desired format
            ais = AudioSystem.getAudioInputStream(convertFormat, mp3Stream);

        } else {
            try {
                ais = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
            } catch (UnsupportedAudioFileException | IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        try {
            this.setClip(AudioSystem.getClip());
            this.getClip().open(ais);
            FloatControl gainControl = (FloatControl) this.getClip().getControl(FloatControl.Type.MASTER_GAIN);
            double gain = 1;
            float dB = (float) ((Math.log(gain) / Math.log(10.0)) * 20.0);
            gainControl.setValue(dB);
            this.getClip().setMicrosecondPosition(timeCode * 1000000);
            this.stopLoadingScreen(panel);
            this.getClip().start();
            this.getClip().loop(Clip.LOOP_CONTINUOUSLY);
        } catch (LineUnavailableException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * Stop music.
     */
    public void stopMusic() {
        try {
            this.getClip().stop();
        } catch (Exception e) {
        }
    }

    /**
     * Gets the clip.
     *
     * @return the clip
     */
    public Clip getClip() {
        return this.clip;
    }

    /**
     * Sets the clip.
     *
     * @param clip the new clip
     */
    public void setClip(Clip clip) {
        this.clip = clip;
    }

    /**
     * Gets the controller.
     *
     * @return the controller
     */
    IControllerMain getController() {
        return this.controller;
    }

    /**
     * Sets the controller.
     *
     * @param controller the new controller
     */
    public void setController(final IControllerMain controller) {
        this.controller = controller;
        this.getController().getObservable().addObserver(this);
    }

    /**
     * Gets the model.
     *
     * @return the model
     */
    public IModel getModel() {
        return this.model;
    }

    /**
     * Sets the model.
     *
     * @param model the new model
     */
    void setModel(final IModel model) {
        this.model = model;
    }

    /**
     * Load image.
     *
     * @param path the path
     * @return the image
     */
    public Image loadImage(String path) {
        Image img = null;
        try {
            img = ImageIO.read(new File(path));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return img;
    }

    /**
     * Builds the view frame.
     *
     * @param model the model
     */
    private void buildViewFrame(final IModel model) {
        this.setModel(model);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setSize(FRAMEWIDTH, FRAMEHEIGHT);
        this.setBounds(0, 0, FRAMEWIDTH, FRAMEHEIGHT);
        this.setIconImage(this.loadImage(ICON));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); // fullscreen
        this.setUndecorated(true); // truly fullscreen
        this.setContentPane(new MenuPanel(this, 0));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * Update.
     *
     * @param o the o
     * @param arg the arg
     */
    @Override
    public void update(Observable o, Object arg) {
        // TODO Auto-generated method stub
        this.getContentPane().repaint();
        if ((this.getContentPane() instanceof ViewPanel) && ((ViewPanel) this.getContentPane()).isPaused()) {
            this.getController().setTimeLeft(this.getController().getTimeLeft() + 1);
        }
        if (this.getController().getThemeIndex() != this.getCurrentThemeIndex()) {
            this.stopMusic();
            this.playMusic(this.getController().getTheme().getFile(), this.getController().getTheme().getTimecode(),
                    new ViewPanel(this));
            this.setCurrentThemeIndex(this.getController().getThemeIndex());
        }
    }

    /**
     * Gets the current theme index.
     *
     * @return the current theme index
     */
    public int getCurrentThemeIndex() {
        return this.currentThemeIndex;
    }

    /**
     * Sets the current theme index.
     *
     * @param currentThemeIndex the new current theme index
     */
    public void setCurrentThemeIndex(int currentThemeIndex) {
        this.currentThemeIndex = currentThemeIndex;
    }
}
