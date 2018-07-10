package yan.jiang.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import yan.jiang.bean.Message;
import yan.jiang.db.DBAccess;

/**
 * 与数据库中message表 交互
 * @author Yan_Jiang
 *
 */
public class MessageDao {
	/*
	 * 根据条件查询数据列表
	 */
	public List<Message> queryMessageList(String command,String description) {
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		List<Message> messageList= new ArrayList<Message>();
		
		try {
			sqlSession = dbAccess.getSqlSession();
			
			//通过封装对象思想  ==> 实现对个参数的查询
			Message message = new Message();
            message.setCommand(command);
            message.setDescription(description);
            
			//通过sqlSesion操作sql语句
			messageList = sqlSession.selectList("Message.queryMessageList",message);
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(sqlSession!=null) {
				sqlSession.close();
			}
		}
		return messageList;
	}
	
	/**
	 * 单条删除
	 */
	public void deleteOne(int id) {
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		
		try {
			sqlSession = dbAccess.getSqlSession();
			
			//通过sqlSesion操作sql语句
			sqlSession.delete("Message.deleteOne",id);
			
			//mybaits 需要手动提交事务 而JDBC是指定提交的
			sqlSession.commit();
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(sqlSession!=null) {
				sqlSession.close();
			}
		}
	}
	
	/**
	 * 批量删除
	 */
	public void deleteBatch(List<Integer> ids) {
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		
		try {
			sqlSession = dbAccess.getSqlSession();
			
			//通过sqlSesion操作sql语句
			sqlSession.delete("Message.deleteBatch",ids);
			
			//mybaits 需要手动提交事务 而JDBC是指定提交的
			sqlSession.commit();
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(sqlSession!=null) {
				sqlSession.close();
			}
		}
	}
	
	/*public static void main(String[] args) {
		MessageDao messageDao = new MessageDao();
		messageDao.queryMessageList("", "");
	}*/
	
/*	
 * public List<Message> queryMessageList(String command,String description) {
		Connection conn = null;
		PreparedStatement statement = null;
		//封装数据
		List<Message> messageList= new ArrayList<Message>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("JDBC:mysql:///micro_message","root","root");
			//指定查找类型  提高查询性能
			StringBuilder sql = new StringBuilder("select ID,COMMAND,DESCRIPTION,CONTENT from message where 1=1");
			
			List<String> paramList = new ArrayList<String>();
			if(command !=null && !"".equals(command.trim())) {
				sql.append(" and command=? ");
				paramList.add(command);
			}
			if(description !=null && !"".equals(description.trim())) {
				sql.append(" and DESCRIPTION like '%' ? '%'");
				//System.out.println(sql);
				paramList.add(description);
			}
			statement = conn.prepareStatement(sql.toString());
			
			for(int i=0; i<paramList.size(); i++) {
				statement.setString(i + 1, paramList.get(i));
				//System.out.println(statement);
			}
			    
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				Message message = new Message();
				messageList.add(message);
				message.setId(rs.getString("ID"));
				message.setCommand(rs.getString("COMMAND"));
				message.setDescription(rs.getString("DESCRIPTION"));
				message.setContent(rs.getString("CONTENT"));
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return  messageList;
		
	}*/
}
