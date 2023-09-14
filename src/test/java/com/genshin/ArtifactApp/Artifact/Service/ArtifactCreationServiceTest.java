package com.genshin.ArtifactApp.Artifact.Service;

import com.genshin.ArtifactApp.Artifact.Artifact;
import com.genshin.ArtifactApp.Artifact.Repository.ArtifactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ArtifactCreationServiceTest {

    @Mock
    private ArtifactRepository artifactRepository;
    private ArtifactCreationService underTest;

    @BeforeEach
    void setUp(){
        underTest = new ArtifactCreationService(artifactRepository);
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
}