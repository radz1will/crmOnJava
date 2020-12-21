package management.system.at.an.IT.enterprise.demo.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class ProjectManager {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public ProjectManager(){

    }
    public ProjectManager(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Project> projects = new ArrayList<>();
}
