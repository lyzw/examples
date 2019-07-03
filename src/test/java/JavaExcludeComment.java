import java.io.*;

public class JavaExcludeComment {

    /**/

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("D:\\workspace5\\openjdk\\jdk\\src\\share\\classes\\java\\util\\List.java")));
        String line = "";
        while ((line = bufferedReader.readLine()) != null) {
            if (line.contains("*") || line.contains("//") || line.trim().equals("")) {

            } else {
                System.out.println(line);
            }


        }
    }
}
