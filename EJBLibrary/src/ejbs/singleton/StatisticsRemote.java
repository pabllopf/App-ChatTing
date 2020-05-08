package ejbs.singleton;

import javax.ejb.Remote;

@Remote
public interface StatisticsRemote {
    public void init();
    public void destroy();
    
    public void countNewLoggin();
    public int getCounterOfLoggings();
    
    public void countNewsSingUp();
    public int getCounterOfSingUp();
}
