package net.spring.util;

import net.spring.common.Pagesize;
import net.spring.dao.UserDAO;
import net.spring.dao.impl.IUserDAO;

public class PageUtil {
	/**计算出分页数**/
	public static int PageCount(String tablename){
		if(tablename.equals("user_tbl")){
			UserDAO dao = new IUserDAO();
			int pageCount = 1;
			if(dao.getUserCount()>Pagesize.PAGESIZE){
				Double Count = (double) dao.getUserCount();
				
				pageCount = (int) Math.ceil(Count/Pagesize.PAGESIZE);
			}
			return pageCount;
		}
		return 0;
	}
}
