package com.github.noobymatze.bikerental.business.rental.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Represents a category of an article. For example
 * bikes.
 *
 * @author Matthias Metzger
 */
@Entity
@Table(name = "category")
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @ManyToOne
    private Category parentCategory;

    @ManyToMany
    private final List<Article> articles = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public List<Article> getArticles() {
        return articles;
    }

    @Override
    public String toString() {
        return String.format("%s", name);
    }

}
