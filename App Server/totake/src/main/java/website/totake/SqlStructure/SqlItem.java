package website.totake.SqlStructure;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by meni on 26/05/17.
 */
@Entity
@Table(name = "items")
public class SqlItem {
    @Transient
    private SqlTrip owner;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item_id")
    private long itemID;
    @Column(name = "en_name")
    private String mItemEHName;
    @Column(name = "he_name")
    private String mItemHEName;

    public SqlItem() {}

    public SqlItem(String mItemEHName, String mItemHEName) {
        this.mItemEHName = mItemEHName;
        this.mItemHEName = mItemHEName;
    }


    public long getItemID() {
        return itemID;
    }

    public String getItemEHName() {
        return mItemEHName;
    }

    public String getItemHEName() {
        return mItemHEName;
    }

    @JsonIgnore
    public SqlTrip getOwner() {
        return owner;
    }

    public void setOwner(SqlTrip owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "SqlItem{" +
                "itemID=" + itemID +
                ", mItemEHName='" + mItemEHName + '\'' +
                ", mItemHEName='" + mItemHEName + '\'' +
                '}';
    }

}
