package website.totake.SqlStructure;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by meni on 26/05/17.
 */
@Entity
@Table(name = "items")
public class Item {
    public static final int ITEM_REGULAR = 0;
    public static final int ITEM_DELETED = 1;
    @Transient
    private Trip owner;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item_id")
    private long itemID;
    @Column(name = "en_name")
    private String mItemEHName;
    @Column(name = "he_name")
    private String mItemHEName;
    @Column(name = "status", nullable = false)
    private Long status;

    @OneToMany(mappedBy = "primaryKey.item",
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ItemDetails> itemDetails = new HashSet<>();

    public Item() {
        setStatus(ITEM_REGULAR);
    }

    public long getStatus() {
        if (status != null) {
            return status.longValue();
        }
        return -1;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public Item(String mItemEHName, String mItemHEName) {
        this.mItemEHName = mItemEHName;
        this.mItemHEName = mItemHEName;
    }


    @JsonIgnore
    @Transient
    public Set<ItemDetails> getItemDetails() {
        return itemDetails;
    }

    public void setItemDetails(Set<ItemDetails> itemDetails) {
        this.itemDetails = itemDetails;
    }

    @JsonProperty("itemInTripId")
    public long getItemID() {
        return itemID;
    }

    @JsonProperty("itemInTripName")
    public String getItemEHName() {
        return mItemEHName;
    }

    @JsonIgnore
    public Trip getOwner() {
        return owner;
    }

    public void setOwner(Trip owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemID=" + itemID +
                ", mItemEHName='" + mItemEHName + '\'' +
                ", mItemHEName='" + mItemHEName + '\'' +
                '}';
    }

}
