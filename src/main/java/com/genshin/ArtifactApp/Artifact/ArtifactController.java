package com.genshin.ArtifactApp.Artifact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/artifact")
public class ArtifactController {

    private final ArtifactService artifactService;

    @Autowired
    public ArtifactController(ArtifactService artifactService) {
        this.artifactService = artifactService;
    }

    @GetMapping
    public List<Artifact> getArtifacts() {
        return artifactService.getArtifacts();
    }

    @PostMapping
    public void addArtifact(@RequestBody Artifact artifact) {
        artifactService.addNewArtifact(artifact);
    }
    @DeleteMapping(path = "{artifactId}")
    public void deleteArtifact(@PathVariable("artifactId") Long artifactId) {
        artifactService.deleteArtifact(artifactId);
    }
}
