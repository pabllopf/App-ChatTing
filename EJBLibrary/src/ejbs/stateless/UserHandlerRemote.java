package ejbs.stateless;

import javax.ejb.Remote;
import models.User;

@Remote
public interface UserHandlerRemote {
    public void init();
    public boolean login(User account);
    public boolean signUp(User account);
    public boolean exists(User account);
    public void destroy();
}
