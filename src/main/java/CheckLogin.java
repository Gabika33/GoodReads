import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/api/checkLogin")
public class CheckLogin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        JSONObject res=new JSONObject();

        HttpSession session= req.getSession();
        boolean ok=session.getAttribute("username")!=null;
        res.put("adminc",session.getAttribute("kor_admin") != null? session.getAttribute("kor_admin") : "err");
        res.put("res",ok?"ok":"err");

        resp.setContentType("application/json");
        try(PrintWriter out = resp.getWriter()){
            res.write(out);
        }
    }
}
