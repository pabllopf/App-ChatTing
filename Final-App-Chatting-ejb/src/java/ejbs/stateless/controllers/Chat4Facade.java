/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.stateless.controllers;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tables.Chat4;

/**
 *
 * @author wwwam
 */
@Stateless
public class Chat4Facade extends AbstractFacade<Chat4> {

    @PersistenceContext(unitName = "ChatPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Chat4Facade() {
        super(Chat4.class);
    }
    
}
