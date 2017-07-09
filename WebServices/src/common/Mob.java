package common;

import java.util.Date;

/**
 * Created by gbsojo on 7/9/17.
 */
public class Mob {
    private int id;
    private Date fechaCreacion;
    private String nombre;
    private String accion;

    public Mob() {
    }

    public Mob(int id, Date fechaCreacion, String nombre, String accion) {
        this.id = id;
        this.fechaCreacion = fechaCreacion;
        this.nombre = nombre;
        this.accion = accion;
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
