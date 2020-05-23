package com.course.project.businessmanager.repository.impl;

import com.course.project.businessmanager.entity.User;
import com.course.project.businessmanager.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Slf4j
public class UserRepositoryImpl extends BasicRepositoryImpl<User, UUID> implements UserRepository {

    /**
     * Method gets information about all users from DB
     *
     * @return List of all users with ASCII sorting by name
     */
    @Override
    public List<User> getAll() {
        log.info("In getAll()");
        Session session = getSession();
        return session.createQuery("from User ORDER BY role ASC")
                .getResultList();
    }


    /**
     * The method used for getting User by email from database
     *
     * @param email String email used to find User by it
     * @return User
     */
    @Override
    public Optional<User> findByEmail(String email) {
        log.info("Enter into findByEmail method with email:{}", email);
        TypedQuery<User> query = getSession().createNamedQuery("findEmail", User.class).setMaxResults(1);
        query.setParameter("email", email);
        List<User> user = query.getResultList();
        if (user.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(query.getResultList().get(0));
    }

    /**
     * The method used for getting User by token from database
     *
     * @param token String token used to find User by it
     * @return User
     */
    @Override
    public Optional<User> findByToken(String token) {
        log.info("Enter into findByToken  with token:{}", token);
        TypedQuery<User> query = getSession().createNamedQuery("findToken", User.class).setMaxResults(1);
        query.setParameter("token", token);
        List<User> user = query.getResultList();
        if (user.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(query.getResultList().get(0));
    }


    /**
     * Modified update method, which merge entity before updating it
     *
     * @param entity user is going to be updated
     * @return User
     */
    @Override
    public User update(User entity) {
        log.info("Enter into update method with entity:{}", entity);
        entity = (User) getSession().merge(entity);
        getSession().update(entity);
        return entity;
    }

}
