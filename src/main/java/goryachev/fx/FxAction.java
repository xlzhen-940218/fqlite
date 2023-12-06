package goryachev.fx;

import goryachev.common.log.Log;
import java.util.function.Consumer;
import javafx.beans.property.BooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleButton;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/FxAction.class */
public class FxAction implements EventHandler<ActionEvent> {
    protected static final Log log = Log.get("FxAction");
    public static final FxAction DISABLED = new FxAction((Runnable) null, false);
    private final FxBoolean selectedProperty;
    private final FxBoolean disabledProperty;
    private Runnable onAction;
    private Consumer<Boolean> onSelected;

    public FxAction(Runnable onAction, Consumer<Boolean> onSelected, boolean enabled) {
        this.selectedProperty = new FxBoolean();
        this.disabledProperty = new FxBoolean();
        this.onAction = onAction;
        this.onSelected = onSelected;
        setEnabled(enabled);
        if (onSelected != null) {
            this.selectedProperty.addListener((observableValue, prev, cur) -> {
                fireSelected(cur.booleanValue());
            });
        }
    }

    public FxAction(Runnable onAction, Consumer<Boolean> onSelected) {
        this(onAction, onSelected, true);
    }

    public FxAction(Runnable onAction, boolean enabled) {
        this(onAction, null, enabled);
    }

    public FxAction(Runnable onAction) {
        this.selectedProperty = new FxBoolean();
        this.disabledProperty = new FxBoolean();
        this.onAction = onAction;
    }

    public FxAction() {
        this.selectedProperty = new FxBoolean();
        this.disabledProperty = new FxBoolean();
    }

    public void setOnAction(Runnable r) {
        this.onAction = r;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void invokeAction() {
        if (this.onAction != null) {
            try {
                this.onAction.run();
            } catch (Throwable e) {
                log.error(e);
            }
        }
    }

    public void attach(ButtonBase b) {
        b.setOnAction(this);
        b.disableProperty().bind(disabledProperty());
        if (b instanceof ToggleButton) {
            ((ToggleButton) b).selectedProperty().bindBidirectional(selectedProperty());
        }
    }

    public void attach(MenuItem m) {
        m.setOnAction(this);
        m.disableProperty().bind(disabledProperty());
        if (m instanceof CheckMenuItem) {
            ((CheckMenuItem) m).selectedProperty().bindBidirectional(selectedProperty());
        } else if (m instanceof RadioMenuItem) {
            ((RadioMenuItem) m).selectedProperty().bindBidirectional(selectedProperty());
        }
    }

    public final BooleanProperty selectedProperty() {
        return this.selectedProperty;
    }

    public final boolean isSelected() {
        return this.selectedProperty.get();
    }

    public final void setSelected(boolean on) {
        if (this.selectedProperty.get() != on) {
            this.selectedProperty.set(on);
            fire();
        }
    }

    public final BooleanProperty disabledProperty() {
        return this.disabledProperty;
    }

    public final boolean isDisabled() {
        return this.disabledProperty.get();
    }

    public final void setDisabled(boolean on) {
        this.disabledProperty.set(on);
    }

    public final boolean isEnabled() {
        return !isDisabled();
    }

    public final void setEnabled(boolean on) {
        this.disabledProperty.set(!on);
    }

    public final void enable() {
        setEnabled(true);
    }

    public final void disable() {
        setEnabled(false);
    }

    public void fire() {
        if (isEnabled()) {
            handle((ActionEvent) null);
        }
    }

    public void execute() {
        try {
            invokeAction();
        } catch (Throwable e) {
            log.error(e);
        }
    }

    protected void fireSelected(boolean on) {
        try {
            this.onSelected.accept(Boolean.valueOf(on));
        } catch (Throwable e) {
            log.error(e);
        }
    }

    @Override // javafx.event.EventHandler
    public void handle(ActionEvent ev) {
        ContextMenu p;
        if (isEnabled()) {
            if (ev != null) {
                if ((ev.getSource() instanceof Menu) && ev.getSource() != ev.getTarget()) {
                    return;
                }
                ev.consume();
            }
            execute();
            if (ev != null) {
                Object src = ev.getSource();
                if ((src instanceof Menu) && (p = ((Menu) src).getParentPopup()) != null) {
                    p.hide();
                }
            }
        }
    }
}
