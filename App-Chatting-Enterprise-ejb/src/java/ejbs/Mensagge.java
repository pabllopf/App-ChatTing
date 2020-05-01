package ejbs;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

@Stateless
@LocalBean
public class Mensagge {
   
    @EJB
    Mensagge2Local mensaje2;
    
    public String getMessage(String m){
        String me = mensaje2.getMessage(m);
        return "Mensaje::getMessage: \n" + me;
    }
    
    @PostConstruct
    public void init(){
        System.out.println("Mensaje::Initi()");
    }
    
    @PreDestroy
    public void Destroy(){
        System.out.println("Mensaje::Destroy()");
    }
}
