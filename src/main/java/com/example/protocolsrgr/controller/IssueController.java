package com.example.protocolsrgr.controller;

import com.example.protocolsrgr.model.Issue;
import com.example.protocolsrgr.model.Project;
import com.example.protocolsrgr.model.User;
import com.example.protocolsrgr.repository.IssueRepository;
import com.example.protocolsrgr.repository.ProjectRepository;
import com.example.protocolsrgr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("issue")
public class IssueController {

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping
    public String getIssues(
            Map<String, Object> model
    ) {
        model.put("issues", issueRepository.findAll());
        return "issue/issue";
    }

    @GetMapping("{id}")
    public String getIssue(
            @PathVariable Long id,
            Map<String, Object> model
    ) {
        issueRepository.findById(id).ifPresent(project -> model.put("issue", project));
        return "issue/issue_details";
    }

    @PostMapping
    public String createIssue(
            @RequestParam Map<String, String> params
    ) {
        String name = params.get("name");
        params.remove("name");
        String details = params.get("details");
        params.remove("details");

        Long projectId = Long.valueOf(params.get("project"));
        params.remove("project");
        Project project = projectRepository.findById(projectId).get();

        List<User> users = new ArrayList<>();
        params.keySet().stream().map(Long::parseLong).forEach(id ->
                userRepository.findById(id).ifPresent(users::add)
        );
        Issue issue = new Issue(name, details, users, project);
        issueRepository.save(issue);
        return "redirect:/project/" + project.getId();
    }

}
