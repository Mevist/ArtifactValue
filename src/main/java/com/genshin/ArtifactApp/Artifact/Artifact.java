package com.genshin.ArtifactApp.Artifact;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table
public class Artifact {
    @Id
    @SequenceGenerator(
            name = "artifact_sequence",
            sequenceName = "artifact_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "artifact_sequence"
    )
    private Long id;
    private String type;
    private String mainStat;
    // Add a mainstat value
    @ElementCollection
    @CollectionTable(name = "ARTIFACT_SUB_STATS", joinColumns = @JoinColumn(name = "artifact_id"))
    private List<ArtifactSubStat> subStats;

    public Artifact() {
    }

    public Artifact(Long id, String type, String mainStat, List<ArtifactSubStat> subStats) {
        this.id = id;
        this.type = type;
        this.mainStat = mainStat;
        this.subStats = subStats;
    }

    public Artifact(String type, String mainStat, List<ArtifactSubStat> subStats) {
        this.type = type;
        this.mainStat = mainStat;
        this.subStats = subStats;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMainStat() {
        return this.mainStat;
    }

    public void setMainStat(String mainStat) {
        this.mainStat = mainStat;
    }

    public List<ArtifactSubStat> getsubStats() {
        return subStats;
    }

    public void setSubStats(List<ArtifactSubStat> subStats) {
        this.subStats = subStats;
    }

    @Override
    public String toString() {
        return "Artifact{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", MainStat='" + this.mainStat + '\'' +
                ", SubStats=" + this.subStats +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artifact artifact = (Artifact) o;
        return Objects.equals(id, artifact.id) && Objects.equals(type, artifact.type) && Objects.equals(mainStat, artifact.mainStat) && Objects.equals(subStats, artifact.subStats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, mainStat, subStats);
    }

    @Embeddable
    public static class ArtifactSubStat {
        private String stat;
        private float statValue;

        public ArtifactSubStat() {
        }

        public ArtifactSubStat(String stat, float value) {
            this.stat = stat;
            this.statValue = value;
        }

        public String getStat() {
            return stat;
        }

        public void setStat(String stat) {
            this.stat = stat;
        }

        public float getValue() {
            return statValue;
        }

        public void setValue(float value) {
            this.statValue = value;
        }
    }
}
