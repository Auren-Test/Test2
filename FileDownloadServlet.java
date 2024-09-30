import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class FileDownloadServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filename = request.getParameter("file");
        String filePath = "/var/www/uploads/" + filename;

        File file = new File(filePath);
        if (file.exists()) {
            FileInputStream fileInputStream = new FileInputStream(file);
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

            int i;
            while ((i = fileInputStream.read()) != -1) {
                response.getWriter().write(i);
            }

            fileInputStream.close();
        } else {
            response.getWriter().println("File not found!");
        }
    }
}
