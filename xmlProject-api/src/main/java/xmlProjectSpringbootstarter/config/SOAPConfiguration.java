
package xmlProjectSpringbootstarter.config;

import org.springframework.context.ApplicationContext;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
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
public class SOAPConfiguration extends WsConfigurerAdapter {


    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext context){
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(context);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/soapWs/*");
    }

    @Bean
    public XsdSchema smestajSchema(){
        return new SimpleXsdSchema(new ClassPathResource("post.xsd"));
    }

    @Bean("post")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema smestajSchema){

        DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
        definition.setSchema(smestajSchema);
        definition.setLocationUri("/soapWs");
        definition.setTargetNamespace("post");
        definition.setPortTypeName("PostServicePort");
        definition.setServiceName("PostService");

        return definition;
    }
}
