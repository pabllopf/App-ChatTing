
package ejbs.singleton;

import java.util.HashMap;
import javax.ejb.Remote;

@Remote
public interface StafulContainerRemote {
    public void init();
    public void destroy();
    
    public void add(String name, String JNDI);
    
    public HashMap<String, String> getAll();
}
