package com.srini.kafka.streams;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * The type Config.
 */
@Configuration
@RequiredArgsConstructor
public class Config {


    private final StreamConfig streamConfig ;

    /**
     * Stream props properties.
     *
     * @return the properties
     */
    @Bean
    public Properties adminProps(){
        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, streamConfig.getBootstrapServers());
        props.put(AdminClientConfig.SECURITY_PROTOCOL_CONFIG, streamConfig.getSecurityProtocol());
        props.put(SaslConfigs.SASL_MECHANISM, streamConfig.getSaslMechanism());
        props.put(SaslConfigs.SASL_JAAS_CONFIG, streamConfig.getSaslJaasConfig() );
        props.put("schema.registry.url", streamConfig.getSchemaRegistryUrl());
        props.put("basic.auth.credentials.source", streamConfig.getSchemaAuth());
        props.put("basic.auth.user.info", streamConfig.getSchemaAuthInfo());
        return props ;
    }

    /**
     * Admin client admin client.
     *
     * @param adminProps the admin props
     * @return the admin client
     */
    @Bean
    public AdminClient adminClient(Properties adminProps){
        return AdminClient.create(adminProps) ;
    }


}
