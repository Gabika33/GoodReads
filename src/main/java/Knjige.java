import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/api/knjige")

public class Knjige extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json");
        String naziv=req.getParameter("naziv");
        try(PrintWriter out=resp.getWriter()){
            var q=Main.db.query("SELECT knj_id,knj_naziv,knj_isbn,knj_cena,knj_opis,aut_naziv,izd_naziv,jez_naziv FROM knjige\n" +
                    "INNER JOIN knj_aut USING (knj_id)\n" +
                    "INNER JOIN autori USING (aut_id)\n" +
                    "LEFT JOIN izdavaci USING (izd_id)\n" +
                    "LEFT JOIN jezici USING (jez_id)\n" +
                    "LEFT JOIN knj_znr USING (knj_id)\n" +
                    "INNER JOIN zanrovi USING (znr_id) WHERE znr_naziv=?\n" +
                    "GROUP BY knj_id",naziv);
            //dobili smo i saljemo u vidu jsona

            JSONArray o=new JSONArray(q);
            o.write(out);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
