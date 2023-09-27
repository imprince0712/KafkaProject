package com.kafkaproject;

import com.kafkaproject.entity.WikimediaData;
import com.kafkaproject.repository.WikimediaDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaDatabaseConsumer {
    private final static Logger LOGGER= LoggerFactory.getLogger(KafkaDatabaseConsumer.class);

    @Autowired
    private WikimediaDataRepository wikimediaDataRepository;
    @KafkaListener( topics ="wikimedia_recentchange", groupId = "myGroup")
    private void consume(String eventMessage){
        LOGGER.info(String.format("Event message received -> %s", eventMessage));
        WikimediaData wikimediaData=new WikimediaData();
        wikimediaData.setWikiEventData(eventMessage);
        wikimediaDataRepository.save(wikimediaData);
    }
}
