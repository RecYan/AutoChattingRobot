package yan.jiang.dao;

import java.util.List;
import java.util.Map;

import yan.jiang.bean.Message;

/**
 * ��Message�ļ����Ӧ�Ľӿ�
 * messageList = sqlSession.selectList("Message.queryMessageList",message);
 * 		������������ֵ�4������  nameSpace id ����Ĳ���  ���ص�����
 * 				Message  queryMessageList  message  messageList û��Լ�����ܳ��ֱ������쳣 ������������쳣
 * @author Yan_Jiang
 *
 */
// nameSpace��ͻ����    ��yan.jiang.dao.IMessage ������  �������1
public interface IMessage {
	
	/**�ӿڴ���sql���  ������� 2 4
	 * 		������  sqlSession.selectList("Message.queryMessageList",message);
	 * @param message ָ����������� �������3
	 */
	public List<Message> queryMessageList(Map<String, Object> map);
	/**
	 * ���ݲ�ѯ������ѯ��Ϣ�б������
	 */
	public int count(Message message);
	
	/**
	 * ���ݲ�ѯ������ҳ��ѯ��Ϣ�б�
	 */
	public List<Message> queryMessageListByPage(Map<String,Object> map);
}
