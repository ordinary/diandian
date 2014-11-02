package com.diandian.domain;

import java.util.Date;

/**
 * 商品信息
 * Created by zhutao on 14/10/19.
 */
public class Good extends BaseEntity{



    /**
     * 主题
     */
    private String topic;

    /**
     * 商品大类别
     */
    private int bigType;

    /**
     * 商品小类别
     */
    private int smallType;

    /**
     * 商品内容
     */
    private String context;

    /**
     *是出售还是求购
     */
    private int isSale;

    /**
     * 商品图片
     */
    private String picture;

    /**
     * 发布者Id
     */
    private Long publishId;





    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getBigType() {
        return bigType;
    }

    public void setBigType(int bigType) {
        this.bigType = bigType;
    }

    public int getSmallType() {
        return smallType;
    }

    public void setSmallType(int smallType) {
        this.smallType = smallType;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Long getPublishId() {
        return publishId;
    }

    public void setPublishId(Long publishId) {
        this.publishId = publishId;
    }

    public int getIsSale() {
        return isSale;
    }

    public void setIsSale(int isSale) {
        this.isSale = isSale;
    }


}
