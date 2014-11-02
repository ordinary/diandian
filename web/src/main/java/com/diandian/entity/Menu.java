package com.diandian.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by zhutao on 14/10/6.
 */
public class Menu {

    @JSONField(name="button")
    private List<Button> buttons;

    public List<Button> getButtons() {
        return buttons;
    }

    public void setButtons(List<Button> buttons) {
        this.buttons = buttons;
    }
}
