package yan.jiang.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import yan.jiang.bean.Command;

import yan.jiang.db.DBAccess;

/**
 * ��ָ����Ӧ�Ĳ�����
 * @author Yan_Jiang
 */
public class CommandDao {
	
	/*
	 * ����������ѯָ���б�
	 */
	public List<Command> queryMessageList(String name,String description) {
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		List<Command> commandList= new ArrayList<Command>();
		
		try {
			sqlSession = dbAccess.getSqlSession();
			
			//ͨ����װ����˼��  ==> ʵ�ֶԸ������Ĳ�ѯ
			Command command = new Command();
			command.setName(name);
			command.setDescription(description);
            
			//ͨ��sqlSesion����sql���
			commandList = sqlSession.selectList("Command.queryCommandList",command);
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(sqlSession!=null) {
				sqlSession.close();
			}
		}
		return commandList;
	}
}
