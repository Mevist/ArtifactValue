package com.genshin.ArtifactApp.Artifact.Service;

import com.genshin.ArtifactApp.Artifact.Artifact;
import com.genshin.ArtifactApp.Artifact.Repository.ArtifactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
public class ArtifactUpdateService {
    private final ArtifactRepository artifactRepository;

    @Autowired
    public ArtifactUpdateService(ArtifactRepository artifactRepository) {
        this.artifactRepository = artifactRepository;
    }

    @Transactional
    public void updateArtifact(Long artifactId, String type, String mainStat){
        Optional<Artifact> optionalArtifactById = artifactRepository
                .findArtifactById(artifactId);

        if (optionalArtifactById.isEmpty()){
            throw new IllegalStateException(
                    "Artifact with id " + artifactId + " does not exists"
            );
        }

        Artifact artifactById = optionalArtifactById.get();

        if (type != null
                && !type.isEmpty())
        {
            Optional<Artifact> optionalArtifactByType = artifactRepository
                    .findArtifactByType(type);
            if (optionalArtifactByType.isPresent()){
                throw new IllegalStateException("Type taken");
            }

            artifactById.setType(type);
        }

        if (mainStat != null
                && !mainStat.isEmpty()
                && !Objects.equals(artifactById.getMainStat(), mainStat)){
            artifactById.setMainStat((mainStat));
        }
    }
}
