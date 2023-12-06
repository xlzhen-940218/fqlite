package goryachev.fx.internal;

import java.lang.ref.WeakReference;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.util.Duration;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/internal/WeakAnimation.class */
public abstract class WeakAnimation<T> {
    private final WeakReference<T> ref;
    private final Timeline timeline;

    protected abstract void handleFrame(T t);

    public WeakAnimation(T parent, Duration period) {
        this.ref = new WeakReference<>(parent);
        this.timeline = new Timeline(new KeyFrame(period, ev -> {
            handleFramePrivate();
        }, new KeyValue[0]));
        this.timeline.setCycleCount(-1);
        this.timeline.play();
    }

    protected void handleFramePrivate() {
        T parent = this.ref.get();
        if (parent == null) {
            stop();
        } else {
            handleFrame(parent);
        }
    }

    public void stop() {
        this.timeline.stop();
    }
}
