package com.genshin.ArtifactApp.Artifact.Service;

import com.genshin.ArtifactApp.Artifact.Repository.ArtifactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ArtifactRetrievalServiceTest {

    @Mock
    private ArtifactRepository artifactRepository;
    private ArtifactRetrievalService underTest;

    @BeforeEach
    void setUp(){
        underTest = new ArtifactRetrievalService(artifactRepository);
    }

    @Test
    void canGetAllArtifacts() {
        underTest.getAllArtifacts();

        verify(artifactRepository).findAll();
    }
}