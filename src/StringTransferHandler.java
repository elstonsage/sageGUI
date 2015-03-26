package sage;

import java.awt.datatransfer.*;
import javax.swing.TransferHandler;
import javax.swing.JComponent;
import java.io.IOException;

public abstract class StringTransferHandler extends TransferHandler {

    protected abstract NodeInfo exportString(JComponent c);
    protected abstract void importString(JComponent c, NodeInfo str);

 // create a transferable to use as the source for a data transfer
    protected Transferable createTransferable(JComponent c) {
        return exportString(c);
    }

    public int getSourceActions(JComponent c) {
        return COPY_OR_MOVE;
    }

    public boolean importData(JComponent c, Transferable t) {
      /*
       public DataFlavor[] getTransferDataFlavors()
       Returns an array of flavors in which this Transferable can provide the data.
       DataFlavor.stringFlavor is properly supported. Support for DataFlavor.plainTextFlavor is deprecated.
            Returns:an array of length two, whose elements are DataFlavor. stringFlavor and DataFlavor.plainTextFlavor
       */

        if (canImport(c, t.getTransferDataFlavors())) {
            try {
              /* Returns the Transferable's data in the requested DataFlavor if possible */
                //String str = (String)t.getTransferData(DataFlavor.stringFlavor);
                //NodeInfo str = (NodeInfo)t.getTransferData(DataFlavor.stringFlavor);
                NodeInfo str = (NodeInfo)t.getTransferData(NodeInfo.FLAVOUR);
                importString(c, str);
                return true;
            } catch (UnsupportedFlavorException ufe) {
              ufe.printStackTrace();
            } catch (IOException ioe) {
              ioe.printStackTrace();
            }
        }
        return false;
    }

    protected void exportDone(JComponent c, Transferable data, int action) {
//        cleanup(c, action == MOVE);
    }

/* true if the data can be inserted into the component, false otherwise */
    public boolean canImport(JComponent c, DataFlavor[] flavors) {
        for (int i = 0; i < flavors.length; i++) {
             if (flavors[i].equals(NodeInfo.FLAVOUR)) {
                return true;
            }
        }
        return false;
    }
}
