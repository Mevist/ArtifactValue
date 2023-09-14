package com.genshin.ArtifactApp.Artifact.Service;

import com.genshin.ArtifactApp.Artifact.Artifact;
import com.genshin.ArtifactApp.Artifact.Repository.ArtifactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ArtifactDeletionServiceTest {

    @Mock
    private ArtifactRepository artifactRepository;
    private ArtifactDeletionService underTest;

    @BeforeEach
    void setUp(){
        underTest = new ArtifactDeletionService(artifactRepository);
    }
    @Test
    void canDeleteArtifactById() {
        Long artifactId = 1L;

        Artifact.ArtifactSubStat atkStat = new Artifact.ArtifactSubStat("Atk", 30.0f);
        Artifact defaultArtifact = new Artifact(
                "Feather",
                "Crit DMG",
                List.of(atkStat)
        );
        given(artifactRepository.findArtifactById(1L))
                .willReturn(Optional.of(defaultArtifact));

        underTest.deleteArtifact(artifactId);

        verify(artifactRepository).deleteById(artifactId);
    }

    @Test
    void willThrowWhenObjectDoesNotExists(){
        Long artifactId = 2L;

        given(artifactRepository.findArtifactById(artifactId))
                .willReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.deleteArtifact(artifactId))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Artifact with id " + artifactId + " does not exists");

        verify(artifactRepository, never()).deleteById(any());
    }
}