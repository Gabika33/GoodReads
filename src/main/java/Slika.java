import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/api/slika")
public class Slika extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        resp.setContentType("image");
        String id=req.getParameter("id");//ucitam sliku
        //sad moram da dobijem sliku
        try(ServletOutputStream out= resp.getOutputStream()) {
            //morsm da ucitam sliku
            var q=Main.db.query("SELECT knj_slika FROM knjige WHERE knj_id=?",id);
//vraca ga kao niz bajtova
            if(!q.isEmpty()) {
                byte[] data=(byte[])q.get(0).get("knj_slika");//vraca kao niz bajtova
                out.write(data);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
