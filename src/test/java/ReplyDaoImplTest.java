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
import java.util.ArrayList;
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
    public void replyWriteDaoTest() throws Exception {
        Reply reply = new Reply();
        int count = 0;

        reply.setBoardId(16L);
        reply.setContent("DAO INSERT 테스트 입니다.3");
        reply.setUserNickname("테스트9");
        reply.setParentNickname("테스트9");
        reply.setIpAddr("192.168.0.1");
        reply.setRegdate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));

//        reply.setBoardId(22L);
//        reply.setContent("자식 댓글 넣기 테스트");
//        reply.setUserNickname("테스트12");
//        reply.setParentNickname("테스트11");
//        reply.setGroupId(21L);
//        reply.setDepth(1);
//        reply.setIpAddr("192.168.0.10");
//        reply.setRegdate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));

        count = replyDao.writeReply(reply);
        System.out.println(count);
    }
}
