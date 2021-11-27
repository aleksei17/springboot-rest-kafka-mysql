package ee.aleksei.gvozdev.kafka;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Validated
@ConfigurationProperties(prefix = "kafka.wages-topic")
public class WagesTopicPublisherProperties {

    @NotBlank
    private String name;

    @NotBlank
    private String bootstrapAddress;

    @NotNull
    @Positive
    private Integer partitions;

    @NotNull
    @Positive
    private Short replicationFactor;

    public String getName() {
        return name;
    }

    public WagesTopicPublisherProperties setName(String name) {
        this.name = name;
        return this;
    }

    public String getBootstrapAddress() {
        return bootstrapAddress;
    }

    public WagesTopicPublisherProperties setBootstrapAddress(String bootstrapAddress) {
        this.bootstrapAddress = bootstrapAddress;
        return this;
    }

    public Integer getPartitions() {
        return partitions;
    }

    public WagesTopicPublisherProperties setPartitions(Integer partitions) {
        this.partitions = partitions;
        return this;
    }

    public Short getReplicationFactor() {
        return replicationFactor;
    }

    public WagesTopicPublisherProperties setReplicationFactor(Short replicationFactor) {
        this.replicationFactor = replicationFactor;
        return this;
    }
}
