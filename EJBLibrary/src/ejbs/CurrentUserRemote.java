package ejbs;

import javax.ejb.Remote;
import models.User;

@Remote
public interface CurrentUserRemote {
    public void init();
    public void destroy();
    public boolean login(User account);
}
