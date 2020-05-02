package ejbs;

import database.UserHandler;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateful;
import models.User;

@Stateful
public class CurrentUser implements CurrentUserRemote {

    @PostConstruct
    @Override
    public void init(){
        System.out.println("CurrentUser::init - @PostConstruct stateful");
    }
    
    @Override
    public boolean login(User account) {
        System.out.println("CurrentUser::login - @PostConstruct stateful");
        return new UserHandler().login(account);
    }
    
    @PreDestroy
    @Override
    public void destroy(){
        System.out.println("CurrentUser::destroy - @PreDestroy stateful");
    }
}
