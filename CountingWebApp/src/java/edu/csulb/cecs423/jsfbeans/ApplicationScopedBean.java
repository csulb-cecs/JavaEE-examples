package edu.csulb.cecs423.jsfbeans;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 * ApplicationScopedBean is a JSF Managed Bean that is in the Application Scope
 * and as such there will always only be one instance of.  So, it's an object
 * that is global to the entire app and can thus be accessed from managed beans
 * in the other scopes.
 *
 * @author Alvaro Monge <alvaro.monge@csulb.edu>
 */
@Named
@ApplicationScoped
public class ApplicationScopedBean extends BaseBean {

    /**
     * The unique message for this JSF managed bean.
     * @return the message identifying it as a unique application scoped bean.
     */
    @Override
    public String getMessage() {
        return "There's only one of me!  " + super.getMessage();
    }

}
