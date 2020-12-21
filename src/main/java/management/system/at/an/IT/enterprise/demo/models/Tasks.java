package management.system.at.an.IT.enterprise.demo.models;

import javax.persistence.*;
import java.util.Set;


@Entity
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private String taskDesc;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public Tasks(){

    }
    public Tasks(String name, String desc){
        this.name=name;
        this.taskDesc=desc;
    }

    public String getName() {
        return name;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }




    public void setProject(Project project) {
        this.project = project;
    }

    public Project getProject() {
        return project;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;


    @ManyToOne(fetch = FetchType.LAZY)
    private Developers developers;
    public Developers getDevelopers() {
        return developers;
    }
    public void setDevelopers(Developers developers) {
        this.developers = developers;
    }

}
