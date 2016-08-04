package com.caoye.tulingdemo;

/**
 * Created by admin on 7/18/16.
 */
public class ListData {
    public static final int SEND = 0;
    public static final int RECEIVE = 1;
    public int flag;
    private String content;
    private String time;

    public ListData(String content, int flag, String time) {
        setContent(content);
        setFlag(flag);
        setTime(time);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
