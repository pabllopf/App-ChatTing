/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.stateless.controllers;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import models.LogMessage;
import tables.TableLog;

/**
 *
 * @author wwwam
 */
@Stateless
public class TableLogFacade extends AbstractFacade<TableLog> {

    @PersistenceContext(unitName = "ChatPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public void saveLog(LogMessage log){
        this.create(new TableLog(this.count() + 1, "Support messages Saved in database: " + log.getContent()));
    }
    
    public void cleanLog(){
        for(TableLog log: this.findAll()){
            this.remove(log);
        }
    }

    public TableLogFacade() {
        super(TableLog.class);
    }
    
}
