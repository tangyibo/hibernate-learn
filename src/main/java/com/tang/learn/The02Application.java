package com.tang.learn;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.tang.learn.entity.UserAccount;

/**
 * HQL和Criteria查询学习
 * 
 * ==================== SQL： SELECT
 * id,username,nickname,password,phone,email,enabled,create_time,update_time
 * FROM dbsync_user_account where username like '%yibo%';
 * 
 * @author tang
 *
 */
public class The02Application {

	public static void test_query_hql(Session session) {
		// 注： 使用hql的时候，用的是类名UserAccount,而不是表名dbsync_user_account
		// 注： 使用hql的时候，不需要在前面加 select *
		// 注：SQL中的?用?N的形式，即：?0，?1，?2，...
		Query<UserAccount> query = session.createQuery("from UserAccount p where p.username like ?0");
		query.setParameter(0, "%bo%");
		List<UserAccount> us = query.list();
		for (UserAccount u : us) {
			System.out.println(u);
		}
	}

	public static void test_query_criteria(Session session) {
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<UserAccount> criteriaQuery = criteriaBuilder.createQuery(UserAccount.class);
		Root<UserAccount> root = criteriaQuery.from(UserAccount.class);
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.like(root.get("username"), "%yibo%"));
		List<UserAccount> us = session.createQuery(criteriaQuery).getResultList();
		for (UserAccount u : us) {
			System.out.println(u);
		}
	}

	public static void main(String[] args) {
		Configuration configuration=new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		// The02Application.test_query_hql(session);
		The02Application.test_query_criteria(session);

		sessionFactory.close();
	}

}
