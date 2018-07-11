package yan.jiang.interceptor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import com.mysql.jdbc.Connection;

import yan.jiang.entity.Page;

/**
 * ��ҳ������
 * 		����ʱ��������statement֮ǰ
 * 		�ص㣺 MybatisԴ��
 * @author Yan_Jiang
 */
//�������صľ�����Ϣ
@Intercepts({@Signature(type=StatementHandler.class, method="prepare", args={Connection.class})})
public class PageInterceptor implements Interceptor {

	//2 ���ص��Ժ� ִ�е�ҵ�����
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		//���������Ķ���
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
		// MappedStatement ==> �����ȡ ʹ��Mybatis�з�װ�õ���
		MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY);
		//�Ƚ���RoutingStatementHandler Ȼ���ٲ���StatementHandler
		MappedStatement mappedStatement =(MappedStatement) metaObject.getValue("delegate.mappedStatement");
		//��ȡ�����ļ���sql����id
		String id = mappedStatement.getId();
		/**ƥ��ByPage��β��ID
		 * $���ַ�����β�ı�ʾ
		 * .�� ǰ�����ٳ���һ���ַ�
		 */
		if(id.matches(".+ByPage$")) {
			BoundSql boundSql = statementHandler.getBoundSql();
			//Mybatis ԭʼ��sqlִ�����
			String sql = boundSql.getSql();
			
			//2.1 ��ȡ��ѯ��������  ==> ֻ����ԭʼ��jdbc�����
			String countSql = "select count(*) from ("+ sql +")a";
			//��ȡ���صĲ���
			Connection connection = (Connection) invocation.getArgs()[0];
			PreparedStatement countStatement = connection.prepareStatement(countSql);
			//ΪprepareStatement��ռλ�� ��ֵ
			ParameterHandler parameterHandler = (ParameterHandler) metaObject.getValue("delegate.parameterHandler");
			parameterHandler.setParameters(countStatement);
			//ִ�в�ѯ
			ResultSet rs = countStatement.executeQuery();
			int totalNumber = rs.getInt(1);
			
			//2.2 ��ȡ��ҳ��������
			@SuppressWarnings("unchecked")
			Map<String,Object> map = (Map<String,Object>) boundSql.getParameterObject();
			Page page = (Page) map.get("page");
			if(rs.next()) {
				//������ҳ��
				page.setTotalNumber(totalNumber);
			}
			
			//����� ���з�ҳ���ܵ�sql���
			String pageSql = sql + " limit " +page.getDbIndex() + "," + page.getDbNumber();
			//�滻�޸ĵ�sql���  ==> �����ͼ
			metaObject.setValue("delegate.boundSql.sql", pageSql);
		}
		// Դ�룺return method.invoke(target, args); ==>����
		return invocation.proceed();
	}
	
	//1 �ж��Ƿ���Ҫ����
	@Override
	public Object plugin(Object target) {
		/**target �����ص�Ŀ��   this ����ʵ��
		 *  return Plugin.wrap(target, this) ==> ���ؾ��д��������ķ�ҳ������ʵ��
		 *  wrap(target, this) ==> Դ�����ж�����Щ������Ҫ����
		 */
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		// TODO Auto-generated method stub
		
	}

}
