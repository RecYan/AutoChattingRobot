package yan.jiang.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yan.jiang.service.QueryService;



/**
 * �Զ��ظ����ܿ��Ʋ�
 * 	  ���Ajax����
 */
@SuppressWarnings("serial")
public class AutoReplyServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//���ñ���
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		QueryService queryService = new QueryService();
		//����ָ���ѯ �ٽ���ѯ������������ ��ʾ��ҳ����
		out.write(queryService.queryByCommand(req.getParameter("content")));
		out.flush();
		out.close();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
