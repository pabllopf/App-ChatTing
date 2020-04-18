package ejbs;
import javax.ejb.Local;

@Local
public interface Mensagge2Local {
    public String getMessage(String m);
}
