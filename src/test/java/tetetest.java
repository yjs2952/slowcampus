import java.io.File;

public class tetetest {
    public static void main(String[] args) {
        File file = new File("/tmp/a/b/c/d/");
        if(!file.exists()) {
            file.mkdirs();
            System.out.println("생성.");
        } else{
            System.out.println("이미 존재!");
        }

//        File[] files = file.listFiles();
//        files[0].delete();
//        file.delete();

//        File file = new File("/tmp/a/b/c/d/");

        File file2 = new File("/tmp/a/b/c/d/");
        file2.delete();








    }
}