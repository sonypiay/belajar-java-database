package programmer.zaman.now;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import programmer.zaman.now.entity.Comment;
import programmer.zaman.now.repository.CommentRepository;
import programmer.zaman.now.repository.CommentRepositoryImpl;

import java.util.List;

public class RepositoryTest {

    private CommentRepository commentRepository;

    @BeforeEach
    void setUp() {
        commentRepository = new CommentRepositoryImpl();
    }

    @Test
    void testInsert() {
        Comment comment = new Comment("sony@eannovate.com", "ini comment 3");
        commentRepository.insert(comment);

        Assertions.assertNotNull(comment.getId());
        System.out.println(comment.getId());
    }

    @Test
    void testById() {
        Comment comment = commentRepository.findById(3);

        Assertions.assertNotNull(comment);
        System.out.println(comment.getId());
        System.out.println(comment.getComment());
        System.out.println(comment.getEmail());

        Comment notFound = commentRepository.findById(100);
        Assertions.assertNull(notFound);
    }

    @Test
    void testFindAll() {
        List<Comment> comment = commentRepository.findAll();
        System.out.println(comment.size());
    }

    @Test
    void testFindByEmail() {
        List<Comment> comment = commentRepository.findByEmail("sony@eannovate.com");
        System.out.println(comment.size());
    }
}
