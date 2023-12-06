package goryachev.fx;

import goryachev.common.log.Log;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.util.Duration;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/FxTimer.class */
public class FxTimer {
    protected static final Log log = Log.get("FxTimer");
    private final Runnable action;
    private final Timeline timeline;

    public FxTimer(Duration delay, Runnable action) {
        this.action = action;
        this.timeline = new Timeline(new KeyFrame(delay, ev -> {
            fire();
        }, new KeyValue[0]));
        this.timeline.setCycleCount(1);
    }

    public FxTimer(Duration initialDelay, Duration delay, Runnable action) {
        this.action = action;
        this.timeline = new Timeline(new KeyFrame(delay, ev -> {
            fire();
        }, new KeyValue[0]));
        this.timeline.setDelay(initialDelay);
        this.timeline.setCycleCount(-1);
    }

    public FxTimer(int delayMillis, Runnable action) {
        this(Duration.millis(delayMillis), action);
    }

    public FxTimer(int initialDelay, int delay, Runnable action) {
        this(Duration.millis(initialDelay), Duration.millis(delay), action);
    }

    public void start() {
        this.timeline.play();
    }

    public void stop() {
        this.timeline.stop();
    }

    public void restart() {
        this.timeline.stop();
        this.timeline.play();
    }

    protected void fire() {
        try {
            this.action.run();
        } catch (Throwable e) {
            log.error("action.run", e);
        }
    }
}
