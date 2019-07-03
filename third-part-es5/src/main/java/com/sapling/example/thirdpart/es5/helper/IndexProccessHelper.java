package com.sapling.example.thirdpart.es5.helper;

import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.elasticsearch.ElasticsearchTimeoutException;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sapling.common.tools.common.StringUtil;
import com.sapling.example.thirdpart.es5.exception.ElasticIndexNotExistsException;
import com.sapling.example.thirdpart.es5.exception.ElasticTimeoutException;

/**
 * elastic index process helper
 *
 * @author weizhou
 * @version v1.0
 * @date 2019/2/14
 * @since v1.0
 */
public class IndexProccessHelper {


    private static Logger logger = LoggerFactory.getLogger(IndexProccessHelper.class);

    private static final long DEFAULT_GET_TIMEOUT = 1000L;

    public static void createIndex() {

    }

    /**
     * 判断给定的index名称在ES中是否存在
     *
     * @param transportClient 客户端
     * @param indexName       索引名称
     * @param timeout         超时时间
     * @return
     */
    public static boolean isExists(TransportClient transportClient, String indexName, long timeout) {
        IndicesAdminClient client = transportClient.admin().indices();
        return isExists(client, indexName, timeout);
    }

    public static boolean isExists(IndicesAdminClient client, String indexName, long timeout) {
        if (StringUtil.isEmpty(indexName)) {
            throw new IllegalArgumentException();
        }
        try {
            IndicesExistsRequest indicesExistsRequest = new IndicesExistsRequest(indexName);
            IndicesExistsResponse response = client.exists(indicesExistsRequest).actionGet(timeout, TimeUnit.MICROSECONDS);
            return response.isExists();
        } catch (ElasticsearchTimeoutException te) {
            throw new ElasticTimeoutException();
        }
    }

    /**
     * 更新索引
     *
     * @param transportClient 客户端
     * @param indexName       索引名称
     * @param properties      属性
     */
    public static void updateIndex(TransportClient transportClient, String indexName, Properties properties) {
//        if (StringUtil.isEmpty(indexName)) {
//            throw new IllegalArgumentException();
//        }
//        IndicesAdminClient client = transportClient.admin().indices();
//        if (!isExists(client, indexName, DEFAULT_GET_TIMEOUT)) {
//            throw new ElasticIndexNotExistsException();
//        }
//        UpdateRequest updateRequest = new UpdateRequest();
//        client.updateSettings(properties);

    }

    public static void main(String[] args) {
        String regex = "(\\S*\\\\)?(?<ip>\\S*):(?<port>\\d*).*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher("192.179.23.21:9001FS");
        System.out.println(matcher.matches());
        System.out.println(matcher.group("ip"));
        System.out.println(matcher.group("port"));
    }
}
