package website.totake.SqlStructure;

import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by meni on 28/05/17.
 */
@Entity
@Table(name = "trip_item_detail")
public class SqlItemDetails {
    @Id
    @Column(name = "detail_id")
    private long detailId;
    @Column(name = "amount")
    private int amount;
    @Column(name = "is_done")
    private int isDone;


}
