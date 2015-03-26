package sage;

import javax.swing.*;
import java.awt.*;

public class ComboBoxRenderer extends JLabel
                       implements ListCellRenderer {
    private Font uhOhFont;

    public ComboBoxRenderer() {
        setOpaque(true);
        setVerticalAlignment(CENTER);
    }

    /*
     * This method finds the image and text corresponding
     * to the selected value and returns the label, set up
     * to display the text and image.
     */
    public Component getListCellRendererComponent(
                                       JList list,
                                       Object value,
                                       int index,
                                       boolean isSelected,
                                       boolean cellHasFocus) {
        //Get the selected index. (The index param isn't
        //always valid, so just use the value.)
       // int selectedIndex = ((Integer)value).intValue();

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        if(index==2)
          setForeground(Color.red);


        setText(value.toString());

        return this;
    }

    protected void setUhOhText(String uhOhText, Font normalFont) {
        if (uhOhFont == null) {
            uhOhFont = normalFont.deriveFont(Font.ITALIC);
        }
        setFont(uhOhFont);
        setText(uhOhText);
    }

}
