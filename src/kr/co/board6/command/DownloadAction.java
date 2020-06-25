package kr.co.board6.command;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.dao.Board6DAO;
import kr.co.domain.Command;
import kr.co.domain.CommandAction;
import kr.co.dto.Board6DTO;

public class DownloadAction implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int num = Integer.parseInt(request.getParameter("num"));

		Board6DAO dao = new Board6DAO();
		Board6DTO article = dao.read(num);

		String filename = article.getFilename();
		ServletContext context = request.getSession().getServletContext();
		String uploadFileName = context.getRealPath("/upload") + "/" + filename;
		File downFile = new File(uploadFileName);
		if (downFile.exists() && downFile.isFile()) {

			try {

				long filesize = downFile.length();

				response.setContentType("application/x-msdownload");
				response.setContentLength((int) filesize);
				String strClient = request.getHeader("user-agent");
				filename = new String(filename.getBytes(), "8859_1");
				if (strClient.indexOf("MSIE 5.5") != -1) {
					response.setHeader("Content-Disposition", "filename=" + filename + ";");
				} else {
					response.setHeader("Content-Disposition", "attachment; filename=" + filename + ";");
				}
				response.setHeader("Content-Length", String.valueOf(filesize));
				response.setHeader("Content-Transfer-Encoding", "binary;");
				response.setHeader("Pragma", "no-cache");
				response.setHeader("Cache-Control", "private");

				byte b[] = new byte[1024];

				BufferedInputStream fin = new BufferedInputStream(new FileInputStream(downFile));
				BufferedOutputStream outs = new BufferedOutputStream(response.getOutputStream());

				int read = 0;

				while ((read = fin.read(b)) != -1) {
					outs.write(b, 0, read);
				}
				outs.flush();
				outs.close();
				fin.close();
			} catch (Exception e) {
				System.out.println("Download Exception : " + e.getMessage());
			}
		} else {
			System.out.println("Download Error : downFile Error [" + downFile + "]");
		}
		return new CommandAction(false, null);

	}

}
