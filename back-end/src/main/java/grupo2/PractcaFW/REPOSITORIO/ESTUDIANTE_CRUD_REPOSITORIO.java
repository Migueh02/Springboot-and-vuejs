/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo2.PractcaFW.REPOSITORIO;


import org.springframework.data.jpa.repository.JpaRepository; // persistencia inyectando codigo sql
import org.springframework.data.repository.CrudRepository;
import grupo2.PractcaFW.Modelo.ESTUDIANTE;
import java.util.List;

import java.util.Optional;

public interface ESTUDIANTE_CRUD_REPOSITORIO extends JpaRepository<ESTUDIANTE, Integer> {
    Optional<ESTUDIANTE> findByCc(int cc);
}