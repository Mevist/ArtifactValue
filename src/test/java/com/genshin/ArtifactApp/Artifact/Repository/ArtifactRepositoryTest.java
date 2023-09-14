package com.genshin.ArtifactApp.Artifact.Repository;

import com.genshin.ArtifactApp.Artifact.Artifact;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class ArtifactRepositoryTest {

    @Autowired
    private ArtifactRepository underTest;
    @Test
    void itShouldCheckIfArtifactExistsByType() {
        Artifact.ArtifactSubStat defStat = new Artifact.ArtifactSubStat("Def", 30.0f);
        Artifact artifact = new Artifact(
                "Sands",
                "HP",
                List.of(defStat)
        );
        underTest.save(artifact);

        Optional<Artifact> exists = underTest.findArtifactByType("Sands");

        assertTrue(exists.isPresent());
        assertThat(exists.get()).isEqualTo(artifact);
    }

    @Test
    void itShouldCheckIfArtifactExistsById() {
        Artifact.ArtifactSubStat defStat = new Artifact.ArtifactSubStat("Atk", 30.0f);
        Artifact artifact = new Artifact(
                "Circlet",
                "Crit DMG",
                List.of(defStat)
        );
        underTest.save(artifact);

        Optional<Artifact> exists = underTest.findArtifactById(1L);

        assertTrue(exists.isPresent());
        assertThat(exists.get()).isEqualTo(artifact);
    }
}