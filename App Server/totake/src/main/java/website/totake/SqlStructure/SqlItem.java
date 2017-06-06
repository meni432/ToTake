package website.totake.SqlStructure;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(mappedBy = "primaryKey.item",
            cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<SqlItemDetails> sqlItemDetails = new HashSet<>();

    public SqlItem() {}

    public SqlItem(String mItemEHName, String mItemHEName) {
        this.mItemEHName = mItemEHName;
        this.mItemHEName = mItemHEName;
    }


    public Set<SqlItemDetails> getSqlItemDetails() {
        return sqlItemDetails;
    }

    public void setSqlItemDetails(Set<SqlItemDetails> sqlItemDetails) {
        this.sqlItemDetails = sqlItemDetails;
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
