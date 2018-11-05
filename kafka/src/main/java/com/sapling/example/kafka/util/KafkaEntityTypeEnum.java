package com.sapling.example.kafka.util;

/**
 * @author weizhou
 * @version v1.0
 * @date 2018/10/31
 * @since v1.0
 */
public enum KafkaEntityTypeEnum {

    /**
     * type of topic
     */
    ENTITY_TYPE_TOPIC("topic"),
    /**
     * type of broke
     */
    ENTITY_TYPE_BROKE("broke"),
    /**
     * type of client
     */
    ENTITY_TYPE_CLIENT("client"),
    /**
     * type of user
     */
    ENTITY_TYPE_USER("user");;

    private String typeName;

    KafkaEntityTypeEnum(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public static KafkaEntityTypeEnum getEntityTypeByTypeName(String typeName) {
        for (KafkaEntityTypeEnum item : KafkaEntityTypeEnum.values()) {
            if (item.typeName.equals(typeName)) {
                return item;
            }
        }
        return null;
    }
}
