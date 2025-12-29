package testing.infra.dtos;

import testing.domain.models.Post;

import java.util.List;

public record Posts(List<Post> posts) {
}
