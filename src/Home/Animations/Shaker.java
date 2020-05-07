package Home.Animations;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Shaker {
    private TranslateTransition translateTransition;

    public Shaker(Node node) {
        translateTransition = new TranslateTransition(Duration.millis(50));
        translateTransition.setFromX(0f);
        translateTransition.setByX(10f);
        translateTransition.setFromY(39f);
        translateTransition.setByY(0f);
        translateTransition.setCycleCount(3);
        translateTransition.setAutoReverse(true);

    }
    public void shake(){
        translateTransition.playFromStart();
    }

}
