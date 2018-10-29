import com.slowcampus.config.ApplicationConfig;
import com.slowcampus.dao.ReplyDaoImpl;
import com.slowcampus.dto.Reply;
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
public class ReplyDaoImplTest {
    // ReplyDaoImpl SQL 테스트를 위한 임시 Class 입니다.
    @Autowired
    ReplyDaoImpl replyDao;

    @Test
    public void replyDaoTest() throws Exception {
        List<Reply> replyList;

        replyList = replyDao.getList(20L, 0);
        for (Reply reply : replyList) {
            System.out.println(reply.getId() + " " + reply.getBoardId() + " "
                    + reply.getContent() + " " + reply.getIpAddr());
        }
    }

    @Test
    public void replyParentWriteDaoTest() throws Exception {
        Reply reply = new Reply();
        int count = 0;

        reply.setBoardId(25L);
        reply.setContent("Keyholder 적용 insert reply");
        reply.setUserNickname("집에가자1");
        reply.setParentNickname("집에가자1");
        reply.setIpAddr("192.168.0.100");
        reply.setRegdate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));

        count = replyDao.writeReply(reply);
        System.out.println(count);
    }

    @Test
    public void replyChildWriteDaoTest() {
        Reply reply = new Reply();
        int count = 0;

        reply.setBoardId(25L);
        reply.setContent("SqlParameter와 KeyHolder 도입 대댓글 달기");
        reply.setUserNickname("테스트16");
        reply.setParentNickname("집에가자1");
        reply.setGroupId(25L);
        reply.setDepth(1);
        reply.setIpAddr("192.168.0.11");
        reply.setRegdate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));

        count = replyDao.writeReply(reply);
        System.out.println(count);
    }

    @Test
    public void replyModify() {
        Reply reply = new Reply();
        int count = 0;

        reply.setId(21L);

        reply.setContent("JUnit에서 SQL 수정 테스트 입니다.");
        reply.setIpAddr("192.168.10.7");
        reply.setModdate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));

        count = replyDao.modifyReply(reply);
        System.out.println(count);
    }

    @Test
    public void replyDelete() {
        Long id = 20L;
        int count = 0;

        count = replyDao.deleteReply(id);
        System.out.println(count);
    }
}
