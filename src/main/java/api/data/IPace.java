package api.data;

public interface IPace {



    default void registerComponent(PaceComponent paceComponent) {
        paceComponent.setPace(this);
    }

    default void unregisterComponent(PaceComponent paceComponent) {
        paceComponent.clearPace();
    }
}
