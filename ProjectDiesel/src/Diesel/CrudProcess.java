package Diesel;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;






public class CrudProcess {
	private final String NAMESPACE = "Diesel.mapper1";

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
	
	public Integer selectCountCash(Charge_sale c) {
		SqlSession ss = getSession();
		Integer cs = null;
		try {
			String query = NAMESPACE + ".selectCountCash";
			cs = ss.selectOne(query,c);
			return cs;
		} finally {
			ss.close();
		}
	}
	public Integer selectDayIncome(Charge_sale c) {
		SqlSession ss = getSession();
		Integer cs = null;
		try {
			String query = NAMESPACE + ".selectDayIncome";
			cs = ss.selectOne(query,c);
			return cs;
		} finally {
			ss.close();
		}
	}
	
	public Integer selectDayIncome2(Charge_refund c) {
		SqlSession ss = getSession();
		Integer cs = null;
		try {
			String query = NAMESPACE + ".selectDayIncome2";
			cs = ss.selectOne(query,c);
			return cs;
		} finally {
			ss.close();
		}
	}
	
	public List<Customer_info> selectInfoA(String c) {
		SqlSession ss = getSession();
		List<Customer_info> cf = null;
		try {
			String query = NAMESPACE + ".selectInform";
			cf = ss.selectList(query, c);
			return cf;
		} finally {
			ss.close();
		}

	}
	public Integer insertGameRefund(Game_refund gr) {
		SqlSession ss = getSession();
		Integer result = null;
		try {
			String query = NAMESPACE + ".insertGameRefund";
			result = ss.insert(query, gr);
			if (result > 0)
				ss.commit();
			else
				ss.rollback();
			return result;
		} finally {
			ss.close();
		}
	}
	
	
	public Game_sale selectgameSale(Game_sale g) {
		SqlSession ss = getSession();
		Game_sale gs = null;
		try {
			String query = NAMESPACE + ".selectgameSale";
			gs = ss.selectOne(query,g);
			return gs;
		} finally {
			ss.close();
		}
	}
	
	
	public Integer deleteMyList(MyList m) {
		SqlSession ss = getSession();
		Integer result = null;
		try {
			String query = NAMESPACE + ".deleteMyList";
			result = ss.delete(query, m);
			if (result > 0)
				ss.commit();
			else
				ss.rollback();
			return result;
		} finally {
			ss.close();
		}
	}
	
	public Integer updateSalesInfo2(Sales_info s) {
		SqlSession ss = getSession();
		Integer result = null;
		try {
			String query = NAMESPACE + ".updateSalesInfo2";
			result = ss.update(query, s);
			if (result > 0)
				ss.commit();
			else
				ss.rollback();
			return result;
		} finally {
			ss.close();
		}
	}
	
	public Integer updateIsRefunded(Game_sale s) {
		SqlSession ss = getSession();
		Integer result = null;
		try {
			String query = NAMESPACE + ".updateIsRefunded";
			result = ss.update(query, s);
			if (result > 0)
				ss.commit();
			else
				ss.rollback();
			return result;
		} finally {
			ss.close();
		}
	}
	
	
	public Integer updateAdminInform(Customer_info c) {
		SqlSession ss = getSession();
		Integer result = null;
		try {
			String query = NAMESPACE + ".updateAdminInform";
			result = ss.update(query, c);
			if (result > 0)
				ss.commit();
			else
				ss.rollback();
			return result;
		} finally {
			ss.close();
		}
	}
	
	

	
	public List<Customer_info> selectAllUser(){
		SqlSession s = getSession();
		try {
			String q = NAMESPACE+".selectAllUser";
			List<Customer_info> l=s.selectList(q);
			return l;
		}finally {
			s.close();
		}
	}
	
	
	
	public Integer insertGame(Game_info g) {
		SqlSession ss = getSession();
		Integer result = null;
		try {
			String query = NAMESPACE + ".insertGame";
			result = ss.insert(query, g);
			if (result > 0)
				ss.commit();
			else
				ss.rollback();
			return result;
		} finally {
			ss.close();
		}
	}
	
	
	public Integer insertSales(Sales_info s) {
		SqlSession ss = getSession();
		Integer result = null;
		try {
			String query = NAMESPACE + ".insertSales";
			result = ss.insert(query, s);
			if (result > 0)
				ss.commit();
			else
				ss.rollback();
			return result;
		} finally {
			ss.close();
		}
	}
	
	
	
	public void updateAfterPurchase(Integer num, String str) {
		SqlSession ss = getSession();
		Customer_info c1 = null;
		Integer result = null;
		try {
			String query = NAMESPACE + ".selectInform";
			c1 = ss.selectOne(query, str);
			c1.setUser_cash(c1.getUser_cash() - num);
			query = NAMESPACE + ".updateCash";
			result = ss.update(query, c1);
			if (result > 0) {
				ss.commit();
				DiselFrame.loginCustomer = c1;
				DiselFrame.bp.Cash_Label.setText(c1.getUser_cash() + "원");
			} else
				ss.rollback();
		} catch (Exception e) {

		} finally {
			ss.close();
		}
	}

	public String selectGameOrderCode() {
		SqlSession ss = getSession();
		String gameCode = null;
		try {
			String query = NAMESPACE + ".selectGameOrderCode";
			gameCode = ss.selectOne(query);
			return gameCode;
		} finally {
			ss.close();
		}
	}

	public Customer_info selectFindPass(HashMap map) {
		SqlSession ss = getSession();
		Customer_info cf = null;
		try {
			String query = NAMESPACE + ".selectFindPass";
			cf = ss.selectOne(query, map);
			return cf;
		} finally {
			ss.close();
		}

	}

	public Customer_info selectFindId(HashMap map) {
		SqlSession ss = getSession();
		Customer_info cf = null;
		try {
			String query = NAMESPACE + ".selectFindId";
			cf = ss.selectOne(query, map);
			return cf;
		} finally {
			ss.close();
		}

	}

	public Integer insertGameSale(Game_sale g) {
		SqlSession ss = getSession();
		Integer result = null;
		try {
			String query = NAMESPACE + ".insertGameSale";
			result = ss.insert(query, g);
			if (result > 0)
				ss.commit();
			else
				ss.rollback();
			return result;
		} finally {
			ss.close();
		}
	}

	public Integer updateSalesInfo(Sales_info s) {
		SqlSession ss = getSession();
		Integer result = null;
		try {
			String query = NAMESPACE + ".updateSalesInfo";
			result = ss.insert(query, s);
			if (result > 0)
				ss.commit();
			else
				ss.rollback();
			return result;
		} finally {
			ss.close();
		}
	}

	public Integer insertGameList(MyList m) {
		SqlSession ss = getSession();
		Integer result = null;
		try {
			String query = NAMESPACE + ".insertGameList";
			result = ss.insert(query, m);
			if (result > 0)
				ss.commit();
			else
				ss.rollback();
			return result;
		} finally {
			ss.close();
		}
	}

	public Integer updateInform(Customer_info c) {
		SqlSession ss = getSession();
		Integer result = null;
		try {
			String query = NAMESPACE + ".updateInform";
			result = ss.update(query, c);
			if (result > 0)
				ss.commit();
			else
				ss.rollback();
			return result;
		} finally {
			ss.close();
		}
	}

	public Customer_info selectInfo(String c) {
		SqlSession ss = getSession();
		Customer_info cf = null;
		try {
			String query = NAMESPACE + ".selectInform";
			cf = ss.selectOne(query, c);
			return cf;
		} finally {
			ss.close();
		}

	}

	public List<MyList> selectGetGameInfo(HashMap map) {
		SqlSession ss = getSession();
		List<MyList> gl = null;
		try {
			String query = NAMESPACE + ".selectgetGameList";
			gl = ss.selectList(query, map);
			return gl;
		} finally {
			ss.close();
		}

	}

	public List<Game_info> selectGameInfo() {
		SqlSession ss = getSession();
		List<Game_info> gl = null;
		try {
			String query = NAMESPACE + ".selectGameInfo";
			gl = ss.selectList(query);
			return gl;
		} finally {
			ss.close();
		}

	}

// 여기서부터 복사 시작
	public void updateCash(Integer num, String str) {
		SqlSession ss = getSession();
		Customer_info c1 = null;
		Integer result = null;
		try {
			String query = NAMESPACE + ".selectInform";
			c1 = ss.selectOne(query, str);
			c1.setUser_cash(c1.getUser_cash() + num);
			query = NAMESPACE + ".updateCash";
			result = ss.update(query, c1);
			if (result > 0) {
				ss.commit();
				DiselFrame.loginCustomer = c1;
				DiselFrame.bp.Cash_Label.setText(c1.getUser_cash() + "원");
			} else
				ss.rollback();
		} catch (Exception e) {

		} finally {
			ss.close();
		}
	}

	public String selectChargeCode() {
		SqlSession ss = getSession();
		String code = null;
		try {
			String query = NAMESPACE + ".selectChargeCode";
			code = ss.selectOne(query);
			return code;
		} finally {
			ss.close();
		}
	}

	public String selectRefundCode() {
		SqlSession ss = getSession();
		String code = null;
		try {
			String query = NAMESPACE + ".selectRefundCode";
			code = ss.selectOne(query);
			return code;
		} finally {
			ss.close();
		}
	}

	public void insertCharge(Charge_sale car) {
		SqlSession ss = getSession();
		Integer result = null;
		try {
			String query = NAMESPACE + ".insertCharge";
			result = ss.insert(query, car);
			if (result > 0) {
				ss.commit();

			} else
				ss.rollback();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ss.close();
		}
	}

	public void insertRefund(Charge_refund car) {
		SqlSession ss = getSession();
		Integer result = null;
		try {
			String query = NAMESPACE + ".insertRefund";
			result = ss.insert(query, car);
			if (result > 0) {
				ss.commit();

			} else
				ss.rollback();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ss.close();
		}
	}



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
		} catch (Exception e) {
			return 0;
		}finally {
			ss.close();
		}
	}


public List<Charge_sale> selectCharge(String user_id) {
	SqlSession ss = getSession();
	List<Charge_sale> cs = null;
	try {
		String query = NAMESPACE + ".selectCharge";
		cs = ss.selectList(query, user_id);
		return cs;
	} finally {
		ss.close();
	}

}

public List<Charge_sale> selectAllCharge() {
	SqlSession ss = getSession();
	List<Charge_sale> cs = null;
	try {
		String query = NAMESPACE + ".selectAllCharge";
		cs = ss.selectList(query);
		return cs;
	} finally {
		ss.close();
	}

}

public List<Charge_refund> selectRefund(String user_id) {
	SqlSession ss = getSession();
	List<Charge_refund> rf = null;
	try {
		String query = NAMESPACE + ".selectRefund";
		rf = ss.selectList(query, user_id);
		return rf;
	} finally {
		ss.close();
	}

}

public List<Charge_refund> selectAllRefund() {
	SqlSession ss = getSession();
	List<Charge_refund> rf = null;
	try {
		String query = NAMESPACE + ".selectAllRefund";
		rf = ss.selectList(query);
		return rf;
	} finally {
		ss.close();
	}

}

public List<Game_info> SelectCount() {
	SqlSession session = getSession();
	try {
		String query=NAMESPACE+".selectCount";
		List<Game_info> count = session.selectList(query);
		return count;
	}finally {
		session.close();
	}
}

	private SqlSession getSession() {
		String path = "Diesel/NewFile.xml";
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

