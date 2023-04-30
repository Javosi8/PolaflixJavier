package es.unican.canalesj.empresariales.polaflixjavier.DomainModel;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
@DiscriminatorColumn(name="Tipo")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public abstract class Usuario {
    
    @Id
    private String username;
    private String password;
    private String iban;

    @ManyToMany
    private Set<Serie> empezadas;
    @ManyToMany
    private Set<Serie> pendientes;
    @ManyToMany
    private Set<Serie> terminadas;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonManagedReference
    private SortedSet<Factura> facturas;

    @OneToMany
    private Set<Capitulo> capitulosVistos;

    protected Usuario(){
        
    }

    public Usuario(String username, String password, String iban){

        this.username = username;
        this.password = password;
        this.iban = iban;

        empezadas = new HashSet<Serie>();
        pendientes = new HashSet<Serie>();
        terminadas = new HashSet<Serie>();

        facturas = new TreeSet<Factura>();
        facturas.add(new Factura(this, new Date()));

        capitulosVistos = new HashSet<>();
    }

    public void verCapitulo(Capitulo cap){
        capitulosVistos.add(cap);

        Date fechaActual = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaActual);
        int mes = calendar.get(Calendar.MONTH)+1;
        int año = calendar.get(Calendar.YEAR);

        Factura facturaVigente = facturas.last();
        if((facturaVigente.getMes() == mes) && (facturaVigente.getAño() == año)){
            facturaVigente.agregarEntrada(cap, fechaActual);
        } else {
            Factura nuevaFactura = new Factura(this, fechaActual);
            nuevaFactura.agregarEntrada(cap, fechaActual);
            facturas.add(facturaVigente);
        }
        if(!empezadas.contains(cap.getTemporada().getSerie())){
            agregarSerieAEmpezadas(cap.getTemporada().getSerie());
        }

        if(cap.getTemporada().getSerie().getTemporadas().last().getCapitulos().last().getNumCapitulo() == cap.getNumCapitulo()){
            agregarSerieATerminadas(cap.getTemporada().getSerie());
        }

    }

    public void agregarSerieAPendientes(Serie serie){
        if((!terminadas.contains(serie)) && (!empezadas.contains(serie))){
            pendientes.add(serie);
        }   
    }

    public void agregarSerieAEmpezadas(Serie serie){
        if(terminadas.contains(serie)){
            terminadas.remove(serie);
        }

        if(pendientes.contains(serie)){
            pendientes.remove(serie);
        }
        
        empezadas.add(serie);
    }

    public void agregarSerieATerminadas(Serie serie){
        if(pendientes.contains(serie)){
            pendientes.remove(serie);
        }

        if(empezadas.contains(serie)){
            empezadas.remove(serie);
        }
        
        terminadas.add(serie);
    }


    //#region Getters
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getIBAN() {
        return iban;
    }
    public Set<Serie> getEmpezadas() {
        return empezadas;
    }
    public Set<Serie> getPendientes() {
        return pendientes;
    }
    public Set<Serie> getTerminadas() {
        return terminadas;
    }
    public SortedSet<Factura> getFacturas() {
        return facturas;
    }
    public Set<Capitulo> getCapitulosVistos() {
        return capitulosVistos;
    }
    //#endregion

    //#region Setters
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setIBAN(String iban) {
        this.iban = iban;
    }
    public void setEmpezadas(Set<Serie> empezadas) {
        this.empezadas = empezadas;
    }
    public void setPendientes(Set<Serie> pendientes) {
        this.pendientes = pendientes;
    }
    public void setTerminadas(Set<Serie> terminadas) {
        this.terminadas = terminadas;
    }
    public void setFacturas(SortedSet<Factura> facturas) {
        this.facturas = facturas;
    }
    public void setCapitulosVistos(Set<Capitulo> capitulosVistos) {
        this.capitulosVistos = capitulosVistos;
    }
    //#endregion

    @Override
    public int hashCode(){
        return Objects.hash(username, password, iban);
    }

    @Override
    public boolean equals(Object o){
        if(o == this){
            return true;
        }

        if(!(o instanceof Serie)){
            return false;
        }

        Usuario usuario = (Usuario)o;
        return ((this.username.equals(usuario.getUsername())) && (this.password.equals(usuario.getPassword()))
                 && (this.iban.equals(usuario.getIBAN())));
    }
}
