package xmlProjectSpringbootstarter.post;


import javax.jws.WebService;

@WebService
public interface PostSOAPService {

    Post create(Post smestaj);

}
