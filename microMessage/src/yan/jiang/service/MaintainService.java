package yan.jiang.service;

import java.util.ArrayList;
import java.util.List;

import yan.jiang.dao.MessageDao;

/**
 * 维护相关的业务动能
 * @author Yan_Jiang
 *
 */
public class MaintainService {
	/**
	 * 单条删除
	 * @param id 删除的主键
 	 */
	public void deleteOne(String id) {
		/**
		 * 非空 且非空格
		 * String 转 int
		 */
		if(id!=null && !"".equals(id.trim())) {
			MessageDao messageDao = new MessageDao();
			messageDao.deleteOne(Integer.valueOf(id));
		}
	}
	/**
	 * 批量删除
	 * @param ids 删除的主键集合
 	 */
	public void deleteBatch(String[] ids) {
		MessageDao messageDao = new MessageDao();
		/**
		 * service层 将String[] 转为List集合
		 */
		List<Integer> idList = new ArrayList<Integer>();
		for (String id  : ids) {
			idList.add(Integer.valueOf(id));
		}
		//体现出 service控制的作用
		messageDao.deleteBatch(idList);
	}
}
