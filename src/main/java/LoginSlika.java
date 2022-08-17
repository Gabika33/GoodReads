import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/api/loginslika")
public class LoginSlika extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        resp.setContentType("image");
        String id=req.getParameter("id");

        try(ServletOutputStream out= resp.getOutputStream()) {

            var q=Main.db.query("SELECT kor_slika FROM korisnici WHERE kor_id=?",id);
            if(!q.isEmpty()) {
                byte[] data=(byte[])q.get(0).get("kor_slika");
                out.write(data);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    }

