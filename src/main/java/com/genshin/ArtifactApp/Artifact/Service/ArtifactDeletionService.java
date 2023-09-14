package com.genshin.ArtifactApp.Artifact.Service;

import com.genshin.ArtifactApp.Artifact.Artifact;
import com.genshin.ArtifactApp.Artifact.Repository.ArtifactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArtifactDeletionService {

    private final ArtifactRepository artifactRepository;
    @Autowired
    public ArtifactDeletionService(ArtifactRepository artifactRepository) {
        this.artifactRepository = artifactRepository;
    }

    public void deleteArtifact(Long artifactId) {
        Optional<Artifact> artifact = artifactRepository.findArtifactById(artifactId);
        if(artifact.isEmpty()) {
            throw new IllegalStateException(
                    "Artifact with id " + artifactId + " does not exists");
        }
        artifactRepository.deleteById(artifactId);

    }
}
