package co.uk.sainsburys.sc.dis.configuration;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    Logger LOGGER = LoggerFactory.getLogger(KafkaProducerConfig.class);


    @Value(value = "${kafka.broker}")
    private String kafkaBroker;

    @Value(value = "${kafka.enable.ssl}")
    private boolean isSSLEnabled;

    @Autowired
    private Environment env;

    @Bean
    public ProducerFactory<String, Object> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBroker);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        if (isSSLEnabled) {
            enableSSL(configProps);
        }
        return new DefaultKafkaProducerFactory<>(configProps);
    }



    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }


    private void enableSSL(Map<String, Object> configProps) {
        configProps.put("security.protocol", env.getProperty("kafka.ssl.security.protocol", "SSL"));
        configProps.put("ssl.truststore.location", env.getProperty("kafka.ssl.truststore.location"));
        configProps.put("ssl.truststore.password", env.getProperty("kafka.ssl.truststore.password"));
        configProps.put("ssl.key.password", env.getProperty("kafka.ssl.key.password"));
        configProps.put("ssl.keystore.password", env.getProperty("kafka.ssl.keystore.password"));
        configProps.put("ssl.keystore.location", env.getProperty("kafka.ssl.keystore.location"));
        configProps.put("ssl.endpoint.identification.algorithm", env.getProperty("kafka.ssl.endpoint.identification.algorithm"));

    }


}