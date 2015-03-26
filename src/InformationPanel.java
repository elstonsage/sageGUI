package sage;

import java.awt.Font;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.*;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.text.Document;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JButton;
import javax.swing.border.*;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import java.io.File;

public class InformationPanel extends WizardPanel
	implements DocumentListener, ActionListener
{
	protected JTextField pName;
        protected JTextArea DirPath;
        JLabel Dir;
        JButton DirButton;

	public InformationPanel()
	{
             super("S.A.G.E. Project", "Create a new S.A.G.E. project.");

             JPanel top = new JPanel();
             JLabel pLabel = new JLabel("Project name");
             pName = new JTextField();

             top.setLayout(new BorderLayout(5,5));
             top.add(pLabel, BorderLayout.WEST);
             top.add(pName, BorderLayout.CENTER);
             top.setBorder(new EmptyBorder(5,8,8,8));

             JPanel inmiddle = new JPanel();
             Dir = new JLabel("Directory");

             String init_dit = new String();
             String os_type = System.getProperty("os.name");
             if (os_type.indexOf("Windows") >= 0 || os_type.indexOf("Mac")>=0) {
               init_dit = System.getProperty("user.dir")+System.getProperty("file.separator");
             }
             else {
               init_dit = System.getProperty("user.home")+System.getProperty("file.separator");
             }

             DirPath = new JTextArea(init_dit);
             DirPath.setFont(new java.awt.Font("MS Sans Serif", 0, 11));
             DirPath.setBorder(BorderFactory.createLoweredBevelBorder());

             DirButton = new JButton("Browse...");
             DirButton.setSize(new Dimension(60,-1));
             DirButton.setMargin(new Insets(2,2,2,2));
             DirButton.addActionListener(this);

             inmiddle.setLayout(new BorderLayout(5, 0));
             inmiddle.setBorder(new EmptyBorder(5,5,5,5));
             inmiddle.setPreferredSize(new Dimension(360,30));
             inmiddle.add(Dir, BorderLayout.WEST);
             inmiddle.add(DirPath, BorderLayout.CENTER);
             inmiddle.add(DirButton, BorderLayout.EAST);

             JPanel middle = new JPanel();
             middle.setLayout(new BorderLayout());
             middle.setBorder(new TitledBorder("Project location"));
             middle.add(inmiddle, BorderLayout.CENTER);

             JPanel bottom = new JPanel();
             bottom.setPreferredSize(new Dimension(360,160));

             JPanel panel = new JPanel();
             panel.setLayout(new BorderLayout());
             panel.add("North",top);
             panel.add("Center",middle);
             panel.add("South", bottom);

             JPanel center = new JPanel();
             center.setLayout(new UnitLayout());
             center.add(panel);
	     add("Center", center);

              pName.getDocument().addDocumentListener(this);
             DirPath.getDocument().addDocumentListener(this);
	}

        public void actionPerformed(ActionEvent e) {
          Object button = e.getSource();
          if (button == DirButton)
            DirButton_actionPerformed(e);
        }

        public void DirButton_actionPerformed(ActionEvent e)
        {
          String init_dit = new String();
          String os_type = System.getProperty("os.name");
          if (os_type.indexOf("Windows") >= 0) {
            init_dit = System.getProperty("user.dir")+System.getProperty("file.separator");
          }
          else {
            init_dit = System.getProperty("user.home")+System.getProperty("file.separator");
          }

          JFileChooser chooser = new JFileChooser();
          chooser.setCurrentDirectory(new File(init_dit));
          chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
          if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
          {
            DirPath.setText(chooser.getSelectedFile().toString()+System.getProperty("file.separator"));
          }
        }

	public void changedUpdate(DocumentEvent event)
	{
		Document document = event.getDocument();
		if (document == pName.getDocument())
		{
                    if(pName.getText().length()>0)
                    {
                        getDataModel().setValue("info.pName", pName.getText());
                        getDataModel().setValue("info.DirPath", DirPath.getText());
                    }
                    else
                    {
                        if(getDataModel().hasValue("info.pName"))
                        {
                            getDataModel().removeValue("info.pName");
                        }
                    }
		}
		else if (document == DirPath.getDocument())
		{
			getDataModel().setValue("info.DirPath", DirPath.getText());
		}
		if (getDataModel().hasValue("info.pName") && getDataModel().hasValue("info.DirPath"))
		{
			canMoveForward = true;
                        ((PropertyDataModel)getDataModel()).fireChangeEvent();
                        JWizardProject.wizard.nav.validate();
		}
                else
                {
                    canMoveForward = false;
                    ((PropertyDataModel)getDataModel()).fireChangeEvent();
                    JWizardProject.wizard.nav.validate();
                }
	}

	public void insertUpdate(DocumentEvent event)
	{
		changedUpdate(event);
	}

	public void removeUpdate(DocumentEvent event)
	{
		changedUpdate(event);
	}
}
