package com.pyt.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.PluralAttribute;
import javax.persistence.metamodel.SingularAttribute;

import com.pyt.model.Announcement;
import com.pyt.model.Announcement_;
import com.pyt.model.Being;
import com.pyt.model.Being_;
import com.pyt.model.Comment;
import com.pyt.model.Member_;
import com.pyt.model.Quarter;
import com.pyt.model.Quarter_;
import com.pyt.model.Tag;
import com.pyt.rest.queryParams.AnnouncementQueryParams;

import Enums.AnnouncementCathegory;

@Stateless
public class AnnouncementDao extends BaseDao<Announcement,Announcement_>{
	
	public Announcement getById(Long id){
        CriteriaQuery<Announcement> criteria = getCriteriaById(id);
        return em.createQuery(criteria).getSingleResult();
	}
	
	/**
	 * Fetches specified attributes
	 * @param id
	 * @param attributes
	 * @return
	 */
	public Announcement getById(Long id, Attribute<Announcement,?> ... attributes){
		try{
			CriteriaQuery<Announcement> criteria = getCriteriaById(id);
			Iterator<Root<?>> it=criteria.getRoots().iterator();
			Root<Announcement> root = (Root<Announcement>)it.next();
			for(Attribute<Announcement, ?> attribute : attributes){
				if(attribute instanceof SingularAttribute)
					root.fetch((SingularAttribute<Announcement, ?>)attribute);
				if(attribute instanceof PluralAttribute)
					root.fetch((PluralAttribute<Announcement, ?,?>) attribute);
			}
			return em.createQuery(criteria).getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}
	
	private CriteriaQuery<Announcement> getCriteriaById(Long id){
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Announcement> criteria = cb.createQuery(Announcement.class);
        Root<Announcement> announcement = criteria.from(Announcement.class);
        criteria.select(announcement).where(cb.equal(announcement.get(Announcement_.idAnnouncement), id));
        return criteria;
	}
	
	public List<Comment> getCommentsById(Long id){
		try{
			Announcement entity = getById(id, Announcement_.comments); 
			if(entity==null)
				return new ArrayList<Comment>();
			else
				return entity.getComments();
		}catch(NoResultException e){
			return new ArrayList<Comment>();
		}

	}
	
	public List<Announcement> get(AnnouncementQueryParams params){
		Quarter quarter = new Quarter();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Announcement> criteria = cb.createQuery(Announcement.class);
        Root<Announcement> announcement = criteria.from(Announcement.class);
        announcement.fetch(Announcement_.creator);
        Predicate whereQuarter = announcement.join(Announcement_.quarters).in(quarter);
        try {
        	List<Predicate> whereParams= applyParamsFilter(cb,criteria,params,announcement);
        	whereParams.add(whereQuarter);
            criteria.select(announcement).where(whereParams.toArray(new Predicate[]{}));
            return applyQueryParams(criteria,params,cb,announcement);
        }catch(Exception e){
        	return new ArrayList<Announcement>();
        }
	}
	
	public List<Announcement> getByCreatorId(long creatorId, AnnouncementQueryParams p) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Announcement> criteria = cb.createQuery(Announcement.class);
        Root<Announcement> announcement = criteria.from(Announcement.class);
        try{
        List<Predicate> whereParams= applyParamsFilter(cb,criteria,p,announcement);
        whereParams.add(cb.equal(announcement.get(Announcement_.creator).get(Member_.id),creatorId));
        criteria.select(announcement).where(whereParams.toArray(new Predicate[]{}));
        return applyQueryParams(criteria,p,cb,announcement);
        }catch(Exception e){
        	return new ArrayList<Announcement>();
        }
	}
	
	public List<Predicate> applyParamsFilter(CriteriaBuilder cb,CriteriaQuery<Announcement> query,AnnouncementQueryParams params,Root<Announcement> announcement){
		List<Predicate> retval = new ArrayList<Predicate>();
		if(params != null){
			//created by
			if(params.createdBy != null && params.createdBy.length>0){
				Predicate[] createdByPredicate = new Predicate[params.createdBy.length];
				int i=0;
				for(Enums.Being b : params.createdBy)
					createdByPredicate[i++]=cb.equal(announcement.get(Announcement_.creator).get(Member_.role).get(Being_.idBeing),b.code);
				retval.add(cb.or(createdByPredicate));	
			}
			//target
			if(params.targets != null && params.targets.length>0){
				Predicate[] targetPredicate = new Predicate[params.targets.length];
				int i=0;
				boolean hasStudentTarget=false;
				for(Enums.Being t : params.targets){
					targetPredicate[i++]=cb.literal(t).in(announcement.get(Announcement_.beings));
					if(t == Enums.Being.STUDENT)
						hasStudentTarget=true;
				}
				retval.add(cb.or(targetPredicate));
				//department target
				if(hasStudentTarget && params.departmentTargets!=null && params.departmentTargets.length>0){
					i=0;
					Predicate[] departmentPredicate = new Predicate[params.departmentTargets.length];
					for(Enums.Department d : params.departmentTargets){
						departmentPredicate[i++] =cb.literal(d).in(announcement.get(Announcement_.departments));
					}
					retval.add(cb.or(departmentPredicate));
				}
			}
			//tags
			if(params.tags != null && params.tags.length>0){
				Predicate[] tagsPredicate = new Predicate[params.tags.length];
				int i=0;
				for(String t : params.tags){
					tagsPredicate[i++]= cb.literal(t).in(announcement.get(Announcement_.tags));
				}
				retval.add(cb.or(tagsPredicate));
			}
			if(params.title != null && !params.title.isEmpty()){
				retval.add(cb.like(announcement.get(Announcement_.title),"%"+params.title+"%"));
			}
		}
		return retval;
	}
	
	public List<Announcement> applyQueryParams(CriteriaQuery<Announcement> query,AnnouncementQueryParams params, CriteriaBuilder cb,Root<Announcement> announcement){
		if(params !=null){
			if(params.orderBy!=null){
				switch (params.orderBy){
					case AnnouncementQueryParams.ORDERBY_TITLE : {
						query.orderBy(cb.asc(announcement.get(Announcement_.title)));
					} break;
				}
			}
			return super.applyQueryParams(query, params, cb).getResultList();
		}else
			return em.createQuery(query).getResultList();
		
	}


}
