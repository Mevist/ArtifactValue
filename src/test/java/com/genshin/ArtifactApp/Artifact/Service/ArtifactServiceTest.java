package com.genshin.ArtifactApp.Artifact.Service;

import com.genshin.ArtifactApp.Artifact.Artifact;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ArtifactServiceTest {

    @Mock
    private ArtifactRetrievalService artifactRetrievalService;

    @Mock
    private ArtifactCreationService artifactCreationService;

    @Mock
    private ArtifactUpdateService artifactUpdateService;

    @Mock
    private ArtifactDeletionService artifactDeletionService;

    @InjectMocks
    private ArtifactService artifactService;

    @Test
    public void testGetAllArtifacts() {
        // Arrange
        List<Artifact> artifacts = Arrays.asList(new Artifact(), new Artifact());
        when(artifactRetrievalService.getAllArtifacts()).thenReturn(artifacts);

        // Act
        List<Artifact> result = artifactService.getAllArtifacts();

        // Assert
        assertThat(result).isEqualTo(artifacts);
        verify(artifactRetrievalService, times(1)).getAllArtifacts();
    }

    @Test
    public void testAddNewArtifact() {
        // Arrange
        Artifact artifact = new Artifact();

        // Act
        artifactService.addNewArtifact(artifact);

        // Assert
        verify(artifactCreationService, times(1)).addNewArtifact(artifact);
    }

    @Test
    public void testUpdateArtifact() {
        // Arrange
        Long artifactId = 1L;
        String type = "Feather";
        String mainStat = "Crit Rate";

        // Act
        artifactService.updateArtifact(artifactId, type, mainStat);

        // Assert
        verify(artifactUpdateService, times(1)).updateArtifact(artifactId, type, mainStat);
    }

    @Test
    public void testDeleteArtifact() {
        // Arrange
        Long artifactId = 1L;

        // Act
        artifactService.deleteArtifact(artifactId);

        // Assert
        verify(artifactDeletionService, times(1)).deleteArtifact(artifactId);
    }
}
