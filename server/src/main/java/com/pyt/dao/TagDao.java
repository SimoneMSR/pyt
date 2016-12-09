package com.pyt.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.pyt.model.Tag;
import com.pyt.model.Tag_;

public class TagDao extends BaseDao<Tag, Tag_>{
    public List<Tag> getAllOrderedByName() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tag> criteria = cb.createQuery(Tag.class);
        Root<Tag> tag = criteria.from(Tag.class);
        criteria.select(tag).orderBy(cb.asc(tag.get(Tag_.name)));
        return em.createQuery(criteria).getResultList();
    }
}
