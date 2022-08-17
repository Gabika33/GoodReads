import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/api/registracija")

public class Registracija extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json");

        String ime = req.getParameter("ime");
        String prezime = req.getParameter("prezime");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String repeatpassword = req.getParameter("repeatpassword");
        String email = req.getParameter("email");

        JSONObject res = new JSONObject();
        if (!repeatpassword.equals(password)) {
            res.put("res","err");
            res.put("res","Lozinke nisu iste!");
        } else {


            try {
                var q = Main.db.execute("INSERT INTO korisnici (kor_username,kor_password,kor_ime,kor_prezime,kor_email,kor_admin) VALUES (?,?,?,?,?,?)", username, password, ime, prezime,email,0);
                res.put("res", "ok");
            } catch (Exception e) {
                res.put("res", "err");
                e.printStackTrace();
            }
        }
        try (PrintWriter out = resp.getWriter()) {
            res.write(out);


        }
    }
}