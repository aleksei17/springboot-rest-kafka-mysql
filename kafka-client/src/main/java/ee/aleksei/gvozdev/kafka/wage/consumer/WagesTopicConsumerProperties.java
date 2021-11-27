package ee.aleksei.gvozdev.kafka.wage.consumer;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Validated
@ConfigurationProperties(prefix = "kafka.wages-topic")
public class WagesTopicConsumerProperties {

    @NotBlank
    private String name;

    @NotBlank
    private String bootstrapAddress;

    @NotNull
    @Positive
    private Integer partitions;

    @NotNull
    @Positive
    private Integer replicationFactor;

    @NotBlank
    private String groupId;

    public String getName() {
        return name;
    }

    public WagesTopicConsumerProperties setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getPartitions() {
        return partitions;
    }

    public WagesTopicConsumerProperties setPartitions(Integer partitions) {
        this.partitions = partitions;
        return this;
    }

    public Integer getReplicationFactor() {
        return replicationFactor;
    }

    public WagesTopicConsumerProperties setReplicationFactor(Integer replicationFactor) {
        this.replicationFactor = replicationFactor;
        return this;
    }

    public String getGroupId() {
        return groupId;
    }

    public WagesTopicConsumerProperties setGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public String getBootstrapAddress() {
        return bootstrapAddress;
    }

    public WagesTopicConsumerProperties setBootstrapAddress(String bootstrapAddress) {
        this.bootstrapAddress = bootstrapAddress;
        return this;
    }
}
