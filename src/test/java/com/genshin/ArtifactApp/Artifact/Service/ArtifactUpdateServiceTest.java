package com.genshin.ArtifactApp.Artifact.Service;

import com.genshin.ArtifactApp.Artifact.Repository.ArtifactRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ArtifactUpdateServiceTest {

    @Mock
    private ArtifactRepository artifactRepository;
    private ArtifactUpdateService underTest;

    public ArtifactUpdateServiceTest(ArtifactRepository artifactRepository) {
        this.artifactRepository = artifactRepository;
    }

}