package yan.jiang.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import yan.jiang.bean.Command;
import yan.jiang.bean.CommandContent;
import yan.jiang.bean.Message;
import yan.jiang.dao.CommandDao;
import yan.jiang.dao.MessageDao;
import yan.jiang.entity.Page;
import yan.jiang.util.Iconst;

/**
 * 查询列表相关的业务功能
 * @author Yan_Jiang
 *
 */
public class QueryService {
	/**
	 * 调用 dao来查询数据
	 * @param command
	 * @param description
	 * @return 返回查询结果
	 */
	public List<Message> queryMessageList(String command,String description, Page page) {
		
		//组织消息对象
		Message message = new Message();
		message.setCommand(command);
		message.setDescription(description);
		MessageDao messageDao = new MessageDao();
		//根据条件查询总条数
		int totalNumber = messageDao.count(message);
		//组织分页查询参数
		page.setTotalNumber(totalNumber);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("message", message);
		map.put("page",page);
		
		List<Message> queryMessageList = messageDao.queryMessageList(map);
		return queryMessageList;
	}
	
	/**
	 * 根据查询条件分页查询消息列表 ==>拦截器
	 */
	public List<Message> queryMessageListByPage(String command,String description,Page page) {
		Map<String,Object> map = new HashMap<String, Object>();
		// 组织消息对象
		Message message = new Message();
		message.setCommand(command);
		message.setDescription(description);
		map.put("message", message);
		map.put("page", page);
		MessageDao messageDao = new MessageDao();
		// 分页查询并返回结果
		return messageDao.queryMessageListByPage(map);
	}
	
	/**
	 * 通过指令查询自动回复的内容
	 * @param command 指令
	 * @return 自动回复的内容
	 */
	public String queryByCommand(String command) {
		CommandDao commandDao = new CommandDao();
		List<Command> commandList;
		//出现帮助指令 则执行无条件指令检索 然后在进行拼接 
		if(Iconst.HELP_COMMAND.equals(command)) {
			//检索条件全部为空
			commandList = commandDao.queryMessageList(null, null);
			StringBuilder result = new StringBuilder();
			
			for(int i = 0; i < commandList.size(); i++) {
				//从第二行开始 <br/>标签拼接
				if(i != 0) {
					result.append("<br/>");
				}
				result.append("回复[" + commandList.get(i).getName() + "]可以查看" + commandList.get(i).getDescription());
			}
			return result.toString();
		}
		//主表列表
		commandList = commandDao.queryMessageList(command, null);
		if(commandList.size() > 0) {
			//返回指令对应的多个内容集合
			List<CommandContent> contentList = commandList.get(0).getContentList();
			//定义一个随机数索引  返回对应的内容
			int randomIndex = new Random().nextInt(contentList.size());
			return contentList.get(randomIndex).getContent();
			 
		}
		return Iconst.NO_MATCHING_CONTENT;
	}
}

