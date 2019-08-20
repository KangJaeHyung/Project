package RhythmGame4;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class CrudProcess {
//���ۿ� �ִ� ������ �����ϴ� �޼��尡 �ִ�.
//�� �޼��忡�� ���ۿ� �ִ� ������ �ҷ��´�.
//������ �����ϴ� ��ü�� �ʿ��ϴ�.
//�� ��ü�� �̸��� SqlSession
//�� Ŭ������ ��� �޼��忡���� SqlSession�� �ʿ��ϴ�.
//����, SqlSession�� �����ؾ� �Ѵ�.
//SqlSession�� �� ���� �������� �ʴ´�. �׷��� ��� ����?
//SqlSession�� �����ϴ� ��ü�� �����Ѵ�. �� ��ü�� �̸���
//SqlSessionFactory
//SqlSessionFactory��ü�� �����ϴ� ��ü�� �����Ѵ�.
//�� ��ü�� �̸��� SqlSessionFactoryBuilder�̴�.
//SqlSessionFactoryBuilder�� ������ �� ȯ�漳�������� �ʿ���.
	private final String NAMESPACE = "RhythmGame4.myMapper";

	public Integer insert_legend_of_moonlight(Result re) {
		SqlSession s = getSession();
		Integer result = null;
		try {
			String query = NAMESPACE + ".insert_legend_of_moonlight";
			result = s.insert(query, re);
			if (result > 0) {
				System.out.println("����");
				s.commit();
			} else {
				s.rollback();
			}
			return result;
		} finally {
			s.close();
		}
	}
	public Integer insert_mirro_night(Result re) {
		SqlSession s = getSession();
		Integer result = null;
		try {
			String query = NAMESPACE + ".insert_mirro_night";
			result = s.insert(query, re);
			if (result > 0) {
				System.out.println("����");
				s.commit();
			} else {
				s.rollback();
			}
			return result;
		} finally {
			s.close();
		}
	}
	

	private SqlSession getSession() {
		String path = "RhythmGame4/mybatis_config.xml";
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
