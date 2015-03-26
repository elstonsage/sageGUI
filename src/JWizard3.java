package sage;

import java.awt.*;
import javax.swing.*;


public class JWizard3 extends JPanel {
    DeckPanel deck;
    WizardNavigator3 nav;
    DataCollectionModel model;
    SequenceManager manager;

    public JWizard3() throws Exception
    {
      setLayout(new BorderLayout());
      setManager(new WizardSequenceManager());
      add("Center", deck = new DeckPanel(manager));
      add("South", nav = new WizardNavigator3(deck));
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
