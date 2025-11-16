package persistence.repository;

import persistence.entity.TaskData;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Esta interfaz ES mi repositorio.
 * No tiene código adentro.
 * No implementa metodos.
 * Spring Data JPA automáticamente crea la implementación en tiempo de ejecución.
 * Por que funciona?
 * Porque extends JpaRepository le dice a Spring:
 *  "Generame el CRUD completo para ProjectData con ID tipo Long".
 */
public interface TaskRepositoryImpl extends JpaRepository<TaskData, Long> {
}
