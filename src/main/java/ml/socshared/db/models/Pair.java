package ml.socshared.db.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Pair")
public class Pair {
    @Id
    public long id;
    @Column(name="key")
    public String key;
    @Column(name="value")
    public String value;

}
