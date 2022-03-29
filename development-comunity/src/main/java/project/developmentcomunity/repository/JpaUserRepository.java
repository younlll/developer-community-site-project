package project.developmentcomunity.repository;

import project.developmentcomunity.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public class JpaUserRepository implements UserRepository {

    private final EntityManager em;

    public JpaUserRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(User user) {
        em.persist(user);
        em.flush();
    }

    @Override
    public Long inqMaxUserId() {
        return (long) em.createQuery("select MAX(u.userId) from User u")
                .getFirstResult();
    }

    @Override
    public boolean findByEmail(String email) {
        try {
            User user = em.createQuery("select u from User u where u.email = :email", User.class)
                    .setParameter("email", email)
                    .getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        }


    }

    @Override
    public Optional<User> findById(Long userId) {
        User findUser = em.find(User.class, userId);
        return Optional.ofNullable(findUser);
    }

    @Override
    public String inqUserPassword(String userEmail) {
        return null;
    }
}
