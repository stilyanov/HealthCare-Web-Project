package bg.softuni.healthcare.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@Configuration
public class RestConfig {

    @Bean
    public RestClient restClient() {
        return RestClient.create();
    }

    @Bean("appointmentsRestClient")
    public RestClient doctorsRestClient(AppointmentsApiConfig appointmentsApiConfig) {
        return RestClient
                .builder()
                .baseUrl(appointmentsApiConfig.getBaseUrl())
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

}
