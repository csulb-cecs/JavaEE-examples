package edu.csulb.cecs423.jsfbeans;

import java.io.Serializable;
import java.util.Date;

/**
 * BaseBean is a simple JSF managed bean class that has a counter and
 * a timestamp for when the object is instantiated.
 *
 * @author Alvaro Monge <alvaro.monge@csulb.edu>
 */
public class BaseBean implements Serializable {
    private int counter;
    private final Date timeStamp;

    /**
     * Create an instance with an initial counter value of 0 and the current time.
     */
    public BaseBean() {
        counter = 0;
        timeStamp = new Date();
    }

    /**
     * Access the type of JSF Managed Bean that this is
     *
     * @return the message identifying the bean.
     */
    public String getMessage() {
        return "Named bean of type: " +
                this.getClass().getSimpleName();
    }

    /**
     * increase the counter by one, to demonstrate updates to the counter.
     */
    public void incrementCounter() {
        counter += 1;
    }

    /**
     * @return the counter
     */
    public int getCounter() {
        return counter;
    }

    /**
     * @return the timeStamp
     */
    public Date getTimeStamp() {
        return timeStamp;
    }



}
