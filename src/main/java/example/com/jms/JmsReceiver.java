package example.com.jms;

/*``
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;


public class JmsReceiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(JmsReceiver.class);
    private static final Logger msgLogger = LoggerFactory.getLogger("messagelogger");
    private JAXBContext contextObj;
    private Unmarshaller unmarshaller;
    
    public JmsReceiver() {
        try {
            //contextObj = JAXBContext.newInstance(ChatRequest.class);
            unmarshaller = contextObj.createUnmarshaller();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    
    @JmsListener(destination = "${jms.queue-name}")
    public void receiveFromQueue(String message) throws JMSException {
        LOGGER.info("Received queue message: '{}'", message);

        try {
            if (message.startsWith("<?xml version")){
                StringReader reader = new StringReader(message);
                //ChatRequest cr = (ChatRequest) unmarshaller.unmarshal(reader);

                //msgLogger.info("JMS | " + cr.getSender() + " | " + cr.getMessage());

                //ChatMessageDTO cm = new ChatMessageDTO(cr.getMessage(), cr.getSender());
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
*/

