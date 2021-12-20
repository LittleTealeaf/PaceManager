package app;

import data.*;

import java.io.File;

public class App {

    public static void main(String[] args) {
        Pace pace = new Pace();
        Division divisionA = new Division();
        divisionA.setName("Hunt");
        divisionA.setGoalTime(new Time(1000));
        Team teamA = new Team();
        teamA.getRiders().add(new Rider("Christina", "Bellanich"));
        teamA.getRiders().add(new Rider("Ariel", "Khemraj"));
        teamA.setName("Test ABC");
        teamA.setStartTime(new Time(100));
        teamA.setEndTime(new Time(1920));
        teamA.setNotes("THESE\nARE\nNOTES");
        teamA.setExcluded(false);
        teamA.setDivision(divisionA);
        pace.getDivisions().add(divisionA);
        pace.getTeams().add(teamA);
        printPace(pace);
        try {
            pace.serialize(new File("test.ser"));
            System.out.println("Deserializing");
            Pace pace2 = new Pace(new File("test.ser"));
            printPace(pace2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printPace(Pace pace) {
        System.out.println(pace);
        System.out.println("Divisions:");
        for (Division division : pace.getDivisions()) {
            System.out.println("\t" + division.toString());
        }
        System.out.println("Teams:");
        pace.getTeams().forEach(t -> {
            System.out.println("\t" + t.toString());
            System.out.println("\t\t Of Division: " + t.getDivision().toString());
        });
    }
}
