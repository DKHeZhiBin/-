package tool;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import entity.ScoreTable;

/**
 * A data access object (DAO) providing persistence and search support for
 * ScoreTable entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see entity.ScoreTable
 * @author MyEclipse Persistence Tools
 */

public class ScoreTableDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ScoreTableDAO.class);
	// property constants
	public static final String SCORE = "score";

	public void save(ScoreTable transientInstance) {
		log.debug("saving ScoreTable instance");
		Transaction tran = getSession().beginTransaction();
		try {
			getSession().saveOrUpdate(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		tran.commit();
	    getSession().flush();
	    getSession().close();
	}

	public void delete(ScoreTable persistentInstance) {
		log.debug("deleting ScoreTable instance");
		Transaction tran = getSession().beginTransaction();
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
		tran.commit();
	    getSession().flush();
	    getSession().close();
	}

	public ScoreTable findById(java.lang.Integer id) {
		log.debug("getting ScoreTable instance with id: " + id);
		try {
			ScoreTable instance = (ScoreTable) getSession().get(
					"entity.ScoreTable", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ScoreTable instance) {
		log.debug("finding ScoreTable instance by example");
		try {
			List results = getSession().createCriteria("entity.ScoreTable")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding ScoreTable instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from ScoreTable as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByScore(Object score) {
		return findByProperty(SCORE, score);
	}

	public List findAll() {
		log.debug("finding all ScoreTable instances");
		try {
			String queryString = "from ScoreTable";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ScoreTable merge(ScoreTable detachedInstance) {
		log.debug("merging ScoreTable instance");
		try {
			ScoreTable result = (ScoreTable) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ScoreTable instance) {
		log.debug("attaching dirty ScoreTable instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ScoreTable instance) {
		log.debug("attaching clean ScoreTable instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}