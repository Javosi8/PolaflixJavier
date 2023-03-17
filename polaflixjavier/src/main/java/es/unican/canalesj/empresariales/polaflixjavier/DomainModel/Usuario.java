package es.unican.canalesj.empresariales.polaflixjavier.DomainModel;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@DiscriminatorColumn(name="tipo")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public abstract class Usuario {
    
    @Id
    @GeneratedValue
    private long id;

    private String Username;
    private String Password;
    private String IBAN;

    private Set<Serie> Empezadas;
    private Set<Serie> Pendientes;
    private Set<Serie> Terminadas;

    private SortedSet<Factura> Facturas;

    private Map<Serie, Capitulo> UltimoCapitulo;

    public Usuario(String Username, String Password, String IBAN){

        this.Username = Username;
        this.Password = Password;
        this.IBAN = IBAN;

        Empezadas = new HashSet<Serie>();
        Pendientes = new HashSet<Serie>();
        Terminadas = new HashSet<Serie>();

        Facturas = new TreeSet<Factura>();

        UltimoCapitulo = new HashMap<Serie, Capitulo>();
    }

    public void verCapitulo(Capitulo cap){
        Serie serie = cap.getTemporada().getSerie();
        UltimoCapitulo.put(serie, cap);

        Date fechaActual = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaActual);
        int mes = calendar.get(Calendar.MONTH);
        int año = calendar.get(Calendar.YEAR);

        Factura facturaVigente = Facturas.last();
        if((facturaVigente.getMes() == mes) && (facturaVigente.getAño() == año)){
            facturaVigente.agregarEntrada(cap, fechaActual);
        } else {
            Factura nuevaFactura = new Factura(this, fechaActual);
            nuevaFactura.agregarEntrada(cap, fechaActual);
            Facturas.add(facturaVigente);
        }
        
    }

    public void agregarSerieAPendientes(Serie serie){
        if(Terminadas.contains(serie)){
            Terminadas.remove(serie);
        }

        if(Empezadas.contains(serie)){
            Empezadas.remove(serie);
        }
        
        Pendientes.add(serie);
    }

    public void agregarSerieAEmpezadas(Serie serie){
        if(Terminadas.contains(serie)){
            Terminadas.remove(serie);
        }

        if(Pendientes.contains(serie)){
            Pendientes.remove(serie);
        }
        
        Empezadas.add(serie);
    }

    public void agregarSerieATerminadas(Serie serie){
        if(Pendientes.contains(serie)){
            Pendientes.remove(serie);
        }

        if(Empezadas.contains(serie)){
            Empezadas.remove(serie);
        }
        
        Terminadas.add(serie);
    }


    //#region Getters
    public String getUsername() {
        return Username;
    }
    public String getPassword() {
        return Password;
    }
    public String getIBAN() {
        return IBAN;
    }
    //#endregion

    //#region Setters
    public void setUsername(String username) {
        Username = username;
    }
    public void setPassword(String password) {
        Password = password;
    }
    public void setIBAN(String iBAN) {
        IBAN = iBAN;
    }
    //#endregion
    
}
