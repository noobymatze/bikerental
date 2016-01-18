package com.github.noobymatze.bikerental.business.items.entity;

import com.github.noobymatze.bikerental.business.time.entity.Duration;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.REFRESH;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents an item currently being in a repair shop to be fixed.
 *
 * @author Matthias Metzger
 */
@Entity
@Table(name = "repairment")
@Getter
@Setter
public class Repairment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Duration duration;

    private BigDecimal price;

    @ManyToMany(cascade = {MERGE, REFRESH})
    private final List<Broken> items = new ArrayList<>();

    @ManyToOne(optional = false)
    private Company company;

    public void addItem(Broken item) {
        items.add(item);
    }
    
}
