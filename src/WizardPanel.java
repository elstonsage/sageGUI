package sage;

import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public abstract class WizardPanel extends JPanel
	implements WizardValidator
{
	protected boolean canMoveForward = false;
	protected boolean canMoveBackward = true;

        JFileChooser jFileChooser1 = new JFileChooser();

	public WizardPanel(String name, String description)
	{
		setLayout(new BorderLayout());
		JPanel north = new JPanel();
		north.setLayout(new BorderLayout());
                north.setBackground(Color.white);

                try {
                    jFileChooser1.setCurrentDirectory(new File(Frame1.mainFrame1.
                            activeinframe.projectPath));
                } catch (Exception ex) {

                }
		JLabel title = new JLabel(name);
		title.setFont(new Font("Helvetica", Font.BOLD, 11));
                title.setBorder(new EmptyBorder(8,8, 0, 8));

		north.add("North", title);

                if (description != null) {
                  JTextArea explain = new JTextArea();
                  explain.setLineWrap(true);
                  explain.setEditable(false);
                  explain.setFocusable(false);
                  explain.setFont(new Font("Dialog", Font.PLAIN, 11));
                  explain.setBorder(new EmptyBorder(2, 10, 10, 8));
                  explain.setText(description);
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
		return ((JWizard)getParent().getParent()).getModel();
	}

	public SequenceManager getSequenceManager()
	{
		return ((JWizard)getParent().getParent()).getManager();
	}

}
