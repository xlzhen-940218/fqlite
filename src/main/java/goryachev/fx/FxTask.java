package goryachev.fx;

import goryachev.common.util.CTask;
import javafx.application.Platform;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/FxTask.class */
public class FxTask<T> extends CTask<T> {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // goryachev.common.util.CTask
    public void handleSuccess(T result) {
        if (this.onSuccess != null) {
            Platform.runLater(() -> {
                super.handleSuccess(result);
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // goryachev.common.util.CTask
    public void handleError(Throwable e) {
        if (this.onError == null) {
            log.error(e);
        } else {
            Platform.runLater(() -> {
                super.handleError(e);
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // goryachev.common.util.CTask
    public void handleFinish() {
        if (this.onFinish != null) {
            Platform.runLater(() -> {
                super.handleFinish();
            });
        }
    }
}
