package management.system.at.an.IT.enterprise.demo.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity

public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    public String name;

    public Project(){

    }
    public Project(String name){
        this.name=name;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
    private List<Tasks> tasks = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private ProjectManager projectManager;
    public ProjectManager getProjectManager() {
        return projectManager;
    }
    public void setProjectManager(ProjectManager projectManager) {
        this.projectManager = projectManager;
    }
    public void setTeamLead(TeamLead teamLead) {
        this.teamLead = teamLead;
    }
    public TeamLead getTeamLead() {
        return teamLead;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    private TeamLead teamLead;
}
