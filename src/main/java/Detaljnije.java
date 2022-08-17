import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet("/api/detaljnije")
public class Detaljnije extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json");
        String id=req.getParameter("id");
        try(PrintWriter out= resp.getWriter()){

            var q=Main.db.query("SELECT knj_id,knj_naziv,knj_isbn,knj_opis,knj_cena,aut_naziv,izd_naziv,jez_naziv FROM knjige\n" +
                    "INNER JOIN knj_aut USING (knj_id)\n" +
                    "INNER JOIN autori USING (aut_id)\n" +
                    "LEFT JOIN izdavaci USING (izd_id)\n" +
                    "LEFT JOIN jezici USING (jez_id)\n" +
                    "LEFT JOIN knj_znr USING (knj_id)\n" +
                    "INNER JOIN zanrovi USING (znr_id) WHERE knj_id=?\n" +
                    "GROUP BY knj_id",id);



            var q2=Main.db.query("SELECT * FROM komentari WHERE knj_id = ? ",id);
            Map<String,Object> temo = q.get(0);
            temo.put("Komentari",q2);
            q.remove(0);
            q.add(0,temo);
            JSONArray o=new JSONArray(q);
           // JSONArray o2=new JSONArray(q2);
           // o.put(q2);
            o.write(out);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

