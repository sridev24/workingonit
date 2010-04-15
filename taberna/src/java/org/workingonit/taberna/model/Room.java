package org.workingonit.taberna.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("room")
public class Room {

    private Integer number;
    private String name;
    private Integer price;

    public Room() {
        // noop
    }

    public Room(Integer number, String name, Integer price) {
        this.number = number;
        this.name = name;
        this.price = price;
    }

    public Integer getNumber() {
        return this.number;
    }

    public String getName() {
        return this.name;
    }

    public Integer getPrice() {
        return this.price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

}
