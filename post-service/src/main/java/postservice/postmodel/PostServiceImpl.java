package postservice.postmodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    	Post updatePost = this.postRepository.save(post);
        return updatePost;
    }


    public void delete(String id) {
        this.postRepository.deleteById(id);
    }

	public Optional<Post> findOne(String id) {
		// TODO Auto-generated method stub
		
		Optional<Post> post = this.postRepository.findById(id);
		return post;
	}

	public Post findPost(String identificationNumber) {
		
		List<Post> posts = this.postRepository.findAll();
		
		
		for(Post p : posts) {
			
			if(p.getIdentificationNum().equalsIgnoreCase(identificationNumber)) {
				
				return p;
			}			
			
		}
		
		return null;
	}

}
