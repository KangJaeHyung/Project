package CrudPackage;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class CrudProcess {
	private final String NAMESPACE = "CrudPackage.mapper1";
	
	// ���ۿ� �ִ� ������ �����ϴ� �޼ҵ�
	// �� �޼ҵ忡�� mapper���ִ� ������ �ҷ��´�
	// ������ �����ϴ� ��ü�� �ʿ��ϴ�
	// �� ��ü�� �̸��� SqlSession
	// �� Ŭ������ ��� �޼ҵ忡�� SqlSession�� �ʿ��ϴ�
	// SqlSession�� �ѹ��� �����ȵ�
	// SqlSession�� �����ϴ� ��ü�� ����
	// �� ��ü�� �̸��� SqlSessionFactory
	// �׷��� SqlSessionFactory�� ��ü�� �����ϴ� ��ü�� ������
	// �� ��ü�� �̸��� SqlSessionFactoryBuilder
	// SqlSessionFactoryBuilder�� ������ �� ȯ�漳�������� �ʿ���

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