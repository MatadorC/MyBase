package com.gov.mybase.net.base;

import java.util.List;

/**
 * Created by 冯小川 on 2019/11/24.
 */

public class ResponseObjects<T> {
    String stateCode;
    String message;
    List<T> data;
    int count;

    public String getStateCode() {
        return stateCode;
    }

    public String getMessage() {
        return message;
    }

    public List<T> getData() {
        return data;
    }

    public int getCount() {
        return count;
    }
}
