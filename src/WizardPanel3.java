package sage;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class WizardPanel3 extends JPanel implements WizardValidator{
    protected boolean canMoveForward = false;
    protected boolean canMoveBackward = true;

    public WizardPanel3(String name, String description)
    {
            setLayout(new BorderLayout());
            JPanel north = new JPanel();
            north.setSize(new Dimension(580,45));
            north.setLayout(new BorderLayout());
            north.setBackground(Color.white);

            JLabel title = new JLabel(name);
            title.setFont(new Font("Helvetica", Font.BOLD, 11));
            title.setBorder(new EmptyBorder(8,8, 0, 8));

            north.add("North", title);

            if (description != null)
            {
                JLabel explain = new JLabel(description);
                explain.setBorder(new EmptyBorder(2, 10, 10, 8));

                north.add("Center", explain);
            }

            JLabel line = new JLabel();
            line.setBorder(new EdgeBorder(EdgeBorder.SOUTH));
            north.add("South", line);

            add("North", north);
    }

    public boolean canMoveForward()
    {
            return canMoveForward;
    }

    public boolean canMoveBackward()
    {
            return canMoveBackward;
    }

    public DataCollectionModel getDataModel()
    {
            return ((JWizard3)getParent().getParent()).getModel();
    }

    public SequenceManager getSequenceManager()
    {
            return ((JWizard3)getParent().getParent()).getManager();
    }


}
