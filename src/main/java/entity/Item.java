package entity;

import java.sql.Date;

public class Item {
    private int id;
    private String name;
    private float price;
    private int qty;
    private Date createDate;
    private String info;

    public Item(int id, String name, float price, int qty, Date createDate, String info) {
        this(name, price, qty, createDate, info);
        this.id = id;

    }

    public Item(String name, float price, int qty, Date createDate, String info) {
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.createDate = createDate;
        this.info = info;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public int getQty() {
        return qty;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return String.format("%d %s %.2f %d %s %s", id, name, price, qty, createDate, info);
    }

    public String getItem() {
        return String.format("%s %.2f %d %s %s", name, price, qty, createDate, info);
    }

}
