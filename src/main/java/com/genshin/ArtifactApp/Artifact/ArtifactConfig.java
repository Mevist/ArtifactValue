package com.genshin.ArtifactApp.Artifact;

import com.genshin.ArtifactApp.Artifact.Repository.ArtifactRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ArtifactConfig {
    @Bean
    CommandLineRunner commandLineRunner(
            ArtifactRepository repository) {
        return args -> {
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

            repository.saveAll(
                    List.of(defaultFeather, defaultGoblet)
            );
        };
    }
}
