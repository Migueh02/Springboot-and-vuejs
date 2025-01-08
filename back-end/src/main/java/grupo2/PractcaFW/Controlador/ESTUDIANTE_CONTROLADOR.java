package grupo2.PractcaFW.Controlador;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import grupo2.PractcaFW.REPOSITORIO.ESTUDIANTE_CRUD_REPOSITORIO;
import grupo2.PractcaFW.Modelo.ESTUDIANTE;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/uniminuto")
@CrossOrigin(origins = "*")
public class ESTUDIANTE_CONTROLADOR {
    
    @Autowired
    private ESTUDIANTE_CRUD_REPOSITORIO estudianteCrudRepositorio;

    @GetMapping("/")
    public String home() {
        return "Bienvenido a la API de Uniminuto";
    }

    // Obtener todos los estudiantes
    @GetMapping("/estudiantes")
    public ResponseEntity<List<ESTUDIANTE>> getAllEstudiantes() {
        List<ESTUDIANTE> estudiantes = estudianteCrudRepositorio.findAll();
        return new ResponseEntity<>(estudiantes, HttpStatus.OK);
    }

    // Buscar estudiante por cédula
    @GetMapping("/cc/{cedula}")
    public ResponseEntity<ESTUDIANTE> getByCc(@PathVariable("cedula") int cedula) {
        Optional<ESTUDIANTE> estudiante = estudianteCrudRepositorio.findByCc(cedula);
        return estudiante.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                         .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Buscar estudiante por ID
    @GetMapping("/id/{id}")
    public ResponseEntity<ESTUDIANTE> getById(@PathVariable("id") int id) {
        Optional<ESTUDIANTE> estudiante = estudianteCrudRepositorio.findById(id);
        return estudiante.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                         .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Crear un nuevo estudiante
    @PostMapping("/estudiantes")
    public ResponseEntity<ESTUDIANTE> createEstudiante(@RequestBody ESTUDIANTE estudiante) {
        return new ResponseEntity<>(estudianteCrudRepositorio.save(estudiante), HttpStatus.CREATED);
    }

    // Actualizar un estudiante existente
    @PutMapping("/estudiantes/{id}")
    public ResponseEntity<ESTUDIANTE> updateEstudiante(@PathVariable("id") int id, @RequestBody ESTUDIANTE estudiante) {
        return estudianteCrudRepositorio.findById(id)
                .map(existing -> {
                    estudiante.setId(id);
                    return new ResponseEntity<>(estudianteCrudRepositorio.save(estudiante), HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Eliminar un estudiante por ID
    @DeleteMapping("/estudiantes/{id}")
    public ResponseEntity<Void> deleteEstudiante(@PathVariable("id") int id) {
        if (estudianteCrudRepositorio.existsById(id)) {
            estudianteCrudRepositorio.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
