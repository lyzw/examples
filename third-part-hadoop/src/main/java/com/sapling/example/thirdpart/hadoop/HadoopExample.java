package com.sapling.example.thirdpart.hadoop;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

/**
 * @author weizhou
 * @version v1.0
 * @date 2019/3/19
 * @since v1.0
 */
public class HadoopExample {

    static String webhdfsUrl = "192.168.31.172:50070";
    static String hdfsUrl = "hdfs://192.168.31.172:8020";


    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();

        //这里指定使用的是 hdfs文件系统
        conf.set("fs.defaultFS", webhdfsUrl);

        //通过这种方式设置java客户端身份
        System.setProperty("HADOOP_USER_NAME", "root");
        FileSystem fs = FileSystem.get(conf);
        //或者使用下面的方式设置客户端身份
        //FileSystem fs = FileSystem.get(new URI("hdfs://master:9000"),conf,"root");

        // fs.create(new Path("/helloByJava")); //创建一个目录

        //文件下载到本地 如果出现0644错误或找不到winutils.exe,则需要设置windows环境和相关文件.
        //fs.copyToLocalFile(new Path("/zookeeper.out"), new Path("D:\\test\\examplehdfs"));

        Boolean flag = fs.exists(new Path("/xinhong/2.0/DATASET_STORE/adadadad"));
        System.out.println(flag);
        fs.delete(new Path("/xinhong/2.0/DATASET_STORE/adadadad"),true);
//        //使用Stream的形式操作HDFS，这是更底层的方式
//        FSDataOutputStream outputStream = fs.create(new Path("/2.txt"), true); //输出流到HDFS
//        FileInputStream inputStream = new FileInputStream("D:/test/examplehdfs/1.txt"); //从本地输入流。
//        IOUtils.copy(inputStream, outputStream); //完成从本地上传文件到hdfs

        fs.close();
    }
    
}
