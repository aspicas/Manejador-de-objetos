/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;  

/**
 *
 * @author david
 */
public class Mob {
    private int id;
    private Date fechaCreacion;
    private String nombre;
    private String accion;

    public Mob() {
    }
    
    public Mob(int id, Date fechaCreacion, String name, String Accion) {
        this.id = id;
        this.fechaCreacion = fechaCreacion;
        this.nombre = name;
        this.accion = Accion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
    
    
    
    
}
