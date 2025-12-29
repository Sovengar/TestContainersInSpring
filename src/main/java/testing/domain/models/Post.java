package testing.domain.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Post")
public class Post {
        @Id
        /*
        @SequenceGenerator(
                name = "post_sequence",
                sequenceName = "post_sequence",
                allocationSize = 1
        )
        @GeneratedValue(
                generator = "post_sequence",
                strategy = GenerationType.SEQUENCE)
         */
        private Long id;
        private Integer userId;
        @NotEmpty
        private String title;
        @NotEmpty
        private String body;
        @Version
        private Integer version;

        @JsonProperty("id")
        public Post withId(Long id) {
                this.id = id;
                return this;
        }

        @JsonProperty("userId")
        public Post withUserId(Integer userId) {
                this.userId = userId;
                return this;
        }

        @JsonProperty("title")
        public Post withTitle(String title) {
                this.title = title;
                return this;
        }

        @JsonProperty("body")
        public Post withBody(String body) {
                this.body = body;
                return this;
        }

        @JsonProperty("version")
        public Post withVersion(Integer version) {
                this.version = version;
                return this;
        }

        @JsonProperty("id")
        public Long id() {
                return id;
        }

        @JsonProperty("userId")
        public Integer userId() {
                return userId;
        }

        @JsonProperty("title")
        public String title() {
                return title;
        }

        @JsonProperty("body")
        public String body() {
                return body;
        }

        @JsonProperty("version")
        public Integer version() {
                return version;
        }
}
