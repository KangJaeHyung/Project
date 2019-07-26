package CrudPackage;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class CrudProcess {
	private final String NAMESPACE = "CrudPackage.mapper1";
	
	// 매퍼에 있는 쿼리를 접근하는 메소드
	// 이 메소드에서 mapper에있는 쿼리를 불러온다
	// 쿼리를 접근하는 객체가 필요하다
	// 이 객체의 이름은 SqlSession
	// 이 클래스의 모든 메소드에는 SqlSession이 필요하다
	// SqlSession은 한번에 생성안됨
	// SqlSession을 생성하는 객체가 존재
	// 그 객체의 이름은 SqlSessionFactory
	// 그런데 SqlSessionFactory도 객체를 생성하는 객체가 존재함
	// 그 객체의 이름은 SqlSessionFactoryBuilder
	// SqlSessionFactoryBuilder를 생성할 때 환경설정파일이 필요함

	public Customer_info selectInfo(String s) {
		SqlSession ss = getSession();
		Customer_info cf = null;
		try {
			String query = NAMESPACE + ".selectInform";
			cf = ss.selectOne(query, s);
			return cf;
		} finally {
			ss.close();
		}

	}

//	public Integer deleteInfo(Employee_info e) {
//		SqlSession ss = getSession();
//		Integer result = null;
//		try {
//			String query = NAMESPACE2 + ".deleteInfo";
//			result = ss.delete(query, e);
//			if (result > 0)
//				ss.commit();
//			else
//				ss.rollback();
//			return result;
//		} finally {
//			ss.close();
//		}
//
//	}

	public Integer insertInfo(Customer_info c) {
		SqlSession ss = getSession();
		Integer result = null;
		try {
			String query = NAMESPACE + ".insertInform";
			result = ss.insert(query, c);
			if (result > 0)
				ss.commit();
			else
				ss.rollback();
			return result;
		} finally {
			ss.close();
		}
	}

//	public Integer updateInfo(Customer_info e) {
//		SqlSession ss = getSession();
//		Integer result = null;
//		try {
//			String query = NAMESPACE + ".updateInform";
//			result = ss.update(query, e);
//			if (result > 0)
//				ss.commit();
//			else
//				ss.rollback();
//			return result;
//		} finally {
//			ss.close();
//		}
//	}


	private SqlSession getSession() {
		String path = "CrudPackage/NewFile.xml";
		InputStream is = null;
		try {
			is = Resources.getResourceAsStream(path);
		} catch (Exception e) {
		}
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = builder.build(is);
		SqlSession sqlSession = factory.openSession();
		return sqlSession;
	}
}