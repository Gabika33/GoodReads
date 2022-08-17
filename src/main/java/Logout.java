import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/api/logout")
public class Logout  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        JSONObject res = new JSONObject();

        res.put("res", "ok");
        HttpSession session = req.getSession();
        session.invalidate();

        resp.setContentType("application/json");
        try (PrintWriter out = resp.getWriter()) {
            res.write(out);
        }
    }
}
