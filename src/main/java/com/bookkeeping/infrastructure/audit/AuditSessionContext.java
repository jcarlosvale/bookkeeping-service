package com.bookkeeping.infrastructure.audit;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.reactive.mutiny.Mutiny;

import java.util.Objects;

@ApplicationScoped
public class AuditSessionContext {

    private final Mutiny.SessionFactory sessionFactory;

    public AuditSessionContext(final Mutiny.SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Uni<Void> setUserId(final String userId) {
        Objects.requireNonNull(userId);
        final String sql = "SET audit.user_id = '" + userId + "'";
        return sessionFactory.withTransaction(
                (session, tx) -> session.createNativeQuery(sql)
                        .executeUpdate()
                        .replaceWithVoid()
        );
    }
}
