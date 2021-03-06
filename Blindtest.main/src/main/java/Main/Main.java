/*
 * @author Amaury Chabane
 */
package Main;

import Controller.ControllerMain;
import Model.Model;
import View.View;

/**
 * The Class Main.
 *
 * @author Amaury Chabane
 */
public class Main {

    /**
     * The main method.
     *
     * @param args
     *                 the arguments
     */
    public static void main(String[] args) {

        Model model = new Model();
        View view = new View(model);
        ControllerMain controller = new ControllerMain(view, model);
        view.setController(controller);
    }

}
