package es.unican.canalesj.empresariales.polaflixjavier.DomainModel;

import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;

import es.unican.canalesj.empresariales.polaflixjavier.Views;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Factura implements Comparable<Factura>{
    
    @Id
    @GeneratedValue
    @JsonView({Views.DescripcionFactura.class})
    private long id;

    @JsonView({Views.DescripcionFactura.class})
    private double costeTotal;
    @JsonView({Views.DescripcionFactura.class})
    private Date fecha;
    @JsonView({Views.DescripcionFactura.class})
    private int mes;
    @JsonView({Views.DescripcionFactura.class})
    private int año;

    @ManyToOne
    @JsonBackReference
    private Usuario usuario;

    @ElementCollection
    @JsonView({Views.DescripcionFactura.class})
    private List<Entrada> entradasFactura;

    protected Factura(){
        
    }

    public Factura(Usuario usuario, Date fecha){
        this.costeTotal = 0;
        this.fecha = fecha;
        this.usuario = usuario;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        this.mes = calendar.get(Calendar.MONTH)+1;
        this.año = calendar.get(Calendar.YEAR);

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
        return mes;
    }
    public int getAño(){
        return año;
    }
    //#endregion

    //#region Setters
    public void setCosteTotal(double costeTotal) {
        this.costeTotal = costeTotal;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        this.mes = calendar.get(Calendar.MONTH)+1;
        this.año = calendar.get(Calendar.YEAR);
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    //#endregion

    @Override
    public int hashCode(){
        return Objects.hash(costeTotal, mes, año, usuario);
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
        return (this.costeTotal == factura.getCosteTotal()) && (this.mes == factura.getMes()) 
        && (this.año == factura.getAño()) && (this.usuario.equals(factura.getUsuario()));
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
