

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/api/upload")

@MultipartConfig()//da primimo podatke

public class Upload extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username=req.getParameter("username");
        String password=req.getParameter("password");

        Part part=req.getPart("file");
        resp.setContentType("application/json");
        JSONObject res=new JSONObject();
        if(part!=null) {
            String fileName=part.getSubmittedFileName();
            byte[] data=part.getInputStream().readAllBytes();
            try {
                Main.db.execute("INSERT INTO korisnici(kor_slika,kor_username,kor_password) VALUES(?,?,?)",data,username,password);
                res.put("res","OK");
            } catch (SQLException e) {
                res.put("res","ERR");
                res.put("message","Database error");
                e.printStackTrace();
            }
        } else {
            res.put("res","ERR");
        }
        try(PrintWriter out= resp.getWriter()){
            res.write(out);



        }
    }
}

