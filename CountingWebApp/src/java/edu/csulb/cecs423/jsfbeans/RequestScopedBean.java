package edu.csulb.cecs423.jsfbeans;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;


/**
 * RequestScopedBean is a simple JSF managed bean that is in the Request Scope
 * and as such there will one bean instance for every single request. Such a
 * bean can reference any bean in the other scopes.
 *
 * @author Alvaro Monge <alvaro.monge@csulb.edu>
 */
@Named
@RequestScoped
public class RequestScopedBean extends BaseBean {

    @Inject
    private SessionScopedBean sessionBean;

    @Inject
    private ApplicationScopedBean applicationBean;

    @Override
    public String getMessage() {
        return "There are lots of me! " + super.getMessage();
    }
    
    /**
     * A JSF action method to update managed bean counters by one.
     * @return "index" to navigate to the top page
     */
    public String incrementCounters() {
        sessionBean.incrementCounter();
        applicationBean.incrementCounter();
        this.incrementCounter();

        // outcome of this action... index will return navigation to index.html
        return "index";
    }
    
    /**
     * Invalidate a session and redirect
     * @return index to navigate to the start page
     */
    public String resetSession() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index.xhtml?faces-redirect=true";
    }

}
