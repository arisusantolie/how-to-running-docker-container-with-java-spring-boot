package com.arisusantolie.running.docker.service;

import com.arisusantolie.running.docker.util.Utils;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.Bind;
import com.github.dockerjava.api.model.HostConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class DockerRunningService {
    /*
      Credit By : Ari Susanto Lie
      Website : https://arisusantolie.my.id
    */
    @Autowired
    DockerClient dockerClient;

    public String runDockerContainer(){
        String containerName= Utils.generateRandomName();

        /* to passing args we can use parameter */
        List<String> parameter=new ArrayList<>();
        parameter.add("--programName=processManager");
//        parameter.add("anotherParam="+data.getAnotherParam());

        /* to add binding, we can use Bind */
//        List<Bind> binds=new ArrayList<>();
//        binds.add(Bind.parse("path1:path1"));
//        binds.add(Bind.parse("path2:path2"));

        HostConfig hostConfig=new HostConfig();
        hostConfig.withCpusetCpus("0");
        hostConfig.withMemory(Long.parseLong("268435456")); //bytes, in this setting equal = 256MB
        /* network config */
//        hostConfig.withNetworkMode("host");
        /* bind config */
//        hostConfig.withBinds(binds);
        CreateContainerResponse container=dockerClient.createContainerCmd("arisusantolie/executable-program:latest")
                .withCmd(parameter)
                .withName(containerName)
                .withEnv("PROFILE=dev")
                .withHostConfig(hostConfig)
                .exec();
        dockerClient.startContainerCmd(container.getId()).exec();

        return "Docker Container Running Successfully with name : "+containerName;
    }
}
