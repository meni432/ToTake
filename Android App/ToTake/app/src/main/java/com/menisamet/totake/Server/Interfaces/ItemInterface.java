package com.menisamet.totake.Server.Interfaces;

/**
 * Created by meni on 17/04/17.
 */

public interface ItemInterface {
    public String getItemName();
    public long getItemAmount();
    public boolean isDone(); // marked as taken
}
