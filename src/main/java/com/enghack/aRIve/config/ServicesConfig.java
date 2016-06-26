package com.enghack.aRIve.config;

/**
 * Created by 37264 on 5/17/16.
 */
import com.enghack.aRIve.service.TtcService;
import com.enghack.aRIve.service.XMLParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//import ca.sean.kim.spring.dao.StandingDao;


@Configuration
@ComponentScan(basePackages = "com.enghack.aRIve.*")
public class ServicesConfig {


    @Bean
    public TtcService ttcService() {
        return new TtcService();
    }

    @Bean
    public XMLParser xmlParser() {
        return new XMLParser();
    }

}
