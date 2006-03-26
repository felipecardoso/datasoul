/*
 * TimerManager.java
 *
 * Created on March 22, 2006, 11:17 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package datasoul.datashow;

import datasoul.render.ContentManager;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author root
 */
public class TimerManager extends Thread {
    
    private static TimerManager instance;
    
    public static final int TIMER_FORMAT_24H_WITH_SEC = 0;
    public static final int TIMER_FORMAT_24H_WITHOUT_SEC = 1;
    public static final int TIMER_FORMAT_AMPM_WITH_SEC = 2;
    public static final int TIMER_FORMAT_AMPM_WITHOUT_SEC = 3;
    
    public static final int TIMER_DIRECTION_OFF = 0;
    public static final int TIMER_DIRECTION_FORWARD = 1;
    public static final int TIMER_DIRECTION_BACKWARD = 2;
    
    private SimpleDateFormat sdformat;
    private SimpleDateFormat sdformatTimer;
    private volatile boolean stopThread;
    private int timerDirection;
    private long timerForwardCounter;
    private long timerBackwardCounter;
    private long timerBackwardTotal;
    
    /** Creates a new instance of TimerManager */
    private TimerManager() {

        int format = TIMER_FORMAT_24H_WITH_SEC;
        
        sdformatTimer = new SimpleDateFormat("H:mm:ss");
        
        // Select the format
        switch(format){
            
            case TIMER_FORMAT_24H_WITH_SEC: 
                sdformat = new SimpleDateFormat("k:mm:ss");
                break;

            case TIMER_FORMAT_24H_WITHOUT_SEC: 
                sdformat = new SimpleDateFormat("k:mm");
                break;
                
            case TIMER_FORMAT_AMPM_WITH_SEC: 
                sdformat = new SimpleDateFormat("h:mm:ss a");
                break;
                
            case TIMER_FORMAT_AMPM_WITHOUT_SEC: 
                sdformat = new SimpleDateFormat("h:mm");
                break;
                
            default:    
                sdformat = new SimpleDateFormat("k:mm:ss");
                
        }
        
        stopThread = false;
        
        start();
        
    }
    
    public void setTimerOff(){
        this.timerDirection = TIMER_DIRECTION_OFF;
    }
    
    public void setTimerForward(long initial){
        this.timerForwardCounter = initial;
        this.timerDirection = TIMER_DIRECTION_FORWARD;
    }
    
    public void setTimerBackward(long initial, long total){
        this.timerBackwardCounter = initial;
        this.timerBackwardTotal = total;
        this.timerDirection = TIMER_DIRECTION_BACKWARD;
    }
    
    static public TimerManager getInstance(){
        if (instance == null){
            instance = new TimerManager();
        }
        return instance;
    }
    
    private String formatTimer(long t){
        long ts = t / 1000;
        
        long sec = ts % 60;
        long min = ts / 60;
        
        StringBuffer ret = new StringBuffer();
        
        if (min < 10){
            ret.append("0");
        }
        ret.append(min);
        ret.append(":");
        if (sec < 10){
            ret.append("0");
        }
        ret.append(sec);
        
        return ret.toString();
        
        
    }
    
    public void run(){
        
        long t1, t2;
        ContentManager cm = ContentManager.getInstance();
        
        while (!stopThread){
            
            t1 = System.currentTimeMillis();
            
            // update the clock
            cm.setClockLive( sdformat.format(new Date()) );
            
            
            if (timerDirection == TIMER_DIRECTION_FORWARD){
                timerForwardCounter += 1000;
                cm.setTimerLive( formatTimer( timerForwardCounter ));
            }else if (timerDirection == TIMER_DIRECTION_BACKWARD){
                timerBackwardCounter += 1000;
                if ( timerBackwardTotal - timerBackwardCounter < 0 ){
                    setTimerForward(0);
                }else{
                    cm.setTimerLive( formatTimer(timerBackwardTotal - timerBackwardCounter ));
                }
            }else if (timerDirection == TIMER_DIRECTION_OFF){
                cm.setTimerLive("");
            }
            
            // ok, changes done
            cm.updateLive();
        
            // go sleep!
            t2 = System.currentTimeMillis();
            if ( (1000 - (t2 - t1)) > 1 ){
                try {
                    Thread.sleep( 1000 - (t2 - t1) );
                } catch (InterruptedException ex) {
                    //ignore
                }
            }
            
        }
        
    }
    
    
}