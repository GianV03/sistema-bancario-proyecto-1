package com.example.transactions_api.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

public class KafkaTopicConfig {

    @Bean
    public NewTopic generateTopic(){
        Map<String, String> configurations = new HashMap<>();
        configurations.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE);
        configurations.put(TopicConfig.RETENTION_BYTES_CONFIG, "8400000");
        configurations.put(TopicConfig.SEGMENT_BYTES_CONFIG, "1000012"); // Tamaño máximo de mensaje

        return TopicBuilder.name("cliente")
                .partitions(2)
                .replicas(2)
                .configs(configurations)
                .build();

    }
}
