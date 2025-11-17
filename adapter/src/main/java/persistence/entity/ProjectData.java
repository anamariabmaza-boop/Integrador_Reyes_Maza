package persistence.entity;

import jakarta.persistence.*;
import model.StatusProject;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity  // Indica que esta clase es una entidad JPA
@Table(name = "projects") // Nombre de la tabla en la BD
public class ProjectData {

    //En ID es la clave primaria
    @Id   //Marca a Long id como identificador primario de la entidad project
    @GeneratedValue(strategy = GenerationType.IDENTITY) //configura cómo se genera el ID.
    // Identity = autoincrement en muchas BDs.
    private Long id;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaskData> tasks = new ArrayList<>();

    @Column(nullable = false) //la columna no puede ser null en la BD
    private String name;
    // Guardar LocalDate sin zona horaria
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    //enum -> almacenar como STRING para legibilidad y compatibilidad
    @Enumerated(EnumType.STRING)  //guarda el nombre del enum ("PLANNED", "ACTIVE", "CLOSED")
    @Column(name = "project_status", nullable = false)
    private StatusProject projectStatus;
    @Column(nullable = false)
    private String description;

    // Constructor util cuando creas un proyecto nuevo (el ID no va)
    //NO LO PONEMOS EN EL CONSTRUCTOR porque JPA lo genera automaticamente
    //cuando guardo un proyecto nuevo en la bd.
    // Cuando creas un nuevo Project antes de persistirlo, NO tenés id.
    // Por eso se provee un constructor que no recibe id: lo genera la BD.

    public ProjectData() {

    }
    public ProjectData(String name,
            LocalDate startDate,
            LocalDate endDate,
            StatusProject projectStatus,
            String description) {

        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.projectStatus = projectStatus;
        this.description = description;
    }

    public Long getId() {return id;}
    public String getName() {return name;}
    public LocalDate getStartDate() {return startDate;}
    public LocalDate getEndDate() {return endDate;}
    public StatusProject getProjectStatus() {return projectStatus;}
    public String getDescription() {return description;}
    public List<TaskData> getTasks() { return tasks; }
    public void setId(Long id) {this.id = id;}

}