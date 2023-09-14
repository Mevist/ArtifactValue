package com.genshin.ArtifactApp.Artifact.Service;

import com.genshin.ArtifactApp.Artifact.Artifact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtifactService {
    private final ArtifactRetrievalService artifactRetrievalService;
    private final ArtifactCreationService artifactCreationService;
    private final ArtifactUpdateService artifactUpdateService;
    private final ArtifactDeletionService artifactDeletionService;

    @Autowired
    public ArtifactService(ArtifactRetrievalService artifactRetrievalService,
                           ArtifactCreationService artifactCreationService,
                           ArtifactUpdateService artifactUpdateService,
                           ArtifactDeletionService artifactDeletionService) {
        this.artifactRetrievalService = artifactRetrievalService;
        this.artifactCreationService = artifactCreationService;
        this.artifactUpdateService = artifactUpdateService;
        this.artifactDeletionService = artifactDeletionService;
    }

    public List<Artifact> getAllArtifacts() {
        return artifactRetrievalService.getAllArtifacts();
    }

    public void addNewArtifact(Artifact artifact) {
        artifactCreationService.addNewArtifact(artifact);
    }

    public void updateArtifact(Long artifactId, String type, String mainStat) {
        artifactUpdateService.updateArtifact(artifactId, type, mainStat);
    }

    public void deleteArtifact(Long artifactId) {
        artifactDeletionService.deleteArtifact(artifactId);
    }
}