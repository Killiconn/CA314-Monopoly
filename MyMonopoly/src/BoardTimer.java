import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BoardTimer
{
    int secondsPassed = 0;
    int endCond = 0;
    private ScheduledExecutorService executorTimer = Executors.newSingleThreadScheduledExecutor();

    Runnable run = new Runnable () { 
        public void run()
        {
            if(secondsPassed < endCond)
            {
                secondsPassed++;
                //System.out.println("Seconds passed: " + secondsPassed + " Run till -> " + endCond);
            }
            else
            {
                executorTimer.shutdown();
            }
        }
    };

    public BoardTimer(int timeMinutes)
    {
        this.endCond = timeMinutes * 60; // Time in seconds
    }

    public void start()
    {
        executorTimer.scheduleAtFixedRate(run, 0, 1, TimeUnit.SECONDS);  // Timer, start 1000ms after run, 1000ms between each step
    }

    public boolean isAlive()
    {
        return this.executorTimer.isShutdown();
    }

    public void forceShutdown()
    {
        executorTimer.shutdown();
    }

    public static void main(String [] args) throws InterruptedException
    {
        BoardTimer tmp = new BoardTimer(1);
        tmp.start();
        Thread.sleep(8000);
        System.out.println(tmp.isAlive());
    }
}
