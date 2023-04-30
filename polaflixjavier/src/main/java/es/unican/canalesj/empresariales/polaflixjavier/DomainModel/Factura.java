package es.unican.canalesj.empresariales.polaflixjavier.DomainModel;

import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Factura implements Comparable<Factura>{
    
    @Id
    @GeneratedValue
    private long id;

    private double costeTotal;
    private Date fecha;

    @ManyToOne
    @JsonBackReference
    private Usuario usuario;

    @ElementCollection
    private List<Entrada> entradasFactura;

    protected Factura(){
        
    }

    public Factura(Usuario usuario, Date fecha){
        this.costeTotal = 0;
        this.fecha = fecha;
        this.usuario = usuario;

        entradasFactura = new ArrayList<Entrada>();
    }

    public void agregarEntrada(Capitulo cap, Date fecha){
        Double coste = cap.getTemporada().getSerie().getCoste();
        String nombreSerie = cap.getTemporada().getSerie().getTitulo();
        int numTemporada = cap.getTemporada().getNumTemporada();
        int numCapitulo = cap.getNumCapitulo();
        entradasFactura.add(new Entrada(coste, fecha, nombreSerie, numTemporada, numCapitulo));
        this.costeTotal += coste;
    }

    //#region Getters
    public double getCosteTotal() {
        return costeTotal;
    }
    public Date getFecha() {
        return fecha;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public int getMes(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.fecha);
        return calendar.get(Calendar.MONTH)+1;
    }
    public int getAño(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.fecha);
        return calendar.get(Calendar.YEAR);
    }
    //#endregion

    //#region Setters
    public void setCosteTotal(double costeTotal) {
        this.costeTotal = costeTotal;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    //#endregion

    @Override
    public int hashCode(){
        return Objects.hash(costeTotal, fecha, usuario);
    }

    @Override
    public boolean equals(Object o){
        if(o == this){
            return true;
        }
        if(!(o instanceof Factura)){
            return false;
        }

        Factura factura = (Factura)o;
        return (this.costeTotal == factura.getCosteTotal()) && (this.getMes() == factura.getMes()) 
        && (this.getAño() == factura.getAño()) && (this.usuario.equals(factura.getUsuario()));
    }

    @Override
    public int compareTo(Factura factura){
        if(this.getAño() > factura.getAño()){
            return 1;
        } else if(this.getAño() < factura.getAño()){
            return -1;
        } else {
            if(this.getMes() > factura.getMes()){
                return 1;
            }else if(this.getMes() < factura.getMes()){
                return -1;
            }
        }
        return 0;
    }

}
