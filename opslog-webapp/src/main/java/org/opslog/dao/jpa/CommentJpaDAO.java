package org.opslog.dao.jpa;

import java.sql.SQLException;
import java.util.Iterator;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.opslog.dao.CommentDAO;
import org.opslog.model.Comment;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class CommentJpaDAO extends HibernateTemplate implements CommentDAO {

	public void save(Comment comment) {
		super.save(comment);
	}

	public Iterator<Comment> list(final int from, final int to) {
		@SuppressWarnings("unchecked")
		Iterator<Comment> retval = execute(new HibernateCallback<Iterator<Comment>>() {
			public Iterator<Comment> doInHibernate(Session session) throws HibernateException, SQLException {
				Criteria criteria = session.createCriteria(Comment.class);
				criteria.addOrder(Order.desc("sendDate"));
				return criteria.setFirstResult(from).setMaxResults(to - from).list().iterator();
			}
		});
		return retval;
	}
	
	public Comment getComment(Long commentId) {
		return get(Comment.class, commentId);
	}
}
