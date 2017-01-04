package com.pyt.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.pyt.model.Member;
import com.pyt.model.Member_;
import com.pyt.model.Quarter;
import com.pyt.model.QuarterAnnouncement;
import com.pyt.model.QuarterAnnouncement_;
import com.pyt.model.Quarter_;

public class QuarterDao extends BaseDao<Quarter,Quarter_> {

	public List<Quarter> getAll(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Quarter> criteria = cb.createQuery(Quarter.class);
        Root<Quarter> quarter = criteria.from(Quarter.class);
        criteria.select(quarter);
        return em.createQuery(criteria).getResultList();
	}
	public Quarter getById(int id){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Quarter> criteria = cb.createQuery(Quarter.class);
        Root<Quarter> quarter = criteria.from(Quarter.class);
        criteria.select(quarter).where(cb.equal(quarter.get(Quarter_.idQuarter), id));
        return em.createQuery(criteria).getSingleResult();
	}
	public int countAnnouncement(int quarterId) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = cb.createQuery(Long.class);
		Root<QuarterAnnouncement> root = query.from(QuarterAnnouncement.class);
		query.select(cb.count(root)).where(cb.equal(root.get(QuarterAnnouncement_.idQuarter),quarterId));
		return em.createQuery(query).getSingleResult().intValue();
	}
	public int countMember(int quarterId) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = cb.createQuery(Long.class);
		Root<Member> root = query.from(Member.class);
		query.select(cb.count(root)).where(cb.equal(root.get(Member_.quarter).get(Quarter_.idQuarter),quarterId));
		return em.createQuery(query).getSingleResult().intValue();
	}
}
