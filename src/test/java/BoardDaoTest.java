import com.slowcampus.config.ApplicationConfig;
import com.slowcampus.dao.BoardDaoImpl;
import com.slowcampus.dto.Board;
import com.slowcampus.service.BoardServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class BoardDaoTest {

    @Autowired
    BoardDaoImpl boardDao;

    @Test
    public void init() {
        System.out.println("init");
    }

    /*@Test
    public void getBoardListTest() throws Exception{
        List<Board> list = boardDao.getList(2);

        for (Board board : list) {
            System.out.println(board.toString());
        }
    }*/

    /*@Test
    public void getBoard() throws Exception {
        Board board = boardDao.getBoard(1L);
        System.out.println(board.toString());
    }*/

    @Test
    public void addBoard() {

        Board board = new Board();
        board.setTitle("왜 안되 ㅠㅠㅠㅠ");
        board.setUserId("super");
        board.setNickname("blackpink");
        board.setIpAddr("000.000.000.000");
        boardDao.writeBoard(board);


        //System.out.println(board.toString());

        //board.setContent("aaaaaaaaaqqqqqqqqqqqq");
        //boardService.writeBoard(board);
    }

    /*@Test
    public void updateTest(){
        Board board = new Board();
        board.setId(33L);
        board.setTitle("왜 안되 ㅠㅠㅠㅠ");
        board.setIpAddr("000.000.000.000");
        boardDao.modifyBoard(board);

        board.setContent("어? 된다??????????????");
        boardDao.modifyBoardContent(board);
    }*/

    /*@Test
    public void deleteTest(){
        boardDao.deleteBoard(33L);
    }*/
}
