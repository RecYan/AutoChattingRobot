package yan.jiang.service;

import java.util.List;
import java.util.Random;

import yan.jiang.bean.Command;
import yan.jiang.bean.CommandContent;
import yan.jiang.bean.Message;
import yan.jiang.dao.CommandDao;
import yan.jiang.dao.MessageDao;
import yan.jiang.util.Iconst;

/**
 * ��ѯ�б���ص�ҵ����
 * @author Yan_Jiang
 *
 */
public class QueryService {
	/**
	 * ���� dao����ѯ����
	 * @param command
	 * @param description
	 * @return ���ز�ѯ���
	 */
	public List<Message> queryMessageList(String command,String description) {
		
		MessageDao messageDao = new MessageDao();
		List<Message> queryMessageList = messageDao.queryMessageList(command, description);
		return queryMessageList;
	}
	
	/**
	 * ͨ��ָ���ѯ�Զ��ظ�������
	 * @param command ָ��
	 * @return �Զ��ظ�������
	 */
	public String queryByCommand(String command) {
		CommandDao commandDao = new CommandDao();
		List<Command> commandList;
		//���ְ���ָ�� ��ִ��������ָ����� Ȼ���ڽ���ƴ�� 
		if(Iconst.HELP_COMMAND.equals(command)) {
			//��������ȫ��Ϊ��
			commandList = commandDao.queryMessageList(null, null);
			StringBuilder result = new StringBuilder();
			
			for(int i = 0; i < commandList.size(); i++) {
				//�ӵڶ��п�ʼ <br/>��ǩƴ��
				if(i != 0) {
					result.append("<br/>");
				}
				result.append("�ظ�[" + commandList.get(i).getName() + "]���Բ鿴" + commandList.get(i).getDescription());
			}
			return result.toString();
		}
		//�����б�
		commandList = commandDao.queryMessageList(command, null);
		if(commandList.size() > 0) {
			//����ָ���Ӧ�Ķ�����ݼ���
			List<CommandContent> contentList = commandList.get(0).getContentList();
			//����һ�����������  ���ض�Ӧ������
			int randomIndex = new Random().nextInt(contentList.size());
			return contentList.get(randomIndex).getContent();
			 
		}
		return Iconst.NO_MATCHING_CONTENT;
	}
}

