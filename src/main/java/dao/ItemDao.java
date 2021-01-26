package dao;

import entity.Item;

import java.util.List;

public interface ItemDao {

    List<Item> findAll();

    boolean insert(Item item);

    boolean update(Item item);
}
