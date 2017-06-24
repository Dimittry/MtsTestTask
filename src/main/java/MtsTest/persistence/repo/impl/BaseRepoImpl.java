package MtsTest.persistence.repo.impl;


import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public abstract class BaseRepoImpl {
    @PersistenceContext
    protected EntityManager em;

    public BaseRepoImpl() {}
}
