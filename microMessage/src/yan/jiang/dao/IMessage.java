package yan.jiang.dao;

import java.util.List;
import java.util.Map;

import yan.jiang.bean.Message;

/**
 * 与Message文件相对应的接口
 * messageList = sqlSession.selectList("Message.queryMessageList",message);
 * 		规避上面语句出现的4个风险  nameSpace id 传入的参数  返回的类型
 * 				Message  queryMessageList  message  messageList 没有约束可能出现编译无异常 而运行则出现异常
 * @author Yan_Jiang
 *
 */
// nameSpace冲突问题    用yan.jiang.dao.IMessage 来代替  解决问题1
public interface IMessage {
	
	/**接口代言sql语句  解决问题 2 4
	 * 		代言了  sqlSession.selectList("Message.queryMessageList",message);
	 * @param message 指定传入的类型 解决问题3
	 */
	public List<Message> queryMessageList(Map<String, Object> map);
	/**
	 * 根据查询条件查询消息列表的条数
	 */
	public int count(Message message);
	
	/**
	 * 根据查询条件分页查询消息列表
	 */
	public List<Message> queryMessageListByPage(Map<String,Object> map);
}
