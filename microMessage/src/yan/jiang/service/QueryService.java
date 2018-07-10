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
	public List<Message> queryMessageList(String command,String description) {
		
		MessageDao messageDao = new MessageDao();
		List<Message> queryMessageList = messageDao.queryMessageList(command, description);
		return queryMessageList;
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

