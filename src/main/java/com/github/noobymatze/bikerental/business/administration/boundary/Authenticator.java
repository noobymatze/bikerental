package com.github.noobymatze.bikerental.business.administration.boundary;

import com.github.noobymatze.bikerental.business.administration.entity.Customer;
import java.util.List;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Provides access to the users in the database.
 *
 * @author Matthias Metzger
 */
@Stateless
@NoArgsConstructor
@AllArgsConstructor
public class Authenticator {

    @PersistenceContext(unitName = "bikerental")
    EntityManager em;

    /**
     * Tries to authenticate a user based on the email and password given.
     * 
     * @param email The email from the possible user.
     * @param password The password belonging to the user.
     * @return Optional user, if they could be found.
     */
    public Optional<Customer> tryAuthenticate(String email, String password) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Customer> query = cb.createQuery(Customer.class);
        Root<Customer> user = query.from(Customer.class);

        query.where(
            cb.equal(user.get("email"), email),
            cb.equal(user.get("password"), password)
        );

        List<Customer> users = em.createQuery(query).getResultList();
        return users.isEmpty() ? 
            Optional.empty() :
            Optional.of(users.get(0));
    }

}
