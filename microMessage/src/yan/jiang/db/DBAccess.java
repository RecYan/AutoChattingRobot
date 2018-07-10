package yan.jiang.db;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * �������ݿ� ---- sqlSession
 * @author Yan_Jiang
 *
 */
public class DBAccess {

		public SqlSession getSqlSession() throws IOException {
		
			//��ȡ�����ļ�
			Reader reader = Resources.getResourceAsReader("yan/jiang/config/Configuration.xml");
			//ͨ��������Ϣ����SqlSessionFactory
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			//ͨ��SqlSessionFactory�����ݿ�Ự
			SqlSession sqlSession = sqlSessionFactory.openSession();
			
			return sqlSession;
			
		}
	
	
}
