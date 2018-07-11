package yan.jiang.servlet;
import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
/**
 * 列表页面初始化及查询控制
 * @author Yan_Jiang
 */
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yan.jiang.entity.Page;
import yan.jiang.service.QueryService;

@SuppressWarnings("serial")
public class ListServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//设置编码
		req.setCharacterEncoding("utf-8");
		
		//接受页面的值
		String command = req.getParameter("command");
		String description = req.getParameter("description");
		//从页面中获取当前的页数
		String currentPage = req.getParameter("currentPage");
		
		//创建页面对象
		Page page = new Page();
		Pattern pattern = Pattern.compile("[0-9]{1-9}"); //正则匹配  0-9的数字匹配1-9次
		/**
		 * pattern.matcher(currentPage).matches())
		 * 		Pattern pattern = Pattern.compile("[0-9]{1-9}");
		 * 		Matcher m = pattern.matcher(currentPage);
		 * 		boolean flag = m.matchers();
		 */
		if(currentPage == null || !pattern.matcher(currentPage).matches()) {
			page.setCurrentPage(1); //页面值为空 或者不匹配时  页面值置为1
		} else {
			page.setCurrentPage(Integer.valueOf(currentPage));
		}
		
		//查询消息列表并传给页面
		QueryService listService = new QueryService();
		req.setAttribute("messageList", listService.queryMessageListByPage(command, description, page)); 
		//向页面传值
		req.setAttribute("command", command);
		req.setAttribute("description", description);
		req.setAttribute("page", page);
		
		
		
		//向页面跳转
		req.getRequestDispatcher("/WEB-INF/jsp/back/list.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		this.doGet(req, resp);
	}
}
