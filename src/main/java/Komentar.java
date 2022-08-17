import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/api/komentar")

public class Komentar extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        String komentar = req.getParameter("komentar");
        String ocena = req.getParameter("ocena");
        String id = req.getParameter("id");


        JSONObject res = new JSONObject();
        if (komentar == null || ocena == null  || id == null || komentar.trim().equals("") || ocena.trim().equals("") || id.trim().equals("")) {
            res.put("res", "error");
        } else if (!komentar.trim().equals("") || !ocena.trim().equals("") || !id.trim().equals("")) {

            try {


                HttpSession session= req.getSession();
                boolean ok=session.getAttribute("username")!=null;
                if(ok){
                    long id_kor = (long) session.getAttribute("kor_id");
                    var q = Main.db.execute("INSERT INTO komentari (kom_ocena,kom_komentar,kor_id,knj_id) VALUES (?,?,?,?)",ocena,komentar,id_kor,id);

                    res.put("res", "ok");
                }else{
                    res.put("res", "error");
                }



            } catch (Exception e) {
                e.printStackTrace();
                res.put("res", "err");
            }

            try (PrintWriter out = resp.getWriter()) {
                res.write(out);
            }
        }
    }
}
