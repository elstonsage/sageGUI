package sage;

import java.util.*;
import java.text.*;

public class RunJobTask {
    private String analysis_name;
    private String runtime;
    private String starttime;
    DecimalFormat d = new DecimalFormat("00");

    RunJobTask(String input_aname) {
        this.analysis_name = input_aname;
    }

    /**
     * Called from ProgressBarDemo to start the task.
     */
    void go(final Calendar start) {
        String ampm = null;
        if (start.get(Calendar.AM_PM) == 0) {
            ampm = "AM";
        }
        else
            ampm = "PM";
        this.starttime = d.format(start.get(Calendar.HOUR)) + ":" +
                d.format(start.get(Calendar.MINUTE)) + " " + ampm + " ";

        final SwingWorker worker = new SwingWorker() {
            public Object construct() {
                return new ActualTask(start);
            }
        };
        worker.start();
    }

    /**
     * Called from ProgressBarDemo to find out how much work needs
     * to be done.
     */
    String getName() {
        return this.analysis_name;
    }

    String getMessage() {
        return runtime;
    }

    String getStartTime()
    {
        return starttime;
    }

    /**
     * The actual long running task.  This runs in a SwingWorker thread.
     */
    class ActualTask {
        ActualTask(Calendar start) {
            //Fake a long task,
            //making a random amount of progress every second.
            while (start.getTimeInMillis()>0) {
                try {
                    Thread.sleep(1000); //sleep for a second
                    Calendar running = Calendar.getInstance();

                    long t1 = running.getTimeInMillis();
                    long t2 = start.getTimeInMillis();

                    long elapsed = t1 - t2;
                    int tmp = (int) (elapsed / 1000L);
                    int sec = tmp % 60;
                    tmp /= 60;
                    int mn = tmp % 60;
                    tmp /= 60;
                    int hr = tmp % 24;

                    runtime = hr + ":" + d.format(mn) + ":" + d.format(sec) + " ";
                }
                catch (InterruptedException e) {}
            }

        }
    }
}
