package yan.jiang.servlet;
import java.io.IOException;

import javax.servlet.ServletException;
/**
 * �б�ҳ�����
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
		//���ñ���
		req.setCharacterEncoding("utf-8");
		
		//����ҳ���ֵ
		String command = req.getParameter("command");
		String description = req.getParameter("description");
		//System.out.println(command+description);
		
		//��ҳ�洫ֵ
		/*req.setAttribute("command", command==null ? "" : command.trim() );
		req.setAttribute("description", description==null ? "" : description.trim());*/
		req.setAttribute("command", command);
		req.setAttribute("description", description);
		
		//��ѯ��Ϣ�б�����ҳ��
		QueryService listService = new QueryService();
		req.setAttribute("messageList", listService.queryMessageList(command, description)); 
		
		//��ҳ����ת
		req.getRequestDispatcher("/WEB-INF/jsp/back/list.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		this.doGet(req, resp);
	}
}
