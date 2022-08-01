import javax.swing.Timer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/** Place a speaker next to your annoying neighbours wall and run this script for some fun!
 * Leigh Hobson 2022
 * */
public class Main extends Application {
    @Override
    public void start(Stage stage) {}

    static int timerValue = 0;
    static int waitTimerValue = 0;
    static Timer timer;
    static Timer waitTimer;
    static int sampleToPlay;
    static boolean waiting;

    public static void main(String[] args) {
        int i = 0;

        while (i==0) { // run forever muhahahahah!
            int[] values = randomTimerGenerator();
            createControlTimer(values[0], values[1]);
        }
    }
    public static void setTimer() {
        timer = new Timer(1000, e -> {
            timerValue++;
            playSound();
        });
    }

    public static void setWaitTimer() {
        waitTimer = new Timer(1000, e -> waitTimerValue++);
    }
    public static void playSound() {
        int max = 11;
        int min = 1;
        int range = max - min + 1;
        sampleToPlay = (int)(Math.random() * range) + min;

        Media m = new Media("file:///" + System.getProperty("user.dir").replace('\\', '/') + "/samples/" + sampleToPlay + ".mp3");
        MediaPlayer player = new MediaPlayer(m);
        player.play();
    }

    public static int[] randomTimerGenerator(){
        int maxBark = 60;
        int maxWait = 350;
        int min = 10;
        int rangeBark = maxBark - min + 1;
        int rangeWait = maxWait - min + 1;
        int duration = (int)(Math.random() * rangeBark) + min;
        int waitTime = (int)(Math.random() * rangeWait) + min;

        System.out.println("duration =  " + duration + " seconds...");
        System.out.println("waitTime =  " + waitTime + " seconds...");

        return new int[]{duration, waitTime};
        }

    public static void createControlTimer(int duration, int waitTime) {

    System.out.println("Starting audio, will yap for " + duration + " seconds...");
    if (!waiting) {
        setTimer();
        timer.start();

        while (timerValue <= duration) {
            System.out.println("timervalue = " + timerValue + " and valueBarkDuration(duration) = " + duration + " and playing sample " + sampleToPlay + ".mp3" );
        }
        timer.stop();
        timerValue = 0;
        waiting = true;
    } else {
        System.out.println("Stopping audio, next yapping session in " + waitTime + " seconds!");
        setWaitTimer();
        waitTimer.start();
        while (waitTimerValue <= waitTime) {
            System.out.println("waitTimervalue = " + waitTimerValue + " and valueWait(waitTime) = " + waitTime);
        }
        waitTimer.stop();
        waitTimerValue = 0;
        waiting = false;
    }
  }
}
