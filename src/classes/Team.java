package classes;

public class Team {

    private String teamID = "";
    private String[] teamMembers = new String[]{};
    private String division = "default";
    private String notes = "";
    private int startTime = 0;
    private int endTime = 0;
    private boolean exclude = false;

    public Team() {}

    public Team(String teamID) {
        this.teamID = teamID;
    }

    public Team(String teamID, String[] teamMembers) {
        this(teamID);
        this.teamMembers = teamMembers;
    }

    public String getTeamID() {
        return teamID;
    }

}
