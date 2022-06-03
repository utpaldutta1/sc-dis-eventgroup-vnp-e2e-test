package co.uk.sainsburys.sc.dis.step;

import co.uk.sainsburys.sc.dis.util.ResourceUtil;
import io.cucumber.java.en.Given;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.Date;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StepDefinitions {

    private final Logger LOGGER = LoggerFactory.getLogger(StepDefinitions.class);


    @Autowired
    ResourceUtil resourceUtil;


    @Given("Start the execution")
    public void setExecutionTime() {
        long currentMilliSec = Instant.now().toEpochMilli();
        resourceUtil.setExecutionStartTime(currentMilliSec);
        Date date = new Date(currentMilliSec);
        LOGGER.info("current Date: {}", date);
        LOGGER.info("executionStartTime: {}", resourceUtil.getExecutionStartTime());
    }


}




