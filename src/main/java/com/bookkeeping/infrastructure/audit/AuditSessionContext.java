package com.bookkeeping.infrastructure.audit;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.reactive.mutiny.Mutiny;

@ApplicationScoped
public class AuditSessionContext {

    @Inject
    Mutiny.SessionFactory sessionFactory;

    public Uni<Void> setUserId(String userId) {
        return sessionFactory
            .withSession(session ->
                session.createNativeQuery("SET audit.user_id = :userId")
                       .setParameter("userId", userId)
                       .executeUpdate()
                       .replaceWithVoid()
            );
    }
}
