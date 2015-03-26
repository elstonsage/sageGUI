package sage;

import java.awt.Component;
import java.awt.BorderLayout;
import javax.swing.*;

public class JWizard extends JPanel
{
	DeckPanel deck;
	WizardNavigator nav;
	DataCollectionModel model;
	SequenceManager manager;

	public JWizard() throws Exception
	{
          setLayout(new BorderLayout());
          setManager(new WizardSequenceManager());
          add("Center", deck = new DeckPanel(manager));
          add("South", nav = new WizardNavigator(deck));
          setModel(new PropertyDataModel());
	}

	public void setModel(DataCollectionModel model)
	{
		this.model = model;
		model.addChangeListener(nav);
	}

	public DataCollectionModel getModel()
	{
		return model;
	}

	public void setManager(SequenceManager manager)
	{
		this.manager = manager;
	}

	public SequenceManager getManager()
	{
		return manager;
	}

	public void addPanel(String name, Component panel)
	{
		deck.addPanel(name, panel);
	}

	public void setFirst()
	{
		nav.setPanel(deck.getFirst());
	}
}
