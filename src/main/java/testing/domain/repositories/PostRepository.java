package testing.domain.repositories;

import org.springframework.data.repository.ListCrudRepository;
import testing.domain.models.Post;

import java.util.Optional;

public interface PostRepository extends ListCrudRepository<Post,Integer> {

    Optional<Post> findByTitle(String title);

}
