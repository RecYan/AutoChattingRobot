package yan.jiang.servlet;
import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
/**
 * �б�ҳ���ʼ������ѯ����
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
		//���ñ���
		req.setCharacterEncoding("utf-8");
		
		//����ҳ���ֵ
		String command = req.getParameter("command");
		String description = req.getParameter("description");
		//��ҳ���л�ȡ��ǰ��ҳ��
		String currentPage = req.getParameter("currentPage");
		
		//����ҳ�����
		Page page = new Page();
		Pattern pattern = Pattern.compile("[0-9]{1-9}"); //����ƥ��  0-9������ƥ��1-9��
		/**
		 * pattern.matcher(currentPage).matches())
		 * 		Pattern pattern = Pattern.compile("[0-9]{1-9}");
		 * 		Matcher m = pattern.matcher(currentPage);
		 * 		boolean flag = m.matchers();
		 */
		if(currentPage == null || !pattern.matcher(currentPage).matches()) {
			page.setCurrentPage(1); //ҳ��ֵΪ�� ���߲�ƥ��ʱ  ҳ��ֵ��Ϊ1
		} else {
			page.setCurrentPage(Integer.valueOf(currentPage));
		}
		
		//��ѯ��Ϣ�б�����ҳ��
		QueryService listService = new QueryService();
		req.setAttribute("messageList", listService.queryMessageListByPage(command, description, page)); 
		//��ҳ�洫ֵ
		req.setAttribute("command", command);
		req.setAttribute("description", description);
		req.setAttribute("page", page);
		
		
		
		//��ҳ����ת
		req.getRequestDispatcher("/WEB-INF/jsp/back/list.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		this.doGet(req, resp);
	}
}
