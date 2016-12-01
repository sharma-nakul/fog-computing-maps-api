package fog.maps.api.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nakulsharma on 22-11-2016.
 * Configures the Rest call to produce JSON output in proper format.
 */

@Configuration
public class RestConfig
{
    /**
     * Bean to make jackson automatically convert from
     * camelCase (java) to under_scores (json) in property names
     *
     * @return ObjectMapper that maps from Java camelCase to json under_score names
     */
    @Bean
    public ObjectMapper jacksonObjectMapper()
    {
        return new ObjectMapper().setPropertyNamingStrategy(propertyNamingStrategy());
    }

    @Bean
    public PropertyNamingStrategy propertyNamingStrategy()
    {
        return new UpperCaseUnderscoreStrategy();
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter jsonMessageConverter = new MappingJackson2HttpMessageConverter();
        jsonMessageConverter.setObjectMapper(jacksonObjectMapper());
        messageConverters.add(jsonMessageConverter);
        restTemplate.setMessageConverters(messageConverters);

        return restTemplate;
    }

    //todo: replace this and test it. Current method is adding extra line of code
  /*  @Bean
    public ObjectMapper jacksonObjectMapper() {
        return new ObjectMapper().setPropertyNamingStrategy(
                PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
    }*/
}