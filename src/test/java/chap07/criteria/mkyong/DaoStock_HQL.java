package chap07.criteria.mkyong;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

/**
 * Source by MKyong.com
 * http://www.mkyong.com/hibernate/hibernate-criteria-examples/
 *  Moved by Arahansa
 *  HQL을 썼을 때, 소스도 길어지고 문자열에서 실수할 확률이 일어난다. 
 *  그래서 Criteria를 쓴다는 얘기도 있다. 단 크리테리아는 성능을 유념해야 한다. 
 */
public class DaoStock_HQL {

	public static List getStockDailtRecord(Date startDate, Date endDate, Integer volume, Session session) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		boolean isFirst = true;

		StringBuilder query = new StringBuilder("from StockDailyRecord ");

		if (startDate != null) {
			if (isFirst) {
				query.append(" where date >= '" + sdf.format(startDate) + "'");
			} else {
				query.append(" and date >= '" + sdf.format(startDate) + "'");
			}
			isFirst = false;
		}

		if (endDate != null) {
			if (isFirst) {
				query.append(" where date <= '" + sdf.format(endDate) + "'");
			} else {
				query.append(" and date <= '" + sdf.format(endDate) + "'");
			}
			isFirst = false;
		}

		if (volume != null) {
			if (isFirst) {
				query.append(" where volume >= " + volume);
			} else {
				query.append(" and volume >= " + volume);
			}
			isFirst = false;
		}

		query.append(" order by date");
		System.out.println(query); // added by Arahansa
		Query result = session.createQuery(query.toString());

		return result.list();
	}
}
