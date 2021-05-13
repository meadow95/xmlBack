package xmlProjectSpringbootstarter.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepository smestajRepository;


    @Override
    public List<Post> findAll(){
        List<Post> smestaj = this.smestajRepository.findAll();
        return smestaj;
    }

    @Override
    public Post findOne(String id){
    	Post smestaj = this.smestajRepository.findOne(id);
        return smestaj;
    }

    @Override
    public Post create(Post smestaj){
    	Post savedSmestaj = this.smestajRepository.insert(smestaj);
        return savedSmestaj;
    }

    @Override
    public Post update(Post smestaj){
        /*Smestaj smestajUpdt = this.smestajRepository.findOne(smestaj.getId());

        smestajUpdt.setKapacitet(smestaj.getKapacitet());
        smestajUpdt.setOpis(smestaj.getOpis());
        smestajUpdt.setSlika((smestaj.getSlika()));*/

    	Post updateSmestaj = this.smestajRepository.save(smestaj);
        return updateSmestaj;
    }


    @Override
    public void delete(String id) {
        this.smestajRepository.delete(id);
    }

}
