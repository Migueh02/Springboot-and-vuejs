/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo2.PractcaFW.Modelo;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Data
@Entity
@Table(name = "ESTUDIANTE")

public class ESTUDIANTE {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private int id;
    private int cc;
    private String Nombre;
    private String Apellido;
    private String Telefono;
    private String Email;
    private String Carrera;

    public ESTUDIANTE(int id, int cc, String Nombre, String Apellido, String Telefono, String Email, String Carrera) {
        this.id = id;
        this.cc = cc;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Telefono = Telefono;
        this.Email = Email;
        this.Carrera = Carrera;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCc() {
        return cc;
    }

    public void setCc(int cc) {
        this.cc = cc;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getCarrera() {
        return Carrera;
    }

    public void setCarrara(String Carrara) {
        this.Carrera = Carrara;
    }

    public ESTUDIANTE() {
    }
}
