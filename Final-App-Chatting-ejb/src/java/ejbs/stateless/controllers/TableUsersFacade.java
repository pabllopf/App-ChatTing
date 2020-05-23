package ejbs.stateless.controllers;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
        return this.findAll().stream().anyMatch((user) -> (user.getName().equals(account.getUser()) && user.getPassword().equals(account.getPassword())));
    }
    
    public boolean signUp(User account){
        this.create(new TableUsers(this.count() + 1,account.getUser(),account.getPassword()));
        return this.findAll().stream().anyMatch((user) -> (user.getName().equals(account.getUser()) && user.getPassword().equals(account.getPassword())));
    }
    
    public boolean exists(User account){
        return this.findAll().stream().anyMatch((user) -> (user.getName().equals(account.getUser()) && user.getPassword().equals(account.getPassword())));
    }
    
    public void changePassword(User account){
        TableUsers userToEdit = new TableUsers(this.findAll().stream().filter(i-> i.getName().equals(account.getUser())).findFirst().get().getId(),account.getUser(),account.getPassword());
        this.edit(userToEdit);
    }
    
    public void removeThisUser(User account){
        TableUsers userToDelete = new TableUsers(this.findAll().stream().filter(i-> i.getName().equals(account.getUser())).findFirst().get().getId(),account.getUser(),account.getPassword());
        this.remove(userToDelete);
    }

    @PreDestroy
    public void destroy() {
        System.out.println("TableUsersFacade::destroy - @PreDestroy Stateless");
    }
}
