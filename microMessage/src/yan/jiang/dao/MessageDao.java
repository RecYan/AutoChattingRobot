package yan.jiang.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import yan.jiang.bean.Message;
import yan.jiang.db.DBAccess;

/**
 * �����ݿ���message�� ����
 * @author Yan_Jiang
 *
 */
public class MessageDao {
	/**
	 * ���ݲ�ѯ������ѯ��Ϣ�б�
	 */
	public List<Message> queryMessageList(Map<String, Object> map) {
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		List<Message> messageList= new ArrayList<Message>();
		
		try {
			sqlSession = dbAccess.getSqlSession();
			
			/*ͨ��sqlSesion����sql���
			messageList = sqlSession.selectList("Message.queryMessageList",message);*/
            //***ͨ���ӿ�ʽ��� �����������׳��ֵ����� ͨ���ӿ�������sql���***
            IMessage imessage = sqlSession.getMapper(IMessage.class);
            //�൱�� Դ���е� sqlSession.selectList(nameSpace.id,parameter) ==> ��̬����ʵ��
            messageList = imessage.queryMessageList(map);
            
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
	 * ���ݲ�ѯ������ѯ��Ϣ�б������
	 */
	public int count(Message message) {
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		int result = 0;
		try {
			sqlSession = dbAccess.getSqlSession();
			// ͨ��sqlSessionִ��SQL���
			IMessage imessage = sqlSession.getMapper(IMessage.class);
			result = imessage.count(message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(sqlSession != null) {
				sqlSession.close();
			}
		}
		return result;
	}
	
	/**
	 * ���ݲ�ѯ������ҳ��ѯ��Ϣ�б�
	 */
	public List<Message> queryMessageListByPage(Map<String,Object> map) {
		DBAccess dbAccess = new DBAccess();
		List<Message> messageList = new ArrayList<Message>();
		SqlSession sqlSession = null;
		try {
			sqlSession = dbAccess.getSqlSession();
			// ͨ��sqlSessionִ��SQL���
			IMessage imessage = sqlSession.getMapper(IMessage.class);
			messageList = imessage.queryMessageListByPage(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(sqlSession != null) {
				sqlSession.close();
			}
		}
		return messageList;
	}
	
	/**
	 * ����ɾ��
	 */
	public void deleteOne(int id) {
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		
		try {
			sqlSession = dbAccess.getSqlSession();
			
			//ͨ��sqlSesion����sql���
			sqlSession.delete("Message.deleteOne",id);
			
			//mybaits ��Ҫ�ֶ��ύ���� ��JDBC��ָ���ύ��
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
	 * ����ɾ��
	 */
	public void deleteBatch(List<Integer> ids) {
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		
		try {
			sqlSession = dbAccess.getSqlSession();
			
			//ͨ��sqlSesion����sql���
			sqlSession.delete("Message.deleteBatch",ids);
			
			//mybaits ��Ҫ�ֶ��ύ���� ��JDBC��ָ���ύ��
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
		//��װ����
		List<Message> messageList= new ArrayList<Message>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("JDBC:mysql:///micro_message","root","root");
			//ָ����������  ��߲�ѯ����
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
