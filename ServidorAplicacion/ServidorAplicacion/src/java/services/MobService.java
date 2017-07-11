/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import model.Mob;
import xml.XMLManager;

/**
 *
 * @author david
 */
public class MobService {

    public MobService() {
    }
    
    public Mob getMob(int id){
        return null;
    }
    
    public void addMob(Mob mob){
        XMLManager.saveMobXml(mob);        
    }
    
    public void removeMob(int id){
        
    }
}
