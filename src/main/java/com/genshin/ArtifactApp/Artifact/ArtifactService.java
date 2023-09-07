package com.genshin.ArtifactApp.Artifact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtifactService {

    private final ArtifactRepository artifactRepository;

    @Autowired
    public ArtifactService(ArtifactRepository artifactRepository) {
        this.artifactRepository = artifactRepository;
    }

    public List<Artifact> getArtifacts() {
        return artifactRepository.findAll();
    }

    public void addNewArtifact(Artifact artifact) {
        Optional<Artifact> artifactByType = artifactRepository
                .findArtifactByType(artifact.getType());

        // response 200 gives only "1" message
        if (artifactByType.isPresent()){
            throw new IllegalStateException("Type taken -- TODO");
        }

        artifactRepository.save(artifact);
    }

    public void deleteArtifact(Long artifactId) {
        boolean exists = artifactRepository.existsById(artifactId);
        if(!exists) {
            throw new IllegalStateException(
                    "student with id " + artifactId + " does not exists");
        }
        artifactRepository.deleteById(artifactId);

    }
}