package com.tang.learn;

import java.util.Date;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.tang.learn.entity.UserAccount;

/**
 * 基础CRUD学习
 * 
 * @author tang
 *
 */
public class The01Application {

	/**
	 * 测试数据写入
	 * 
	 * @param session
	 */
	public static void test_save(Session session) {
		session.beginTransaction();

		UserAccount user = new UserAccount();
		user.setUsername("tang");
		user.setPassword("xxxxxxx");
		user.setNickname("test");
		user.setCreateTime(new Date());
		System.out.println("此时user是瞬时状态");

		session.save(user);
		System.out.println("此时user是持久状态");

		session.getTransaction().commit();

		session.close();
		System.out.println("此时user是脱管状态");
	}

	/**
	 * 测试数据查询
	 * 
	 * @param session
	 */
	public static void test_query(Session session) {
		UserAccount user = (UserAccount) session.get(UserAccount.class, 1);
		System.out.println("user=" + user);
	}

	/**
	 * 测试数据更新
	 * 
	 * @param session
	 */
	public static void test_update(Session session) {
		session.beginTransaction();
		UserAccount user = (UserAccount) session.get(UserAccount.class, 2);
		if (null != user) {
			user.setUsername("yibo");
			session.update(user);
			System.out.println("update user :" + user);
		} else {
			System.out.println("user not exists!");
		}
		session.getTransaction().commit();
	}

	/**
	 * 测试数据删除
	 * 
	 * @param session
	 */
	public static void test_delete(Session session) {
		session.beginTransaction();
		UserAccount user = (UserAccount) session.get(UserAccount.class, 2);
		if (null != user) {
			session.delete(user);
			System.out.println("delete user :" + user);
		} else {
			System.out.println("user not exists!");
		}
		session.getTransaction().commit();
	}

	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		// HibernateLearnApplication.test_save(session);
		The01Application.test_update(session);
		// HibernateLearnApplication.test_query(session);
		// HibernateLearnApplication.test_delete(session);

		sessionFactory.close();
	}

}
