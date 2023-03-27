package es.unican.canalesj.empresariales.polaflixjavier.DomainModel;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
@DiscriminatorColumn(name="tipo")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public abstract class Usuario {
    
    @Id
    private String Username;
    private String Password;
    private String IBAN;

    @ManyToMany
    private Set<Serie> Empezadas;
    @ManyToMany
    private Set<Serie> Pendientes;
    @ManyToMany
    private Set<Serie> Terminadas;

    @OneToMany(mappedBy = "Usuario")
    private SortedSet<Factura> Facturas;

    @OneToMany
    private Set<Capitulo> CapitulosVistos;

    public Usuario(String Username, String Password, String IBAN){

        this.Username = Username;
        this.Password = Password;
        this.IBAN = IBAN;

        Empezadas = new HashSet<Serie>();
        Pendientes = new HashSet<Serie>();
        Terminadas = new HashSet<Serie>();

        Facturas = new TreeSet<Factura>();

        CapitulosVistos = new HashSet<>();
    }

    public void verCapitulo(Capitulo cap){
        CapitulosVistos.add(cap);

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
    public Set<Serie> getEmpezadas() {
        return Empezadas;
    }
    public Set<Serie> getPendientes() {
        return Pendientes;
    }
    public Set<Serie> getTerminadas() {
        return Terminadas;
    }
    public SortedSet<Factura> getFacturas() {
        return Facturas;
    }
    public Set<Capitulo> getCapitulosVistos() {
        return CapitulosVistos;
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
    public void setEmpezadas(Set<Serie> empezadas) {
        Empezadas = empezadas;
    }
    public void setPendientes(Set<Serie> pendientes) {
        Pendientes = pendientes;
    }
    public void setTerminadas(Set<Serie> terminadas) {
        Terminadas = terminadas;
    }
    public void setFacturas(SortedSet<Factura> facturas) {
        Facturas = facturas;
    }
    public void setCapitulosVistos(Set<Capitulo> capitulosVistos) {
        CapitulosVistos = capitulosVistos;
    }
    //#endregion

    @Override
    public int hashCode(){
        return Objects.hash(Username, Password, IBAN, Empezadas, Pendientes, Terminadas, Facturas, CapitulosVistos);
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
        return ((this.Username.equals(usuario.getUsername())) && (this.Password.equals(usuario.getPassword()))
                 && (this.IBAN.equals(usuario.getIBAN())) && (this.Pendientes.equals(usuario.getPendientes()))
                 && (this.Empezadas.equals(usuario.getEmpezadas())) && (this.Terminadas.equals(usuario.Terminadas))
                 && (this.CapitulosVistos.equals(usuario.getCapitulosVistos())) && (this.Facturas.equals(usuario.getFacturas())));
    }
}
