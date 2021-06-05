package postservice.postmodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepository postRepository;


    public List<Post> findAll(){
        List<Post> post = this.postRepository.findAll();
        return post;
    }

/*    Napravi find all pa nadji dati ID
 * 
    public Post findOne(String id){
    	Post post = this.postRepository.(id);
        return post;
    }
*/
    

    public Post create(Post post){
    	Post savedPost = this.postRepository.insert(post);
        return savedPost;
    }

//Ovo nek bude metoda za dodavanje komentara
    public Post update(Post post){
        /*Smestaj smestajUpdt = this.smestajRepository.findOne(smestaj.getId());

        smestajUpdt.setKapacitet(smestaj.getKapacitet());
        smestajUpdt.setOpis(smestaj.getOpis());
        smestajUpdt.setSlika((smestaj.getSlika()));*/

    	Post updatePost = this.postRepository.save(post);
        return updatePost;
    }


    public void delete(String id) {
        this.postRepository.deleteById(id);
    }

	public Post findOne(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
