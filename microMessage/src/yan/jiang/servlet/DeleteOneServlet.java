package yan.jiang.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yan.jiang.service.MaintainService;
/**
 * ����ɾ�����Ʋ�
 * @author Yan_Jiang
 *
 */
@SuppressWarnings("serial")
public class DeleteOneServlet extends HttpServlet {

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//���ñ���
		req.setCharacterEncoding("utf-8");
		
		//����ҳ���ֵ
		String id = req.getParameter("id");
		MaintainService maintainService = new MaintainService();
		maintainService.deleteOne(id);
		
		//��ҳ��ת����ת
		req.getRequestDispatcher("/List.action").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		this.doGet(req, resp);
	}
}
