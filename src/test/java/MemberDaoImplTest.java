import com.slowcampus.config.ApplicationConfig;
import com.slowcampus.dao.MemberDao;
import com.slowcampus.dto.Authority;
import com.slowcampus.dto.Member;
import org.junit.Assert;
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
public class MemberDaoImplTest {
    @Autowired
    MemberDao memberDao;

    @Test
    public void signupMemberImplTest() throws Exception {
        Member member = new Member();
        int count = 0;

        member.setId("test" + System.currentTimeMillis());
        member.setPassword("pass" + System.currentTimeMillis());
        member.setNickname("nick" + System.currentTimeMillis());
        member.setEmail("aaa@" + System.currentTimeMillis() + ".com");
        member.setRegdate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        member.setModdate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));

        count = memberDao.signupMember(member);

        Assert.assertEquals(count, 1);
    }

    @Test
    public void loginTest(){
        Member member = new Member();
        member.setId("asdasd");
        member.setPassword("sana123");
        memberDao.loginMember(member);
    }

    @Test
    public void getAuthorityTest() {
        List<Authority> authorityList = new ArrayList<>();
        Member member = new Member();
        member.setId("abcde");
        authorityList = memberDao.getMemberAuthority(member);
        for (int i = 0; i < authorityList.size(); i++) {
            System.out.println(authorityList.get(i));
        }

    }
}
