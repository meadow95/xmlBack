package xmlProjectSpringbootstarter.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebService;

@Service
@WebService (targetNamespace="http://xmlProjectSpringbootstarter/", serviceName="PostService", endpointInterface="xmlProjectSpringbootstarter.post.PostSOAPService")
public class PostSOAPServiceImpl implements PostSOAPService {

    @Autowired
    PostDAO smestajDAO;

    @Override
    public Post create(Post smestaj) {
    	Post acc = smestajDAO.create(smestaj);
        System.out.println("OK!");
        return acc;
    }
}
