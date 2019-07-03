import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * @author weizhou
 * @version v1.0
 * @date 2018/12/27
 * @since v1.0
 */
public class PropertiesClean {

    public static void main(String[] args) throws IOException {
        String fileName = "/Users/weizhou/poc3/manager/restapi/config/itoa-streaming-dataset+default+application.properties";
        Properties properties = new Properties();
        properties.load(new FileInputStream(new File(fileName)));
        Set<String> names = properties.stringPropertyNames();
        TreeSet<String> sortedNames = new TreeSet<>(names);
        System.out.println(sortedNames);
        sortedNames.stream().forEach(it -> System.out.println(it + "=" + properties.get(it)));
    }
}
