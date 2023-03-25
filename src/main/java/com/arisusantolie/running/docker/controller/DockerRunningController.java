package com.arisusantolie.running.docker.controller;

import com.arisusantolie.running.docker.service.DockerRunningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/docker")
public class DockerRunningController {
    /*
      Credit By : Ari Susanto Lie
      Website : https://arisusantolie.my.id
    */
    @Autowired
    DockerRunningService dockerRunningService;

    @GetMapping("running")
    public String runningDockerImage(){
        return dockerRunningService.runDockerContainer();
    }
}
