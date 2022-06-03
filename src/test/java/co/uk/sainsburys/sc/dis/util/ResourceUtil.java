package co.uk.sainsburys.sc.dis.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;

@Service
public class ResourceUtil {

    private long executionStartTime;

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceUtil.class);

    public ResourceUtil() {
    }

    @PostConstruct
    public void initExecutionStartTime() {
        long currentMilliSec = Instant.now().toEpochMilli();
        Date date = new Date(currentMilliSec);
        this.setExecutionStartTime(currentMilliSec);
        LOGGER.info("current Date: {}", date);
        LOGGER.info("Setting executionStartTime: {}", this.getExecutionStartTime());


    }

    /**
     * @param pathToFile path to file need to convert as string value
     * @return file data as string
     * <p>
     * return null is case of any exception
     */
    public static String readFileAsString(String pathToFile) {

        String fileAsString = null;
        try {
            fileAsString = StreamUtils.copyToString(new ClassPathResource(pathToFile).getInputStream(), StandardCharsets.UTF_8);

        } catch (IOException ioException) {
            LOGGER.error(ioException.getMessage());
        }
        return fileAsString;
    }


    public long getExecutionStartTime() {
        return executionStartTime;
    }

    public void setExecutionStartTime(long executionStartTime) {
        this.executionStartTime = executionStartTime;
    }
}
