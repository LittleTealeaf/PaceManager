package org.tealeaf.pacemanager.app.wrappers;

import javafx.scene.Node;
import javafx.scene.control.TableCell;
import javafx.scene.text.Text;
import org.tealeaf.pacemanager.app.Identity;

public abstract class IdentifiableTableCell<E, F, G extends Node> extends TableCell<E, F> {

    private Identity identity;

    protected G graphicNode;

    abstract protected G createGraphic();

    public IdentifiableTableCell(Identity identity) {
        this.identity = identity;
        this.graphicNode = createGraphic();
        setGraphic(graphicNode);
    }

    @Override
    public void updateIndex(int i) {
        super.updateIndex(i);
        identity.set(graphicNode,i);
    }

    abstract protected void updateGraphic(G graphic, F item);

    @Override
    protected void updateItem(F item, boolean empty) {
        updateGraphic(graphicNode,item);
    }


    public static class IdentifiableStringCell<E> extends IdentifiableTableCell<E,String, Text> {

        public IdentifiableStringCell(Identity identity) {
            super(identity);
        }

        @Override
        protected Text createGraphic() {
            return new Text();
        }

        @Override
        protected void updateGraphic(Text graphic, String item) {
            graphic.setText(item);
        }
    }
}
