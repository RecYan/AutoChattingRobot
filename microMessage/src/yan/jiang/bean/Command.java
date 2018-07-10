package yan.jiang.bean;

import java.util.List;

/**
 * ��ָ����Ӧ��ʵ����---����
 */
public class Command {
	
	/**
	 * ����
	 */
	private String id;
	/**
	 * ָ������
	 */
	private String name;
	/**
	 * ����
	 */
	private String description;
	/**
	 * һ��ָ���Ӧ���Զ��ظ������б�
	 */
	private List<CommandContent> contentList;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<CommandContent> getContentList() {
		return contentList;
	}
	public void setContentList(List<CommandContent> contentList) {
		this.contentList = contentList;
	}
}
