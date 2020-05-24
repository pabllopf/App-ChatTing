package ejbs.stateless.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import models.Chat;
import models.User;
import tables.TableUsers;

@Stateless
public class TableUsersFacade extends AbstractFacade<TableUsers> {

    @PersistenceContext(unitName = "ChatPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public TableUsersFacade() {
        super(TableUsers.class);
    }
    
    @PostConstruct
    public void init(){
        System.out.println("TableUsersFacade::init - @PostConstruct Stateless");
    }
    
    public boolean login(User account){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<TableUsers> cq = cb.createQuery(TableUsers.class);
        Root<TableUsers> user = cq.from(TableUsers.class);
        cq.select(user)
        .distinct(true)
        .orderBy(cb.asc(user.get("name")))
        .where(cb.like(user.get("name"), account.getUser()));
                
        TypedQuery<TableUsers> q = em.createQuery(cq);
        List<TableUsers> finaluser = q.getResultList();
        
        return finaluser.stream().anyMatch((i) -> (i.getName().equals(account.getUser()) && i.getPassword().equals(account.getPassword())));
    }
    
    public boolean signUp(User account){
        this.create(new TableUsers(this.count() + 1,account.getUser(),account.getPassword()));
        return this.findAll().stream().anyMatch((user) -> (user.getName().equals(account.getUser()) && user.getPassword().equals(account.getPassword())));
    }
    
    public boolean exists(User account){
        List<TableUsers> results = em.createQuery("SELECT c FROM TableUsers C WHERE c.name LIKE :custname")
                                        .setParameter("custname", account.getUser()).getResultList();
        System.out.println("TableUsersFacade::exists:: "+ results.size() +" - @PostConstruct Stateless");
        return !(results.isEmpty());
    }
    
    public void changePassword(User account){
        TableUsers userToEdit = new TableUsers(this.findAll().stream().filter(i-> i.getName().equals(account.getUser())).findFirst().get().getId(),account.getUser(),account.getPassword());
        this.edit(userToEdit);
    }
    
    public void removeThisUser(User account){
        TableUsers userToDelete = new TableUsers(this.findAll().stream().filter(i-> i.getName().equals(account.getUser())).findFirst().get().getId(),account.getUser(),account.getPassword());
        this.remove(userToDelete);
    }
    
    public void saveChatHistory(User account, String chat){
        TableUsers user = new TableUsers(this.findAll().stream().filter(i-> i.getName().equals(account.getUser())).findFirst().get().getId(),account.getUser(),account.getPassword());        
        
        List<String> list = new Gson().fromJson(user.getChat(), new TypeToken<List<String>>(){}.getType());
        list = list == null ? new ArrayList<>() : list;
        list.add(chat);
        
        String jsonToSave = new Gson().toJson(list);
        user.setChat(jsonToSave);
        
        this.edit(user);
    }
    
    public String loadChat(User account){
        TableUsers user = new TableUsers(this.findAll().stream().filter(i-> i.getName().equals(account.getUser())).findFirst().get().getId(),account.getUser(),account.getPassword());        
        
        List<String> list = new Gson().fromJson(user.getChat(), new TypeToken<List<String>>(){}.getType());
        list = list == null ? new ArrayList<>() : list;
        
        if(list.isEmpty()){
            return null;
        }else{
            return list.get(0);
        }
    }

    @PreDestroy
    public void destroy() {
        System.out.println("TableUsersFacade::destroy - @PreDestroy Stateless");
    }
}
