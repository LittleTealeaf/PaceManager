package ui;

import app.App;
import data.Division;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;
import java.util.List;

/*
TODO change this to another tab view
each tab is another division with its own updating. One tab is the "general" list of data (like what we have here).
so like
Overview | Default | Pleasure | Hunt | Western | Junior
 */

public class DivisionView extends TabPane implements Updatable {


    private List<DivisionList> divisionLists;
    private boolean tabClose;


    public DivisionView() {
        super();
        divisionLists = new ArrayList<>();
        Tab tab = new Tab("TEESTSETST");
        getTabs().add(tab);
        update();
        tabClose = false;
    }

    public void update() {
        //TODO change this to an arraylist or linkedlist (just list) and make it dynamically add / remove as needed
        if(divisionLists.size() != App.openedPace.getDivisions().size()) {
            //Get Selected index
            int selIndex = getSelectionModel().getSelectedIndex();
            List<Division> divisions = App.openedPace.getDivisions();

            List<DivisionList> newDivisionLists = new ArrayList<>();

            for(Division div : divisions) {
                DivisionList divisionList = null;
                for(DivisionList divList : divisionLists) {
                    if(divList.division == div) {
                        divisionList = divList;
                        break;
                    }
                }
                if(divisionList == null) {
                    divisionList = new DivisionList(this, div);
                }
                newDivisionLists.add(divisionList);
            }

            getTabs().remove(1,getTabs().size() - 1);
            getTabs().addAll(newDivisionLists);
            divisionLists = newDivisionLists;

            if(selIndex > 0) {
                getSelectionModel().select(selIndex);
            }

        } else {
            tabClose = false;
            for(DivisionList divisionList : divisionLists) {
                divisionList.update();
            }
        }
    }

    private class DivisionList extends Tab implements Updatable {

        TeamTable table;
        Division division;
        DivisionView parent;

        public DivisionList(DivisionView parent, Division division) {
            super(division.getName());
            this.division = division;
            this.parent = parent;
            BorderPane content = new BorderPane();
            table = new TeamTable(() -> this.division.getTeams());
            table.getColumns().remove(0);
            content.setCenter(table);
            setContent(content);

            setOnCloseRequest(e -> {
                if(App.settings.warnOnDelete() && App.warnDelete(this.division.getName())) {
                    App.openedPace.removeDivision(this.division);
                    this.parent.tabClose = true;
                    App.update();
                }
            });

            update();
        }

        public void update() {
            table.update();
        }
    }

}
