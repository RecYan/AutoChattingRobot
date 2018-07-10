package yan.jiang.db;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * 访问数据库 ---- sqlSession
 * @author Yan_Jiang
 *
 */
public class DBAccess {

		public SqlSession getSqlSession() throws IOException {
		
			//读取配置文件
			Reader reader = Resources.getResourceAsReader("yan/jiang/config/Configuration.xml");
			//通过配置信息构造SqlSessionFactory
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			//通过SqlSessionFactory打开数据库会话
			SqlSession sqlSession = sqlSessionFactory.openSession();
			
			return sqlSession;
			
		}
	
	
}
