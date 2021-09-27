package ui;

import app.App;
import data.Division;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;
import java.util.List;


public class DivisionView extends TabPane implements Updatable {


    private List<DivisionTab> divisionTabs;


    public DivisionView() {
        super();
        divisionTabs = new ArrayList<>();
        Tab tab = new Tab("General");
        getTabs().add(tab);
        update();
    }

    public void update() {
        if(divisionTabs.size() != App.openedPace.getDivisions().size()) {
            //Get Selected index
            int selIndex = getSelectionModel().getSelectedIndex();
            List<Division> divisions = App.openedPace.getDivisions();

            List<DivisionTab> newDivisionTabs = new ArrayList<>();

            for(Division div : divisions) {
                DivisionTab divisionTab = null;
                for(DivisionTab divList : divisionTabs) {
                    if(divList.division == div) {
                        divisionTab = divList;
                        break;
                    }
                }
                if(divisionTab == null) {
                    divisionTab = new DivisionTab(this, div);
                }
                newDivisionTabs.add(divisionTab);
            }

            getTabs().remove(1,getTabs().size() - 1);
            getTabs().addAll(newDivisionTabs);
            divisionTabs = newDivisionTabs;

            if(selIndex > 0) {
                getSelectionModel().select(selIndex);
            }

        } else {
            for(DivisionTab divisionTab : divisionTabs) {
                divisionTab.update();
            }
        }
    }

    private class DivisionTab extends Tab implements Updatable {

        TeamTable table;
        Division division;
        DivisionView parent;

        public DivisionTab(DivisionView parent, Division division) {
            super(division.getName());
            setClosable(true);
            this.division = division;
            this.parent = parent;

            BorderPane content = new BorderPane();

            table = new TeamTable(() -> this.division.getTeams());
            table.getColumns().remove(0);
            content.setCenter(table);




            setContent(content);
            update();
        }

        public void update() {
            table.update();
            setText(division.getName());
        }
    }
}
