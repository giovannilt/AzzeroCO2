package it.agilis.mens.azzeroCO2.core.dao;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate3.HibernateAccessor;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.sql.SQLException;
import java.util.List;
import java.util.Locale;


public class DAOSupport extends HibernateDaoSupport {

    interface MyRunnable {
        Object run(HibernateTemplate hibernateTemplate) throws Exception;
    }

    private static Logger logger = Logger.getLogger(DAOSupport.class);

    protected Object runAndLogException(final MyRunnable run) throws Exception {
        try {
            return run.run(this.getHibernateTemplate());
        } catch (DataIntegrityViolationException dive) {
            Exception app = (Exception) dive.getRootCause();
            Exception ex;
            if (app.getMessage().toUpperCase(Locale.ENGLISH).contains("UNIQUE CONSTRAINT")) {
                ex = logException(dive, "Number Already in use");
            } else {
                ex = logException(app, null);
            }
            throw ex;
        } catch (Exception exception) {
            //Object app = exception.getCause();            
            throw logException(exception, null);
        }
    }

    protected Exception logException(final Exception ex, final String msg) {
        logger.error("logException - Exception: " + ex.getMessage(), ex);
        return (Exception)ex;
    }

    protected List<?> getList(final DetachedCriteria criteria, final boolean cachable) {
        return getHibernateTemplate().executeFind(
                new HibernateCallback() {
                    public List<?> doInHibernate(final Session session)
                            throws SQLException {
                        Criteria executableCriteria = criteria.getExecutableCriteria(session);
                        executableCriteria.setCacheable(cachable);
                        return executableCriteria.list();
                    }

                });
    }

    protected Object getObjectById(final Class<?> entity, final Long id) {
        return this.getHibernateTemplate().execute(
                new HibernateCallback() {
                    public Object doInHibernate(final Session session)
                            throws SQLException {
                        return session.get(entity, id);
                    }
                });
    }

    protected void deleteObject(final Object row) throws Exception {
        runAndLogException(new MyRunnable() {
            public Object run(final HibernateTemplate hibernateTemplate)
                    throws Exception {

                    hibernateTemplate.setFlushMode(HibernateAccessor.FLUSH_EAGER);
                    hibernateTemplate.delete(row);

                return null;
            }
        });
    }

    protected void saveObject(final Object entity) throws Exception {
        runAndLogException(new MyRunnable() {
            public Object run(final HibernateTemplate hibernateTemplate)
                    throws Exception {
                hibernateTemplate.setFlushMode(HibernateAccessor.FLUSH_EAGER);

                hibernateTemplate.saveOrUpdate(entity);

                return null;
            }
        });
    }
}
