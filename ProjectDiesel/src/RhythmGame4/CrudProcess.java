package RhythmGame4;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class CrudProcess {
//매퍼에 있는 쿼리를 접근하는 메서드가 있다.
//이 메서드에서 매퍼에 있는 쿼리를 불러온다.
//쿼리를 접근하는 객체가 필요하다.
//이 객체의 이름은 SqlSession
//이 클래스의 모든 메서드에서느 SqlSession이 필요하다.
//따라서, SqlSession을 생성해야 한다.
//SqlSession은 한 번에 생성되지 않는다. 그러면 어떻게 생성?
//SqlSession을 생성하는 객체가 존재한다. 이 객체의 이름은
//SqlSessionFactory
//SqlSessionFactory객체를 생성하는 객체가 존재한다.
//이 객체의 이름은 SqlSessionFactoryBuilder이다.
//SqlSessionFactoryBuilder를 생성할 때 환경설정파일이 필요함.
	private final String NAMESPACE = "RhythmGame4.myMapper";

	public Integer insert_legend_of_moonlight(Result re) {
		SqlSession s = getSession();
		Integer result = null;
		try {
			String query = NAMESPACE + ".insert_legend_of_moonlight";
			result = s.insert(query, re);
			if (result > 0) {
				System.out.println("성공");
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
				System.out.println("성공");
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
