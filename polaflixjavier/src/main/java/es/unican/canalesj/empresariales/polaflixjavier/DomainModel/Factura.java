package es.unican.canalesj.empresariales.polaflixjavier.DomainModel;

import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Factura implements Comparable<Factura>{
    
    @Id
    @GeneratedValue
    private long id;

    private double CosteTotal;
    private Date Fecha;

    @ManyToOne
    private Usuario Usuario;

    @OneToMany
    private List<Entrada> EntradasFactura;

    public Factura(Usuario usuario, Date fecha){
        this.CosteTotal = 0;
        this.Fecha = fecha;
        this.Usuario = usuario;

        EntradasFactura = new ArrayList<Entrada>();
    }

    public void agregarEntrada(Capitulo cap, Date fecha){
        Double coste = cap.getTemporada().getSerie().getCoste();
        String nombreSerie = cap.getTemporada().getSerie().getTitulo();
        int numTemporada = cap.getTemporada().getNumTemporada();
        int numCapitulo = cap.getNumCapitulo();
        EntradasFactura.add(new Entrada(coste, fecha, nombreSerie, numTemporada, numCapitulo));
        this.CosteTotal += coste;
    }

    //#region Getters
    public double getCosteTotal() {
        return CosteTotal;
    }
    public Date getFecha() {
        return Fecha;
    }
    public Usuario getUsuario() {
        return Usuario;
    }
    public int getMes(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.Fecha);
        return calendar.get(Calendar.MONTH);
    }
    public int getAño(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.Fecha);
        return calendar.get(Calendar.YEAR);
    }
    //#endregion

    //#region Setters
    public void setCosteTotal(double costeTotal) {
        CosteTotal = costeTotal;
    }

    public void setFecha(Date fecha) {
        Fecha = fecha;
    }
    public void setUsuario(Usuario usuario) {
        Usuario = usuario;
    }
    //#endregion

    @Override
    public int hashCode(){
        return Objects.hash(CosteTotal, Fecha);
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
        return (this.CosteTotal == factura.getCosteTotal()) && (this.getMes() == factura.getMes()) 
        && (this.getAño() == factura.getAño()) && (this.Usuario.equals(factura.getUsuario()));
    }

    @Override
    public int compareTo(Factura factura){
        if(this.getAño() > factura.getAño()){
            return 1;
        } else if(this.getAño() < factura.getAño()){
            return -1;
        } else if(this.getAño() == factura.getAño()){
            if(this.getMes() > factura.getMes()){
                return 1;
            }else if(this.getMes() < factura.getMes()){
                return -1;
            }
        }
        return 0;
    }

}
