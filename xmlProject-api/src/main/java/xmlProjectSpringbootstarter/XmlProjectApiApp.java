package xmlProjectSpringbootstarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import xmlProjectSpringbootstarter.post.Post;
import xmlProjectSpringbootstarter.post.PostSOAPService;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

@SpringBootApplication
public class XmlProjectApiApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(XmlProjectApiApp.class, args);
	}

}
