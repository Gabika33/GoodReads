import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/api/dodajOmiljene")
public class DodajOmiljene extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        JSONObject res=new JSONObject();
        String knjigaId = req.getParameter("id");
        HttpSession session= req.getSession();
        boolean ok=session.getAttribute("kor_id")!=null;
        if (ok) {
            long korisnikId = (long)session.getAttribute("kor_id");
            try {
                List<Integer> qo = Main.db.execute("INSERT INTO omiljene (knj_id,kor_id) VALUES(?,?)", knjigaId, String.valueOf(korisnikId));
                res.put("res", "ok");
            } catch (SQLException throwables) {
                res.put("res", "err");
                throwables.printStackTrace();
            }

        }else{
            res.put("res", "err");
        }

        resp.setContentType("application/json");
        try(PrintWriter out = resp.getWriter()){
            res.write(out);
        }
    }
}
