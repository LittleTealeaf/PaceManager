package api.data;

public interface IPace {




    default void addTeam(ITeam team) {
        registerComponent(team);
    }

    default void removeTeam(ITeam team) {
        unregisterComponent(team);
    }

    default void registerComponent(PaceComponent paceComponent) {
        paceComponent.setPace(this);
    }

    default void unregisterComponent(PaceComponent paceComponent) {
        paceComponent.clearPace();
    }
}
