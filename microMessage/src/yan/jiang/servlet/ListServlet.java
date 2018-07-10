package yan.jiang.servlet;
import java.io.IOException;

import javax.servlet.ServletException;
/**
 * 列表页面控制
 * @author Yan_Jiang
 */
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		//System.out.println(command+description);
		
		//向页面传值
		/*req.setAttribute("command", command==null ? "" : command.trim() );
		req.setAttribute("description", description==null ? "" : description.trim());*/
		req.setAttribute("command", command);
		req.setAttribute("description", description);
		
		//查询消息列表并传给页面
		QueryService listService = new QueryService();
		req.setAttribute("messageList", listService.queryMessageList(command, description)); 
		
		//向页面跳转
		req.getRequestDispatcher("/WEB-INF/jsp/back/list.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		this.doGet(req, resp);
	}
}
