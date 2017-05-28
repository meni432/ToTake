package website.totake.ClientInterfaces;

/**
 * Created by meni on 26/05/17.
 */
public interface ItemInterface {
    public String getItemName();
    public long getItemAmount();
    public boolean isDone(); // marked as taken
    public long getItemId();
}
