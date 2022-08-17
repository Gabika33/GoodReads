import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/api/home")
public class Home extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json");
        HttpSession session=req.getSession();
        Object username=session.getAttribute("username");
        Object ime=session.getAttribute("ime");
        Object prezime=session.getAttribute("prezime");

        if(username == null){
            JSONObject res=new JSONObject();
            res.put("res","err");
        try(PrintWriter out=resp.getWriter()){
            res.write(out);
        }
        return;//ako nemas username
        }

        JSONObject data=new JSONObject();
        data.put("ime",ime);
        data.put("prezime",prezime);

        JSONObject res=new JSONObject();
        res.put("res","ok");
        res.put("resData",data);
        try(PrintWriter out=resp.getWriter()){
            res.write(out);
        }


    }
}
