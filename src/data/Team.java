package data;

import java.util.UUID;

public class Team {

	private UUID uuid;
	private String teamNumber;
	private String[] riders;


	public Team() {
		this.uuid = UUID.randomUUID();
	}
}
