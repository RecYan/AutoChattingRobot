package yan.jiang.bean;
/**
 * �����ݿ���message���ж�Ӧ��ʵ����
 * @author Yan_Jiang
 *
 */
public class Message {
	
	/**
	 * ����
	 */
	private String id;
	/**
	 * ָ������
	 */
	private String command;
	/**
	 * ����
	 */
	private String description;
	/**
	 * ����
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
