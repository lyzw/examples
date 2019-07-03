package com.sapling.example.thirdpart.es5;

import java.io.IOException;
import java.io.StringReader;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sapling.common.tools.common.StringUtil;

/**
 * @author weizhou
 * @version v1.0
 * @date 2019/1/28
 * @since v1.0
 */
public class ESTools {

    private static Logger logger = LoggerFactory.getLogger(ESTools.class);

    private static final String defaultConfig =
            "client.transport.ping_timeout=5s\n"
                    + "client.transport.sniff=true\n"
                    + "client.transport.ignore_cluster_name=true\n"
                    + "client.transport.nodes_sampler_interval=5s\n";


    private static final Properties defaultProperties = new Properties();

    static {
        StringReader reader = new StringReader(defaultConfig);
        try {
            defaultProperties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * check the given index name is already exists in the cluster
     *
     * @param client    es client
     * @param indexName the name of index
     * @return true - the index is exists;false - the index is not exists
     */
    public static boolean isIndexExists(TransportClient client, String indexName) {
        IndicesExistsRequest indicesExistsRequest = new IndicesExistsRequest(indexName);
        ActionFuture<IndicesExistsResponse> existsResponseActionFuture = client.admin().indices().exists(indicesExistsRequest);
        return existsResponseActionFuture.actionGet().isExists();
    }

    /**
     * check the given index name is already exists in the cluster
     *
     * @param client    es client
     * @param indexName the name of index
     * @return true - the index is exists;false - the index is not exists
     */
    public static boolean isIndexExists(IndicesAdminClient client, String indexName) {
        IndicesExistsRequest indicesExistsRequest = new IndicesExistsRequest(indexName);
        ActionFuture<IndicesExistsResponse> existsResponseActionFuture = client.exists(indicesExistsRequest);
        return existsResponseActionFuture.actionGet().isExists();
    }

    public static void createIndex(TransportClient client, String indexName, Properties indexProps) {
        IndicesAdminClient indicesAdminClient = client.admin().indices();
        if (isIndexExists(indicesAdminClient, indexName)) {
            logger.info("index[{}] exists!",indexName);
            return;
        }
        String index = indicesAdminClient.prepareCreate(indexName).setSettings(indexProps).execute().actionGet().toString();
        System.out.println(index);
    }

    public static TransportClient getTransportClient(List<String> servers, String clusterName, Properties properties) {
        Settings.Builder builder = Settings.builder().put(defaultProperties);
        //
        if (properties != null && properties.size() > 0) {
            builder.put(properties);
        }
        //设置集群的名称
        if (StringUtil.isEmpty(clusterName)) {
            builder.put("cluster.name", clusterName);
        }

        TransportClient transportClient = new PreBuiltTransportClient(builder.build());
        for (String server : servers) {
            if (!server.contains(":")) {
                continue;
            }
            String[] serverInfo = server.split(":");
            if (serverInfo == null || serverInfo.length != 2) {
                continue;
            }
            try {
                transportClient.addTransportAddress(new InetSocketTransportAddress(Inet4Address.getByName(serverInfo[0]), Integer.parseInt(serverInfo[1])));
            } catch (NumberFormatException nfe) {

            } catch (UnknownHostException uhe) {
            }
        }
        return transportClient;
    }


    public static void executeSQL(String esServer, String sql, Properties properties) {

    }

    public static void main(String[] args) {
        TransportClient transportClient = getTransportClient(Arrays.asList("192.168.21.41:9300"), "hangxin-poc", null);
        //                .listedNodes().forEach(it -> System.out.println(it.getHostAddress()));
        List<DiscoveryNode> nodes = transportClient.connectedNodes();
        for (DiscoveryNode node : nodes) {
            System.out.println(
                    node.getHostAddress() + ":" + node.getAddress().getPort());
        }

        System.out.println(isIndexExists(transportClient, "hangxin15t_log11_rh2288j10-es_05"));
        createIndex(transportClient, "hangxin15t_log11_rh2288j10-es_051",null);

    }
}
