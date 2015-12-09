package com.github.noobymatze.bikerental.business.administration.boundary;

import com.github.noobymatze.bikerental.business.administration.entity.User;
import java.util.List;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Provides access to the users in the database.
 *
 * @author Matthias Metzger
 */
@Stateless
public class Users {

    @PersistenceContext(unitName = "bikerental")
    EntityManager em;

    /**
     * Tries to authenticate a user based on the email and password given.
     * 
     * @param email The email from the possible user.
     * @param password The password belonging to the user.
     * @return Optional user, if they could be found.
     */
    public Optional<User> tryAuthenticate(String email, String password) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> user = query.from(User.class);

        query.where(
            cb.equal(user.get("email"), email),
            cb.equal(user.get("password"), password)
        );

        List<User> users = em.createQuery(query).getResultList();
        return users.isEmpty() ? 
            Optional.empty() :
            Optional.of(users.get(0));
    }

}
