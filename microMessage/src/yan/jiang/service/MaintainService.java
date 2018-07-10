package yan.jiang.service;

import java.util.ArrayList;
import java.util.List;

import yan.jiang.dao.MessageDao;

/**
 * ά����ص�ҵ����
 * @author Yan_Jiang
 *
 */
public class MaintainService {
	/**
	 * ����ɾ��
	 * @param id ɾ��������
 	 */
	public void deleteOne(String id) {
		/**
		 * �ǿ� �ҷǿո�
		 * String ת int
		 */
		if(id!=null && !"".equals(id.trim())) {
			MessageDao messageDao = new MessageDao();
			messageDao.deleteOne(Integer.valueOf(id));
		}
	}
	/**
	 * ����ɾ��
	 * @param ids ɾ������������
 	 */
	public void deleteBatch(String[] ids) {
		MessageDao messageDao = new MessageDao();
		/**
		 * service�� ��String[] תΪList����
		 */
		List<Integer> idList = new ArrayList<Integer>();
		for (String id  : ids) {
			idList.add(Integer.valueOf(id));
		}
		//���ֳ� service���Ƶ�����
		messageDao.deleteBatch(idList);
	}
}
