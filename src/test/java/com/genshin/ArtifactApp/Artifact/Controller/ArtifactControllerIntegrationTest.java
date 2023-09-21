package com.genshin.ArtifactApp.Artifact.Controller;

import com.genshin.ArtifactApp.Artifact.Artifact;
import com.genshin.ArtifactApp.Artifact.Repository.ArtifactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ArtifactControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ArtifactRepository artifactRepository;

    @BeforeEach
    public void setup() {
        Artifact.ArtifactSubStat atkStat = new Artifact.ArtifactSubStat("Atk", 30.0f);
        Artifact defaultFeather = new Artifact(
                "Feather",
                "Crit DMG",
                List.of(atkStat)
        );
        Artifact defaultGoblet = new Artifact(
                "Goblet",
                "Crit Rate",
                List.of(atkStat)
        );
        artifactRepository.saveAll(List.of(defaultFeather, defaultGoblet));
    }

    @Test
    public void getAllArtifacts() throws Exception {
        mockMvc.perform(get("/api/v1/artifact"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(equalTo(2))));
        // Add more expectations here, such as content, JSON path etc.
    }

    // Add more tests for POST, PUT, DELETE operations.
}
