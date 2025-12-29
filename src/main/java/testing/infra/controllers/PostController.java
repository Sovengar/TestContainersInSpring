package testing.infra.controllers;


import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import testing.domain.models.Post;
import testing.domain.exceptions.PostNotFoundException;
import testing.domain.repositories.PostRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
class PostController {

    private static final Logger log = LoggerFactory.getLogger(PostController.class);
    private final PostRepository repository;

    public PostController(PostRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    List<Post> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    Optional<Post> findById(@PathVariable Integer id) {
        return Optional.ofNullable(repository.findById(id).orElseThrow(PostNotFoundException::new));
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    Post save(@RequestBody @Valid Post post) {
        return repository.save(post);
    }

    @PutMapping("/{id}")
    Post update(@PathVariable Integer id, @RequestBody Post post) {
        Optional<Post> existing = repository.findById(id);
        if(existing.isPresent()) {
            Post postFound = existing.get();
            Post updatedPost = new Post()
                    .withId(postFound.id())
                    .withUserId(postFound.userId())
                    .withTitle(postFound.title())
                    .withBody(postFound.body())
                    .withVersion(postFound.version());
            return repository.save(updatedPost);
        } else {
            throw new PostNotFoundException();
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        repository.deleteById(id);
    }

}
