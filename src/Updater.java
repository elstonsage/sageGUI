package sage;

import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

public class Updater  {
      private final JProgressBar jpb;
      private boolean queued;
      private int val;
      private Runnable runnable = new Runnable() {
          public void run() {
              int _val;
              synchronized(Updater.this) {
                  queued = false;
                  _val = val;
              }
              jpb.setValue(_val);
          }
      };

      public Updater(JProgressBar jpb) {
          this.jpb = jpb;
      }

      public void update(int _val) {
          boolean go = false;
          synchronized(this) {
              val = _val;
              if (!queued) {
                  queued = true;
                  go = true;
              }
          }
          if (go)
              SwingUtilities.invokeLater(runnable);
      }
  }
