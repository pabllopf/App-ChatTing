package ejbs;

import javax.ejb.Stateless;

@Stateless
public class Mensagge2 implements Mensagge2Local {

    @Override
    public String getMessage(String m) {
        return "Mensaje2::getMessage:" + m;
    }
    
}
