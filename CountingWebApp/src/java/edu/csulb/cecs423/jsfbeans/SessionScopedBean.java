package edu.csulb.cecs423.jsfbeans;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * SessionScopedBean is a simple JSF managed bean that is in the Session Scope
 * and as such there will one bean instance per session. A session occurs between
 * a client (browser thread on client machine) and the web app. Such a
 * bean can reference any bean in the session scope and in the application scope.
 *
 * @author Alvaro Monge <alvaro.monge@csulb.edu>
 */
@Named
@SessionScoped
public class SessionScopedBean extends BaseBean {

    /** Creates a new instance of counting bean that is SessionScope */
    public SessionScopedBean() {
        super();
    }

    /**
     * The unique message for this JSF managed bean.
     * @return the message identifying it as a unique session scoped bean.
     */
    @Override
    public String getMessage() {
        return "There's one of me per session!  " + super.getMessage();
    }
    
    /**
     * Can annotate methods with @PostConstruct to initialize the bean
     * and annotate it with @PreDestroy to clean up just prior to the bean
     * being removed by the container. This is part of the life-cycle management
     * of a bean since a developer does not explicitly create one.
     * 
     */

}
