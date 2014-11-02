package com.diandian.domain;

import java.util.Date;

/**
 * Created by zhutao on 14/10/22.
 */
public class BaseEntity {
    private Long id;
    private Date createTime;

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
