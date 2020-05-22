/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.stateless.controllers;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tables.TablePublicroom;

/**
 *
 * @author wwwam
 */
@Stateless
public class TablePublicroomFacade extends AbstractFacade<TablePublicroom> {

    @PersistenceContext(unitName = "ChatPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TablePublicroomFacade() {
        super(TablePublicroom.class);
    }
    
}
