package com.genshin.ArtifactApp.Artifact;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ArtifactServiceTest {
    @Mock private ArtifactRepository artifactRepository;
    private ArtifactService underTest;

    @BeforeEach
    void setUp(){
        underTest = new ArtifactService(artifactRepository);
    }

    @Test
    void canGetAllArtifacts() {
        underTest.getAllArtifacts();

        verify(artifactRepository).findAll();
    }

    @Test
    void canAddNewArtifact() {
        Artifact.ArtifactSubStat critRateStat = new Artifact.ArtifactSubStat("Crit Rate", 30.0f);
        Artifact artifact = new Artifact(
                "Circlet",
                "Crit DMG",
                List.of(critRateStat)
        );

        underTest.addNewArtifact(artifact);

        ArgumentCaptor<Artifact> artifactArgumentCaptor =
                ArgumentCaptor.forClass((Artifact.class));

        verify(artifactRepository)
                .save(artifactArgumentCaptor.capture());

        Artifact capturedArtifact = artifactArgumentCaptor.getValue();
        assertThat(capturedArtifact).isEqualTo(artifact);
    }

    @Test
    void willThrowWhenTypeIsTaken() {
        Artifact.ArtifactSubStat critRateStat = new Artifact.ArtifactSubStat("Crit Rate", 30.0f);
        Artifact artifact = new Artifact(
                "Circlet",
                "Crit DMG",
                List.of(critRateStat)
        );

        given(artifactRepository.findArtifactByType(artifact.getType()))
                        .willReturn(Optional.of(artifact));

        assertThatThrownBy(() -> underTest.addNewArtifact(artifact))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Type taken -- TODO");

        verify(artifactRepository, never()).save(any());
    }

    @Test
    @Disabled
    void updateArtifact() {
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