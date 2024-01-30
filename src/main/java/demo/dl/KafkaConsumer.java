package demo.dl;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import demo.util.Constants;

@Service
public class KafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    @Autowired
    DLIssueService dlIssueService;

    @KafkaListener(topics = Constants.TOPIC_NAME,
                    groupId = Constants.GROUP_ID)
    public void consume(String message) throws IOException, ParseException, InterruptedException, java.text.ParseException{
        LOGGER.info(String.format("\n\n\nDL Issue Service Consuming Event -> \n %s\n\n\n", message));

        dlIssueService.generateDL(message);

    }
}