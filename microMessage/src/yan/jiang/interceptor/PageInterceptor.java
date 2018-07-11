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
 * 分页拦截器
 * 		拦截时机：出现statement之前
 * 		地点： Mybatis源码
 * @author Yan_Jiang
 */
//描述拦截的具体信息
@Intercepts({@Signature(type=StatementHandler.class, method="prepare", args={Connection.class})})
public class PageInterceptor implements Interceptor {

	//2 拦截到以后 执行的业务代码
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		//拦截描述的对象
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
		// MappedStatement ==> 反射获取 使用Mybatis中封装好的类
		MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY);
		//先进入RoutingStatementHandler 然后再并入StatementHandler
		MappedStatement mappedStatement =(MappedStatement) metaObject.getValue("delegate.mappedStatement");
		//获取配置文件中sql语句的id
		String id = mappedStatement.getId();
		/**匹配ByPage结尾的ID
		 * $：字符串结尾的表示
		 * .： 前面至少出现一个字符
		 */
		if(id.matches(".+ByPage$")) {
			BoundSql boundSql = statementHandler.getBoundSql();
			//Mybatis 原始的sql执行语句
			String sql = boundSql.getSql();
			
			//2.1 获取查询的总条数  ==> 只能用原始的jdbc来完成
			String countSql = "select count(*) from ("+ sql +")a";
			//获取拦截的参数
			Connection connection = (Connection) invocation.getArgs()[0];
			PreparedStatement countStatement = connection.prepareStatement(countSql);
			//为prepareStatement的占位符 赋值
			ParameterHandler parameterHandler = (ParameterHandler) metaObject.getValue("delegate.parameterHandler");
			parameterHandler.setParameters(countStatement);
			//执行查询
			ResultSet rs = countStatement.executeQuery();
			int totalNumber = rs.getInt(1);
			
			//2.2 获取分页参数对象
			@SuppressWarnings("unchecked")
			Map<String,Object> map = (Map<String,Object>) boundSql.getParameterObject();
			Page page = (Page) map.get("page");
			if(rs.next()) {
				//设置总页数
				page.setTotalNumber(totalNumber);
			}
			
			//改造后 带有分页功能的sql语句
			String pageSql = sql + " limit " +page.getDbIndex() + "," + page.getDbNumber();
			//替换修改的sql语句  ==> 看层次图
			metaObject.setValue("delegate.boundSql.sql", pageSql);
		}
		// 源码：return method.invoke(target, args); ==>反射
		return invocation.proceed();
	}
	
	//1 判断是否需要拦截
	@Override
	public Object plugin(Object target) {
		/**target 被拦截的目标   this 本类实例
		 *  return Plugin.wrap(target, this) ==> 返回具有代理能力的分页拦截器实例
		 *  wrap(target, this) ==> 源码中判断了哪些对象需要拦截
		 */
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		// TODO Auto-generated method stub
		
	}

}
