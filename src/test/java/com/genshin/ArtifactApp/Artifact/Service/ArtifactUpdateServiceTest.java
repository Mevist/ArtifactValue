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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ArtifactUpdateServiceTest {

    @Mock
    private ArtifactRepository artifactRepository;
    private ArtifactUpdateService underTest;
    private Artifact artifact;

    @BeforeEach
    void setUp(){
        underTest = new ArtifactUpdateService(artifactRepository);

        Artifact.ArtifactSubStat critRateStat = new Artifact.ArtifactSubStat("Crit Rate", 30.0f);
        artifact = new Artifact(
                "Circlet",
                "Crit DMG",
                List.of(critRateStat)
        );
    }

    @Test
    void canUpdateByTypeAndMainStat(){
        Long artifactId = 1L;
        String type = "Feather";
        String mainStat = "Atk";
        given(artifactRepository.findArtifactById(artifactId))
                .willReturn(Optional.of(artifact));

        underTest.updateArtifact(artifactId, type, mainStat);

        assertEquals(type, artifact.getType());
    }

    @Test
    void shouldUpdateOnlyByType(){
        Long artifactId = 1L;
        String type = "Feather";
        String mainStat = null;
        given(artifactRepository.findArtifactById(artifactId))
                .willReturn(Optional.of(artifact));

        underTest.updateArtifact(artifactId, type, mainStat);

        assertThat(artifact.getType()).isEqualTo(type);
        assertThat(artifact.getMainStat()).isNotEqualTo(mainStat);
    }

    @Test
    void shouldUpdateOnlyByMainState(){
        Long artifactId = 1L;
        String type = null;
        String mainStat = "Atk";
        given(artifactRepository.findArtifactById(artifactId))
                .willReturn(Optional.of(artifact));

        underTest.updateArtifact(artifactId, type, mainStat);

        assertThat(artifact.getMainStat()).isEqualTo(mainStat);
        assertThat(artifact.getType()).isNotEqualTo(type);
    }

    @Test
    void willThrowWhenTypeIsTaken()
    {
        Long artifactId = 1L;
        String type = "Circlet";
        String mainStat = null;
        given(artifactRepository.findArtifactById(artifactId))
                .willReturn(Optional.of(artifact));
        given(artifactRepository.findArtifactByType(type))
                .willReturn(Optional.of(artifact));

        assertThatThrownBy(() -> underTest.updateArtifact(artifactId, type, mainStat))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Type taken");

    }

    @Test
    void willThrowWhenNotFoundById(){
        Long artifactId = 1L;
        String type = "Circlet";
        String mainStat = null;

        given(artifactRepository.findArtifactById(artifactId))
                .willReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.updateArtifact(artifactId, type, mainStat))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Artifact with id " + artifactId + " does not exists");
    }

}