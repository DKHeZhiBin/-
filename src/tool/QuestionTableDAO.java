package tool;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import entity.QuestionTable;

/**
 * A data access object (DAO) providing persistence and search support for
 * QuestionTable entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see entity.QuestionTable
 * @author MyEclipse Persistence Tools
 */

public class QuestionTableDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(QuestionTableDAO.class);
	// property constants
	public static final String QUEST_TEXT = "questText";
	public static final String ANSWER = "answer";
	public static final String MARK = "mark";
	public static final String A = "a";
	public static final String B = "b";
	public static final String C = "c";
	public static final String D = "d";

	public void save(QuestionTable transientInstance) {
		log.debug("saving QuestionTable instance");
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

	public void delete(QuestionTable persistentInstance) {
		log.debug("deleting QuestionTable instance");
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

	public QuestionTable findById(java.lang.Integer id) {
		log.debug("getting QuestionTable instance with id: " + id);
		try {
			QuestionTable instance = (QuestionTable) getSession().get(
					"entity.QuestionTable", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
		
	}

	public List findByExample(QuestionTable instance) {
		log.debug("finding QuestionTable instance by example");
		try {
			List results = getSession().createCriteria("entity.QuestionTable")
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
		log.debug("finding QuestionTable instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from QuestionTable as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByQuestText(Object questText) {
		return findByProperty(QUEST_TEXT, questText);
	}

	public List findByAnswer(Object answer) {
		return findByProperty(ANSWER, answer);
	}

	public List findByMark(Object mark) {
		return findByProperty(MARK, mark);
	}

	public List findByA(Object a) {
		return findByProperty(A, a);
	}

	public List findByB(Object b) {
		return findByProperty(B, b);
	}

	public List findByC(Object c) {
		return findByProperty(C, c);
	}

	public List findByD(Object d) {
		return findByProperty(D, d);
	}

	public List findAll() {
		log.debug("finding all QuestionTable instances");
		try {
			String queryString = "from QuestionTable";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public QuestionTable merge(QuestionTable detachedInstance) {
		log.debug("merging QuestionTable instance");
		try {
			QuestionTable result = (QuestionTable) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(QuestionTable instance) {
		log.debug("attaching dirty QuestionTable instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(QuestionTable instance) {
		log.debug("attaching clean QuestionTable instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}