package com.genshin.ArtifactApp.Artifact.Service;

import com.genshin.ArtifactApp.Artifact.Artifact;
import com.genshin.ArtifactApp.Artifact.Repository.ArtifactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ArtifactService {

    private final ArtifactRepository artifactRepository;

    @Autowired
    public ArtifactService(ArtifactRepository artifactRepository) {
        this.artifactRepository = artifactRepository;
    }

    public List<Artifact> getAllArtifacts() {
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

    @Transactional
    public void updateArtifact(Long artifactId, String type, String mainStat){
        Optional<Artifact>  optionalArtifactById = artifactRepository
                .findArtifactById(artifactId);

        if (optionalArtifactById.isEmpty()){
            throw new IllegalStateException(
                    "Artifact with id " + artifactId + " does not exists"
            );
        }

        Artifact artifactById = optionalArtifactById.get();

        if (type != null
                && !type.isEmpty()
                && !Objects.equals(artifactById.getType(), type))
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

    public void deleteArtifact(Long artifactId) {
        Optional<Artifact> artifact = artifactRepository.findArtifactById(artifactId);
        if(artifact.isEmpty()) {
            throw new IllegalStateException(
                    "Artifact with id " + artifactId + " does not exists");
        }
        artifactRepository.deleteById(artifactId);

    }
}