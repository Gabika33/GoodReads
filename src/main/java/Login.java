import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/api/login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        JSONObject res = new JSONObject();
        try {
            resp.setContentType("application/json");
            String username = req.getParameter("username");
            String password = req.getParameter("password");


            var q = Main.db.query("SELECT kor_id,kor_ime,kor_prezime,kor_admin FROM korisnici WHERE kor_username=? AND kor_password=?", username, password);
            if(q.size() == 0){
                res.put("res","err");
                res.put("message","Nevazece korisnicko ime ili lozinka");
            }else {
                long kor_id = (Long) q.get(0).get("kor_id");
                String ime = (String) q.get(0).get("kor_ime");
                String prezime = (String) q.get(0).get("kor_prezime");
                int kor_admin = (int) q.get(0).get("kor_admin");
                HttpSession session = req.getSession();
                session.setAttribute("username", username);
                session.setAttribute("ime",ime);
                session.setAttribute("prezime",prezime);
                session.setAttribute("kor_id", kor_id);
                session.setAttribute("kor_admin",kor_admin);
                res.put("res", "ok");
                res.put("message","Uspesno ste se ulogovali");
            }
        } catch (Exception e) {
            e.printStackTrace();
            res.put("res","err");
        }

        try (PrintWriter out = resp.getWriter()) {
            res.write(out);
        }


    }
}
