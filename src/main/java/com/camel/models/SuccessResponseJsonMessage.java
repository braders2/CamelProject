package com.camel.models;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mateusz Dobrowolski on 02.12.2016.
 */
@Getter
public class SuccessResponseJsonMessage<T> {
    private String message;
    private List<T> data;

    public void setData(T data) {
        this.data = new ArrayList<T>();
        this.data.add(data);
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
