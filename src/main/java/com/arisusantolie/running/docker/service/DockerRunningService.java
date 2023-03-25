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
    @Autowired
    DockerClient dockerClient;

    /*
    Credit By : Ari Susanto Lie
    Website : https://arisusantolie.my.id
 */

    public String runDockerContainer(){
        String containerName= Utils.generateRandomName();
        List<String> parameter=new ArrayList<>();
        parameter.add("--programName=processManager");
//        parameter.add("--processManagerId="+data.getId());

//        List<Bind> binds=new ArrayList<>();
//        binds.add(Bind.parse(aesPath+":/dockerapp/scapps/scconfigserver/secure/aes"));
//        binds.add(Bind.parse(dataPath+":/dockerapp/scapps/data"));
        HostConfig hostConfig=new HostConfig();
        hostConfig.withCpusetCpus("0");
        hostConfig.withMemory(Long.parseLong("268435456")); //bytes, in this setting equal = 256MB
//        hostConfig.withNetworkMode("host");
//        hostConfig.withBinds(binds);
        CreateContainerResponse container=dockerClient.createContainerCmd("arisusantolie/executable-program:latest")
                .withCmd(parameter)
                .withName(containerName)
                .withEnv("PROFILE=dev")
                .withHostConfig(hostConfig)
//                        .withNetworkMode("host")
//                        .withBinds(Bind.parse("/dockerapp/scapps/scexecutableservice/test:/logs"))
//                        .withBinds(Bind.parse("/scapp/data:/scapp/data"))
//                        .withBinds(Bind.parse("/scapp/secure/aes:/scapp/secure/aes"))
////                        .withCpusetCpus("1")
//                        .withMemory(Long.parseLong("2099236864"))
                .exec();
        dockerClient.startContainerCmd(container.getId()).exec();

        return "Docker Container Running Successfully with name : "+containerName;
    }
}
