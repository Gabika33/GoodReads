import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/api/checkUser")
public class CheckUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json");
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        String email=req.getParameter("email");

        JSONObject res=new JSONObject();
        try{
            var q=Main.db.query("SELECT kor_id FROM korisnici WHERE kor_username=? AND kor_password=? AND kor_email=?",username,password,email);
            if(q.size() == 0){
                res.put("res","err");
                res.put("message","Korisnicko ime ne postoji");

            }else{
                res.put("res","ok");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        try(PrintWriter out= resp.getWriter()){
            res.write(out);
        }
    }
}
