package com.example.protocolsrgr.controller;

import com.example.protocolsrgr.model.Project;
import com.example.protocolsrgr.model.User;
import com.example.protocolsrgr.repository.ProjectRepository;
import com.example.protocolsrgr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String getProjects(
            Map<String, Object> model
    ) {
        model.put("projects", projectRepository.findAll());
        model.put("users", userRepository.findAll());
        return "project/project";
    }

    @GetMapping("{id}")
    public String getProject(
            @PathVariable Long id,
            Map<String, Object> model
    ) {
        projectRepository.findById(id).ifPresent(project -> model.put("project", project));
        return "project/project_details";
    }

    @PostMapping
    public String createProject(
            @RequestParam Map<String, String> params
    ) {
        String name = params.get("name");
        params.remove("name");
        String details = params.get("details");
        params.remove("details");
        List<User> users = new ArrayList<>();
        params.keySet().stream().map(Long::parseLong).forEach(id ->
                userRepository.findById(id).ifPresent(users::add)
        );
        Project project = projectRepository.save(new Project(name, details, users));
        return "redirect:/project/" + project.getId();
    }

    @DeleteMapping("id")
    public String deleteProject(
            @PathVariable Long id
    ) {
        projectRepository.deleteById(id);
        return "redirect:/project/";
    }

}
