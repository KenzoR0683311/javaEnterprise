package example.com.soap.server;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;



@EnableWs
@Configuration
public class TodoWebServiceConfig extends WsConfigurerAdapter {
  @Bean("todoWebService")
  // EntryPoint generating on the fly a response to a WSDL request.
  // WSDL to be retrieved by the following URL http://localhost:8180/chat/soap/chatService.wsdl
  public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema chatSchema) {
    DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
    definition.setTargetNamespace("http://ucll.be/java/com/example/todo");
    definition.setSchema(chatSchema);
    definition.setPortTypeName("TodoPort");
    definition.setLocationUri("/soap/addTodoRequest"); // URL to call SOAP upon
    return definition;
  }

  @Bean
  public XsdSchema ChatSchema() {
    return new SimpleXsdSchema(new ClassPathResource("todo.xsd"));
  }

  @Bean
  // Define Base context for ALL Soap services of the Web Application
  // /soap/* => http://localhost:8180/chat/soap/*
  public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
    MessageDispatcherServlet servlet = new MessageDispatcherServlet();
    servlet.setApplicationContext(applicationContext);
    servlet.setTransformWsdlLocations(true); // Generate WSDL on the fly, true
    return new ServletRegistrationBean(servlet, "/soap/*");
  }
}

