package co.uk.sainsburys.sc.dis;

import co.uk.sainsburys.sc.dis.util.ResourceUtil;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features",
        plugin = {"pretty", "html:target/cucumber", "json:target/cucumber.json"})
public class ApplicationTests {

    @Bean(name = "resourceUtil")
    public ResourceUtil getResourceUtil(){
        return new ResourceUtil();
    }

}
