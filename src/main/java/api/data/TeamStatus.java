package api.data;

/**
 * The current status of a given Team
 */
public enum TeamStatus {
    /**
    Indicates that the team has not yet started the pace
     */
    NOT_STARTED,
    /**
    Indicates that the team is currently in progress of completing the pace.
     */
    IN_PROGRESS,
    /**
    Indicates that the team has completed the pace.
     */
    FINISHED;
}
