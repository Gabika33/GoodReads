import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.JSONArray;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/api/zanrovi")
public class Zanrovi extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json");
        try(PrintWriter out=resp.getWriter()){
            var q=Main.db.query("SELECT * FROM zanrovi");
            JSONArray o=new JSONArray(q);
             o.write(out);
        }catch(Exception e){
            e.printStackTrace();

        }
    }
}
