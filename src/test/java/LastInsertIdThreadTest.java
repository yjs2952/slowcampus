import com.slowcampus.dao.BoardDaoImpl;
import com.slowcampus.dto.Board;
import org.springframework.beans.factory.annotation.Autowired;

public class LastInsertIdThreadTest extends Thread {

    @Autowired
    BoardDaoImpl boardDao;

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {

            Board board = new Board();
            board.setTitle("sss");
            board.setUserId("super");
            board.setNickname("blackpink");
            board.setCategory(2);
            board.setDepth(0);
            board.setDepthOrder(0);
            board.setIpAddr("");
            board.setReadCount(0);
            board.setParentBoardId(1L);
            board.setRootBoardId(1L);
            Long id = boardDao.writeBoard(board);
        }
    }
}
