package ejbs.stateful;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateful;
import javax.ejb.LocalBean;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;

@Stateful
@LocalBean
public class Pagination {
    private int numberPage;
    
    @PostConstruct
    public void init() {
        System.out.println("Pagination::init - @PostConstruct Stateful");
        numberPage = 0;
    }

    @PrePassivate
    public void passivate() {
        System.out.println("Pagination::passivate - @PrePassivate Stateful");
    }

    public int nextPage(){
        return numberPage;
    }
    
    public int previousPage(){
        return numberPage;
    }
    
    @PostActivate
    public void active() {
        System.out.println("Pagination::active - @PostActivate Stateful");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Pagination::destroy - @PreDestroy Stateful");
    }
}
