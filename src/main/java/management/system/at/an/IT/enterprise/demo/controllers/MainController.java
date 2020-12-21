package management.system.at.an.IT.enterprise.demo.controllers;
import management.system.at.an.IT.enterprise.demo.models.*;
import management.system.at.an.IT.enterprise.demo.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;



@Controller
public class MainController {
    @Autowired
    private ProjectRepo projectRepo;
    @Autowired
    private TaskRepo taskRepo;
    @Autowired
    private TeamLeadRepo teamLeadRepo;
    @Autowired
    private DeveloperRepo developerRepo;
    @Autowired
    private ProjectManagerRepo projectManagerRepo;

    @GetMapping("/getAllTasks")
    public String getAllTasks(Model model){
        Iterable<Tasks> tasks=taskRepo.findAll();
        model.addAttribute("tasks", tasks);
        return "task-main";
    }

    @GetMapping("/getAllProject")
    public String getAllProject(Model model) {
        Iterable<Project> projects=projectRepo.findAll();
        model.addAttribute("projects", projects);
        return "project";
    }

    @GetMapping("/")
    public String home(Model model) {
        Iterable<Project> projects=projectRepo.findAll();
        model.addAttribute("projects", projects);
        return "project";
    }

    @GetMapping("/getAllTeamLead")
    public String getAllTeamLead(Model model) {
        Iterable<TeamLead> teamLeads=teamLeadRepo.findAll();
        model.addAttribute("teamLead", teamLeads);
        return "teamLead";
    }


    @GetMapping("/getAllDeveloper")
    public String getAllDeveloper(Model model) {
        Iterable<Developers> developers=developerRepo.findAll();
        model.addAttribute("developers", developers);
        return "developers";
    }

    @GetMapping("/getAllProjectManager")
    public String getAllPM(Model model) {
        Iterable<ProjectManager> projectManagers=projectManagerRepo.findAll();
        model.addAttribute("projectManagers", projectManagers);
        return "projectManager";
    }

    @GetMapping("/addTask")
    public String addTaskGet(Model model){
        return "addTask";
    }

    @GetMapping("/addProject")
    public String addProjectGet(Model model){
        return "addProject";
    }

    @GetMapping("/addTeamLead")
    public String addTeamLeadGet(Model model){
        return "addTeamLead";
    }

    @GetMapping("/addDevelopers")
    public String addDeveloperGet(Model model){
        return "addDevelopers";
    }

    @GetMapping("/addProjectManager")
    public String addProjectManagerGet(Model model){
        return "addPM";
    }

    @GetMapping("/project/{id}")
    public String projectDetail(@PathVariable(value = "id") long id, Model model) {
        Optional<Project> projects=projectRepo.findById(id);
        ArrayList<Project> res =new ArrayList<>();
        projects.ifPresent(res::add);

        model.addAttribute("projects", res);
        return "project-detail";
    }

    @GetMapping("/tasks/{id}")
    public String taskDetail(@PathVariable(value = "id") long id, Model model) {
        Optional<Tasks> tasks=taskRepo.findById(id);
        ArrayList<Tasks> res =new ArrayList<>();
        tasks.ifPresent(res::add);

        model.addAttribute("task", res);
        return "task-detail";
    }

    @GetMapping("/tasks/{id}/edit")
    public String taskEdit(@PathVariable(value = "id") long id, Model model) {
        if (!taskRepo.existsById(id)){
            return "redirect:/";
        }
        Optional<Tasks> tasks=taskRepo.findById(id);
        ArrayList<Tasks> res =new ArrayList<>();
        tasks.ifPresent(res::add);

        model.addAttribute("task", res);
        return "task-edit";
    }

    @PostMapping("/addTask")
    public String addTaskPost(@RequestParam String name,@RequestParam Project project_id,@RequestParam String desc,@RequestParam Developers developer_id){

        System.out.println(project_id);
        Tasks expected = new Tasks(name,desc);
        expected.setProject(project_id);
        expected.setDevelopers(developer_id);
        taskRepo.save(expected);

        return "redirect:/getAllTasks";
    }


    @PostMapping("/addTeamLead")
    public String addTeamLeadPost(@RequestParam String name){

        TeamLead expected = new TeamLead(name);
        teamLeadRepo.save(expected);

        return "redirect:/getAllTeamLead";
    }

    @PostMapping("/addProjectManager")
    public String addProjectManagerPost(@RequestParam String name){

        ProjectManager expected = new ProjectManager(name);
        projectManagerRepo.save(expected);

        return "redirect:/getAllProjectManager";
    }

    @PostMapping("/addDevelopers")
    public String addDeveloperPost(@RequestParam String name){

        Developers expected = new Developers(name);
        developerRepo.save(expected);

        return "redirect:/getAllDeveloper";
    }

    @PostMapping("/addProject")
    public String addProjectPost(@RequestParam String name,@RequestParam ProjectManager project_manager_id,@RequestParam TeamLead team_lead_id){
        System.out.println(name);
        Project expected = new Project(name);
        expected.setProjectManager(project_manager_id);
        expected.setTeamLead(team_lead_id);

        projectRepo.save(expected);

        return "redirect:/getAllProject";
    }



    @PostMapping("/tasks/{id}/edit")
    public String updateTaskPost(@PathVariable(value = "id") long id,@RequestParam String desc, @RequestParam Developers developer_id){
      Optional<Tasks> task=taskRepo.findById(id);
      if (desc!=null){
          task.get().setTaskDesc(desc);
      }

   if (developer_id!=null){
            task.get().setDevelopers(developer_id);}

        taskRepo.save(task.get());

        return "redirect:/getAllTasks";
    }

    @PostMapping("/tasks/{id}/delete")
    public String deleteTaskPost(@PathVariable(value = "id") long id){
        taskRepo.deleteById(id);
        return "redirect:/getAllTasks";
    }

}