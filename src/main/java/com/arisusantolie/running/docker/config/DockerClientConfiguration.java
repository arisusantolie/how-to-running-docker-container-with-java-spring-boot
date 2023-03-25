package com.arisusantolie.running.docker.config;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DockerClientConfiguration {
    /*
      Credit By : Ari Susanto Lie
      Website : https://arisusantolie.my.id
    */
    @Bean
    public DockerClient dockerClient(){
        //"unix:///var/run/docker.sock" -> default value for docker host for unix (Linux/MacOs)

        String osName = System.getProperty("os.name").toLowerCase();
        String dockerHost=null;
        if (osName.contains("win")) {
            // Docker on Windows
            dockerHost="npipe:////./pipe/docker_engine";
        } else if (osName.contains("mac") || osName.contains("nix") || osName.contains("nux")) {
            // Docker on macOS, Linux, or Unix
            dockerHost="unix:///var/run/docker.sock";
        } else {
            throw new RuntimeException("Unsupported operating system: " + osName);
        }

        DefaultDockerClientConfig config
                = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withDockerHost(dockerHost).build();

        DockerHttpClient httpClient = new ApacheDockerHttpClient.Builder()
                .dockerHost(config.getDockerHost())
                .build();

        DockerClient dockerClient=DockerClientBuilder.getInstance().withDockerHttpClient(httpClient).build();
        return dockerClient;
    }
}
