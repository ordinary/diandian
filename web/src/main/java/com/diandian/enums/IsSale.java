package com.diandian.enums;

/**
 * Created by zhutao on 14/10/21.
 */
public enum IsSale {
    NONE(-1),

    YES(0),

    NO(1);

    private int id;

    IsSale(int id) {
        this.id = id;
    }

    public IsSale getIsSaleById(int id) {
        for (IsSale isSale : IsSale.values()) {
            if (id == isSale.id) {
                return isSale;
            }
        }
        return NONE;
    }


    public int getId() {
        return id;
    }
}
