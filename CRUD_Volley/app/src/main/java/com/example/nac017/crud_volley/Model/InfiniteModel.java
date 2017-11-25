package com.example.nac017.crud_volley.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nac017 on 06/11/17.
 */

public class InfiniteModel {
    private int id, count;
    private String name, permalink;

    //private List<String> image = new ArrayList<>();

    public InfiniteModel() {
        this.id = id;
        this.count = count;
        this.name = name;
        this.permalink = permalink;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }
}
