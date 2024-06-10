package example.com.jms;

/*
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;

import example.com.entities.Todo;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;


public class JmsSender {
    private static final Logger LOG = LoggerFactory.getLogger(JmsSender.class);

    @Value("${jms.queue-name}")
    private String queueName;

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendOnQueue(Todo todo) {
        try {
            JAXBContext contextObj = JAXBContext.newInstance(Todo.class);
            Marshaller marshaller = contextObj.createMarshaller();

            Todo req = new Todo();

            StringWriter sw = new StringWriter();
            marshaller.marshal(req, sw);

            jmsTemplate.convertAndSend(queueName, sw.toString());
            LOG.info("Message sent: " + sw);
        } catch (Exception e) {
            LOG.error("JMS Sending message on Queue error", e);
        }
    }
}
*/
