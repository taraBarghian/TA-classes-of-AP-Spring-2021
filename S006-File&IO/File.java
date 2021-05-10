import java.io.*;
import java.net.Socket;
import java.util.Scanner;
class File {
    static File myFile = new File("E:\\io\\myFile.txt");

    public static void makeFile() throws IOException {

        System.out.println(myFile.exists());
        System.out.println(myFile.isFile());
        System.out.println(myFile.canRead());
        System.out.println(myFile.canWrite());
        //myFile.mkdirs()
        if (!myFile.exists()) {

            myFile.getParentFile().mkdirs();
            //myFile.mkdirs();
            System.out.println(myFile.createNewFile());
        }

    }

    public static void main(String[] args) throws IOException {
        file.makeFile();
        file.myFile.renameTo(new File("E:\\io\\moved.txt"));
    }

}
class Streams {
    public static void main(String[] args) throws IOException {
       a();
        b();
        c();
        d();
        sc();
    }
    public static void  a() throws IOException {
        FileInputStream fs = new FileInputStream("E:\\io\\text.txt");
        int i;
        while ((i = fs.read()) != -1) {
             if (((char) i) == '\r')
                System.out.print(" !!!! ");
             else
                System.out.print((char) i);
        }
    }
    public static void  b() throws IOException {
        FileInputStream fs = new FileInputStream("E:\\io\\text.txt");
        int i;
        byte[] bytes = new byte[1024];
        while ((i = fs.read(bytes)) != -1) {

            for (int x=0;x<i;x++) {
                System.out.print((char) bytes[x]);
            }

        }
    }
    public static void  c() throws IOException {
        FileOutputStream fs = new FileOutputStream("E:\\io\\text.txt");
        String data = "it\'s my data" ;
        fs.write(data.getBytes());
        fs.flush();

    }
    public static void  d() throws IOException {

        DataInputStream ds = new DataInputStream(new FileInputStream("E:\\io\\text.txt"));
        //and ...
    }
    
    public static void sc() throws IOException{
        Scanner scanner = new Scanner(new File("E:\\io\\text.txt"));
        while (scanner.hasNext()){
            System.out.print(scanner.next());
        }
    }
}
