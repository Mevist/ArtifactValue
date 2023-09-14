package com.genshin.ArtifactApp.Artifact.Service;

import com.genshin.ArtifactApp.Artifact.Artifact;
import com.genshin.ArtifactApp.Artifact.Repository.ArtifactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArtifactCreationService {

    private final ArtifactRepository artifactRepository;

    @Autowired
    public ArtifactCreationService(ArtifactRepository artifactRepository) {
        this.artifactRepository = artifactRepository;
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
}
