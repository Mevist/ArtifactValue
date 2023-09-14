package com.genshin.ArtifactApp.Artifact.Repository;

import com.genshin.ArtifactApp.Artifact.Artifact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArtifactRepository
        extends JpaRepository<Artifact, Long> {

    @Query("SELECT item FROM Artifact item WHERE item.type =?1")
    Optional<Artifact> findArtifactByType(String type);

    @Query("SELECT item FROM Artifact item WHERE item.id =?1")
    Optional<Artifact> findArtifactById(Long artifactId);
}
