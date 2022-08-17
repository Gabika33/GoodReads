import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet("/api/profil")
@MultipartConfig()
public class Profil  extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        JSONObject res=new JSONObject();

        HttpSession session= req.getSession();
        boolean ok=session.getAttribute("username")!=null;
        res.put("res",ok?"ok":"err");

        if (ok) {
            long id = (long) session.getAttribute("kor_id");
            try {
                var q = Main.db.query("SELECT kor_ime,kor_prezime,kor_username,kor_email, kor_password,kor_id FROM korisnici WHERE kor_id=?", id);

                Map<String, Object> asd = q.get(0);
                res.put("id",asd.get("kor_id"));
                res.put("username",asd.get("kor_username"));
                res.put("ime",asd.get("kor_ime"));
                res.put("prezime",asd.get("kor_prezime"));
                res.put("email",asd.get("kor_email"));
                res.put("pass",asd.get("kor_password"));


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        resp.setContentType("application/json");
        try(PrintWriter out = resp.getWriter()){
            res.write(out);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("application/json");
        JSONObject res = new JSONObject();
        List<Map<String, Object>> q = null;

        HttpSession session = req.getSession();
        Object username = session.getAttribute("username");

        if (username == null) {
            res.put("res", "err");
        } else {

            try {
                long id = (long) session.getAttribute("kor_id");
                String user = req.getParameter("user");
                String password = req.getParameter("password");
                String ime = req.getParameter("ime");
                String prezime = req.getParameter("prezime");
                String email = req.getParameter("email");
                Part part=req.getPart("slika");
                byte[] slika=part.getInputStream().readAllBytes();

                List<Integer> qo1 = Main.db.execute("UPDATE korisnici SET kor_username= ?,kor_password=?,kor_ime=?,kor_prezime=?,kor_email=?,kor_slika=? WHERE kor_id=?",user,password,ime,prezime,email,slika,String.valueOf(id));
                res.put("res", "ok");

            } catch (Exception e) {
                System.out.println(e.getMessage());
                res.put("res", "Erorrr");
                res.put("Pocetna", "Greska u API-ju" + e.getMessage());
            }
        }
        try (PrintWriter out = resp.getWriter()) {
            res.write(out);
        }
    }
}