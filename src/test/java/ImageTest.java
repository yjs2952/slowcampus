import com.slowcampus.config.ApplicationConfig;
import com.slowcampus.dao.ImageDaoImpl;
import com.slowcampus.dto.Image;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class ImageTest {
    @Autowired
    ImageDaoImpl imageDao;

    @Test
    public void getImageList() {
        List<Image> imageList = imageDao.getImageList(3L);
        System.out.println(imageList.size());
    }

    @Test
    public void addImage() {
        Image image = new Image();
        String name = "jeongyeon.jpg";
        UUID uid = UUID.randomUUID();
        String savedName = uid.toString() + "_" + name;

        image.setBoardId(3L);
        image.setOriginalName(name);
        image.setSaveName("9b5d33f8-b5e7-4228-835a-a4341571d752_jeongyeon");
        image.setPath("/2018_11_01/9b5d33f8-b5e7-4228-835a-a4341571d752_jeongyeon.jpg");
        image.setSize(7777L);
        image.setType("jpg");
        image.setRegDate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));

        imageDao.uploadImage(image);


    }

    @Test
    public void getImage() {

        Image image = imageDao.getImage(5L);

        System.out.println(image.toString());
//        System.out.println(image.getOriginalName());
//        System.out.println(image.getSavedName());
//        System.out.println(image.getPath());
//        System.out.println(image.getSize());
//        System.out.println(image.getType());
//        System.out.println(image.getBoardId());
//        System.out.println(image.getRegDate());



    }
}
