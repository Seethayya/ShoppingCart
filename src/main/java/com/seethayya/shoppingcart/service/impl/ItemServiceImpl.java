package com.seethayya.shoppingcart.service.impl;

import com.seethayya.shoppingcart.dao.ItemDao;
import com.seethayya.shoppingcart.dto.Item;
import com.seethayya.shoppingcart.service.ItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Seethayya
 * Date: 4/12/14
 * Time: 1:16 PM
 */
@Service
public class ItemServiceImpl implements ItemService{

    private ItemDao itemDao;

    public List<Item> loadAllItems() {
       List<Item> itemList = itemDao.loadAll();
        return itemList;
    }

    @Resource
    public void setItemDao(ItemDao itemDao) {
        this.itemDao = itemDao;
    }
}
