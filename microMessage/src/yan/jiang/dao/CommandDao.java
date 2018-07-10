package yan.jiang.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import yan.jiang.bean.Command;

import yan.jiang.db.DBAccess;

/**
 * 与指令表对应的操作类
 * @author Yan_Jiang
 */
public class CommandDao {
	
	/*
	 * 根据条件查询指令列表
	 */
	public List<Command> queryMessageList(String name,String description) {
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		List<Command> commandList= new ArrayList<Command>();
		
		try {
			sqlSession = dbAccess.getSqlSession();
			
			//通过封装对象思想  ==> 实现对个参数的查询
			Command command = new Command();
			command.setName(name);
			command.setDescription(description);
            
			//通过sqlSesion操作sql语句
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
