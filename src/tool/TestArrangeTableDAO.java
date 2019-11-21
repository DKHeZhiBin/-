package tool;

import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import entity.TestArrangeTable;

/**
 * A data access object (DAO) providing persistence and search support for
 * TestArrangeTable entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see entity.TestArrangeTable
 * @author MyEclipse Persistence Tools
 */

public class TestArrangeTableDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TestArrangeTableDAO.class);
	// property constants
	public static final String QID_VESS = "qidVess";
	public static final String BEGIN_TIME = "beginTime";
	public static final String LIMIT_TIME = "limitTime";
	public static final String CLASS_ = "class_";

	public void save(TestArrangeTable transientInstance) {
		log.debug("saving TestArrangeTable instance");
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

	public void delete(TestArrangeTable persistentInstance) {
		log.debug("deleting TestArrangeTable instance");
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

	public TestArrangeTable findById(java.lang.Integer id) {
		log.debug("getting TestArrangeTable instance with id: " + id);
		try {
			TestArrangeTable instance = (TestArrangeTable) getSession().get(
					"entity.TestArrangeTable", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TestArrangeTable instance) {
		log.debug("finding TestArrangeTable instance by example");
		try {
			List results = getSession().createCriteria(
					"entity.TestArrangeTable").add(Example.create(instance))
					.list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TestArrangeTable instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TestArrangeTable as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByQidVess(Object qidVess) {
		return findByProperty(QID_VESS, qidVess);
	}

	public List findByBeginTime(Object beginTime) {
		return findByProperty(BEGIN_TIME, beginTime);
	}

	public List findByLimitTime(Object limitTime) {
		return findByProperty(LIMIT_TIME, limitTime);
	}

	public List findByClass_(Object class_) {
		return findByProperty(CLASS_, class_);
	}

	public List findAll() {
		log.debug("finding all TestArrangeTable instances");
		try {
			String queryString = "from TestArrangeTable";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TestArrangeTable merge(TestArrangeTable detachedInstance) {
		log.debug("merging TestArrangeTable instance");
		try {
			TestArrangeTable result = (TestArrangeTable) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TestArrangeTable instance) {
		log.debug("attaching dirty TestArrangeTable instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TestArrangeTable instance) {
		log.debug("attaching clean TestArrangeTable instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}