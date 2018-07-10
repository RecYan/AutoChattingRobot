package yan.jiang.bean;
/**
 * 与数据库中message表中对应的实体类
 * @author Yan_Jiang
 *
 */
public class Message {
	
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 指令名称
	 */
	private String command;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 内容
	 */
	private String content;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
