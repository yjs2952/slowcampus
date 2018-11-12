import com.slowcampus.config.ApplicationConfig;
import com.slowcampus.dao.CommentDaoImpl;
import com.slowcampus.dto.Comment;
import com.slowcampus.service.CommentService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class CommentDaoImplTest {
    // CommentDaoImpl SQL 테스트를 위한 임시 Class 입니다.
    @Autowired
    CommentDaoImpl commentDao;
    @Autowired
    CommentService commentService;

    @Test
    public void commentDaoTest(){
        List<Comment> commentList;

        commentList = commentDao.getCommentList(3L, 0);
        for (Comment comment : commentList) {
            System.out.println(comment.getId() + " " + comment.getBoardId() + " "
                    + comment.getContent() + " " + comment.getIpAddr());
        }
    }

    @Test
    public void commentParentWriteDaoTest() throws Exception {
        Comment comment = new Comment();
        comment.setBoardId(3L);
        comment.setContent("Reply 출력하기 테스트!7");
        comment.setUserNickname("아이러브김치");
        comment.setParentNickname("아이러브김치");
        comment.setIpAddr("192.168.0.136");
        Long result = commentService.writeComment(comment);
        System.out.println(result);
    }

    @Test
    public void commentChildWriteDaoTest() {
        Comment comment = new Comment();
        Long count =0l;

        comment.setBoardId(3L);
        comment.setContent("Comment 자식 댓글 테스트1");
        comment.setUserNickname("이명박");
        comment.setParentNickname("박근혜");
        comment.setGroupId(27L);
        comment.setDepth(1);
        comment.setIpAddr("192.168.0.11");
        comment.setRegdate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));

        count = commentDao.writeComment(comment);
        System.out.println(count);
    }

    @Test
    public void commentModify() {
        Comment comment = new Comment();
        int count = 0;

        comment.setId(28L);

        comment.setContent("JUnit에서 SQL 수정 테스트 입니다. 20181030");
        comment.setIpAddr("192.168.10.7");
        comment.setModdate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));

        count = commentDao.modifyComment(comment);
        System.out.println(count);
    }

    @Test
    public void commentDelete() {
        Long id = 27L;
        int count = 0;

        count = commentDao.deleteComment(id);
        System.out.println(count);
    }

    @Test
    public void commentCount() {
        Long boardId=172L;
        Long groupId=98L;

        Long count = commentDao.getCountOfRecommentList(boardId,groupId);
        System.out.println(count);

    }

    @Test
    public void recommentList() {
        Long boardId=220L;
        Long groupId=164L;

        List<Comment> list = commentDao.getRecommentList(boardId,groupId);
        for(Comment comment : list) {
            System.out.println(comment);
        }
    }
}
