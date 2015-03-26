package sage;

import java.awt.Component;
import java.awt.BorderLayout;
import javax.swing.*;

public class JWizard2 extends JPanel
{
        DeckPanel deck;
        WizardNavigator2 nav;
        DataCollectionModel model;
        SequenceManager manager;

        public JWizard2() throws Exception
        {
          setLayout(new BorderLayout());
          setManager(new WizardSequenceManager());
          add("Center", deck = new DeckPanel(manager));
          add("South", nav = new WizardNavigator2(deck));
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
