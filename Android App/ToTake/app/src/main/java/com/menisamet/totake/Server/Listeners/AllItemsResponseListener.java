package com.menisamet.totake.Server.Listeners;

import com.menisamet.totake.Modals.Item;

import java.util.List;

/**
 * Created by tahel on 25/04/2017.
 */

public interface AllItemsResponseListener {
    public void onResponse(List<Item> items);
}
