
import java.awt.FontFormatException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;

public class Main extends Application
{
    private Canvas canvas;

    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("Test");                                                       //loads javafx setup
        canvas = new Canvas(1000, 1000);                                        //this code is mostly irrelevant and only implements the
        StackPane root = new StackPane();                                                    //javafx canvas, root and scene.
        root.getChildren().add(canvas);                                                      //the screen size and listeners are also added in here
        Scene scene = new Scene(root, 1000, 1000);
        scene.setOnKeyPressed(this::onKeyPressed);
        scene.setOnKeyReleased(this::onKeyReleased);
        primaryStage.setScene(scene);

    }

    public void onKeyPressed(KeyEvent e)
    {

    }

    public void onKeyReleased(KeyEvent e)
    {

    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
