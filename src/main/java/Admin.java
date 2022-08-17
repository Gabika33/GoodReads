import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import static java.lang.String.valueOf;

@WebServlet("/api/admin")
public class Admin extends HttpServlet {

    public JSONArray lista_json_string(List<Map<String, Object>> lista) {
        JSONArray niz = new JSONArray(); // lista je isto sto i json array
        for (Map<String, Object> map : lista) {
            JSONObject jedan = new JSONObject();
            for (Map.Entry<String, Object> vrednost : map.entrySet()) {
                String key = vrednost.getKey();
                Object value = vrednost.getValue();
                try {
                    jedan.put(key,value);
                } catch (JSONException e) {
                    System.out.println(e.getMessage());
                }
            }
            niz.put(jedan);
        }
        return niz;
    }

    @SuppressWarnings("unused")
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {

    resp.setContentType("application/json");
    JSONObject res=new JSONObject();
    List<Map<String,Object>> q=null;

    HttpSession session=req.getSession();
    Object username=session.getAttribute("username");

    if(username == null){
        res.put("res","err");
    }else {
       /* Enumeration parametar = req.getParameterNames();
        while (parametar.hasMoreElements()) {
            Object asd = parametar.nextElement();
            System.out.println(asd);
        }*/
        // metoda za pregledanje prosledjenih elemenata
        try {
            if (req.getParameterMap().containsKey("com")) {
                String provera = req.getParameter("com");
                if (!provera.isEmpty()) {
                    if (provera.equals("d")) {
                        // System.out.println("usla" + provera);
                        //  System.out.println("DELETE FROM " + req.getParameter("odakle") + " WHERE id = " + req.getParameter("id"));
                        String params = req.getParameter("odakle");

                        if (params.equals("korisnici")) {
                            String id = req.getParameter("id");
                            List<Integer> qo = Main.db.execute("DELETE FROM " + params + " WHERE kor_id= " + req.getParameter("id"));
                        }

                        if (params.equals("komentari")) {
                            String id = req.getParameter("id");
                            List<Integer> qo = Main.db.execute("DELETE FROM " + params + " WHERE kom_id= " + req.getParameter("id"));
                        }
                        if (params.equals("knjige")) {
                            String id = req.getParameter("id");
                            List<Integer> prvi = Main.db.execute("DELETE FROM knj_aut WHERE knj_id= " + req.getParameter("id"));
                            List<Integer> drugi = Main.db.execute("DELETE FROM knj_znr WHERE knj_id= " + req.getParameter("id"));
                            List<Integer> qo = Main.db.execute("DELETE FROM " + params + " WHERE knj_id= " + req.getParameter("id"));

                        }
                        if (params.equals("autori")) {
                            String id = req.getParameter("id");
                            List<Integer> qo = Main.db.execute("DELETE FROM " + params + " WHERE aut_id= " + req.getParameter("id"));
                        }
                        if (params.equals("jezici")) {
                            String id = req.getParameter("id");
                            List<Integer> qo = Main.db.execute("DELETE FROM " + params + " WHERE jez_id= " + req.getParameter("id"));
                        }
                        if (params.equals("izdavaci")) {
                            String id = req.getParameter("id");
                             List<Integer> qo = Main.db.execute("DELETE FROM " + params + " WHERE izd_id= " + req.getParameter("id"));
                        }
                        if (params.equals("zanrovi")) {
                            String id = req.getParameter("id");
                            List<Integer> qo = Main.db.execute("DELETE FROM " + params + " WHERE znr_id= " + req.getParameter("id"));
                        }
                        resp.sendRedirect("/admin");
                        return;
                    } else if (provera.equals("i")) {
                        System.out.println("usla" + provera);

                        String params = req.getParameter("odakle");

                        if (params.equals("knjige")) {
                            String naziv = req.getParameter("naziv");
                            String isbn = req.getParameter("isbn");
                            String opis = req.getParameter("opis");
                            String slika = req.getParameter("slika");
                            String cena = req.getParameter("cena");
                            String IDizdavac = req.getParameter("IDizdavac");
                            String zanr = req.getParameter("zanr"); // Stari podaci (bio bi prosledjen samo jedan id) : 2 // Trenutno se salje vise podataka istovremeno : 2,3,7
                            String autor = req.getParameter("autor");// Stari podaci (bio bi prosledjen samo jedan id) : 2 // -||-
                            String IDjezik = req.getParameter("IDjezik");

                            List<Integer> qo = Main.db.execute("INSERT INTO knjige (knj_naziv,knj_isbn,knj_opis,knj_cena,jez_id,izd_id) VALUES('" + naziv + "','" + isbn + "','" + opis + "','" + cena + "','" + IDjezik + "','" + IDizdavac + "')");
                            var e = Main.db.query("SELECT knj_id FROM knjige WHERE knj_naziv = ? AND knj_isbn = ?", naziv,isbn);

                            if (e.size() > 0) {
                                long kor_id = (Long) e.get(0).get("knj_id");
                                System.out.println(kor_id + "NASAO");
                                String[] zanrRazbijeno = zanr.split(",");
                                for (String s: zanrRazbijeno) {
                                    List<Integer> qo2 = Main.db.execute("INSERT INTO knj_znr (knj_id,znr_id) VALUES(?, ?)", kor_id,s );
                                }
                                String[] autoriRazbijeno = autor.split(",");
                                for (String s: autoriRazbijeno) {
                                    List<Integer> qo1 = Main.db.execute("INSERT INTO knj_aut (knj_id,aut_id) VALUES(?, ?)", kor_id,s );
                                }

                            }
                            res.put("res", "ok");

                        } else if (params.equals("autori")) {
                            String naziv = req.getParameter("naziv");
                            List<Integer> qo = Main.db.execute("INSERT INTO autori (aut_naziv) VALUES ('" + naziv + "')");
                            res.put("res", "ok");

                        } else if (params.equals("zanrovi")) {
                            String naziv = req.getParameter("naziv");
                            List<Integer> qo = Main.db.execute("INSERT INTO zanrovi (znr_naziv) VALUES ('" + naziv + "')");
                            res.put("res", "ok");

                        } else if (params.equals("izdavaci")) {

                            String naziv = req.getParameter("naziv");
                            List<Integer> qo = Main.db.execute("INSERT INTO izdavaci (izd_naziv) VALUES ('" + naziv + "')");
                            res.put("res", "ok");

                        } else if (params.equals("jezici")) {
                            String naziv = req.getParameter("naziv");
                            List<Integer> qo = Main.db.execute("INSERT INTO jezici (jez_naziv) VALUES ('" + naziv + "')");
                            res.put("res", "ok");

                        } else if (params.equals("komentari")) {
                            String komentar = req.getParameter("komentar");
                            String ocena = req.getParameter("ocena");
                            String IDkorisnika = req.getParameter("IDkorisnika");
                            String IDknjige = req.getParameter("IDknjige");
                            List<Integer> qo = Main.db.execute("INSERT INTO komentari (kom_komentar,kom_ocena,kor_id,knj_id) VALUES ('" + komentar + "','" + ocena + "','" + IDkorisnika + "','" + IDknjige + "')");
                            res.put("res", "ok");

                        } else if (params.equals("korisnici")) {
                            String user = req.getParameter("user");
                            String password = req.getParameter("password");
                            String ime = req.getParameter("ime");
                            String prezime = req.getParameter("prezime");
                            String email = req.getParameter("email");
                            String slika= req.getParameter("slika");
                            String tip = req.getParameter("tip");
                            List<Integer> qo = Main.db.execute("INSERT INTO korisnici (kor_username,kor_password,kor_ime,kor_prezime,kor_email,kor_slika,kor_admin) VALUES ('" + user + "','" + password + "','" + ime + "','" + prezime + "','" + email + "','" + slika + "','" + tip + "')");
                            res.put("res", "ok");
                        }


                        try (PrintWriter out = resp.getWriter()) {
                            res.write(out);
                        }
                        resp.sendRedirect("/admin");//da ode na drugu stranicu
                        return;

                    }else if (provera.equals("u")) {
                        String params = req.getParameter("odakle");

                        if (params.equals("knjige")) {
                            String id = req.getParameter("id");
                            String naziv = req.getParameter("naziv");
                            String isbn = req.getParameter("isbn");
                            String opis = req.getParameter("opis");
                            String slika = req.getParameter("slika");
                            String cena = req.getParameter("cena");
                            String zanr = req.getParameter("zanr");
                            String autor = req.getParameter("autor");
                            String IDizdavac =req.getParameter("IDizdavac");
                            String IDjezik = req.getParameter("IDjezik");
                            List<Integer> qo = Main.db.execute("UPDATE knjige SET knj_naziv='" + naziv + "',knj_isbn='" + isbn + "',knj_opis='" + opis + "',knj_cena='" + cena + "',jez_id='" + IDjezik + "',izd_id='" + IDizdavac + "' WHERE knj_id='" + id + "'");

                            q = Main.db.query("SELECT knj_id FROM knjige WHERE knj_naziv = ?", naziv);

                           if (q.size() > 0) {
                               long kor_id = (Long) q.get(0).get("knj_id");
                               List<Integer> prvi = Main.db.execute("DELETE FROM knj_aut WHERE knj_id= " + kor_id);
                               List<Integer> drugi = Main.db.execute("DELETE FROM knj_znr WHERE knj_id= " + kor_id);
                               String[] zanrRazbijeno = zanr.split(",");
                               for (String s: zanrRazbijeno) {
                                   List<Integer> qo2 = Main.db.execute("INSERT INTO knj_znr (knj_id,znr_id) VALUES(?, ?)", valueOf(kor_id),s );
                               }
                               String[] autoriRazbijeno = autor.split(",");
                               for (String s: autoriRazbijeno) {
                                   List<Integer> qo1 = Main.db.execute("INSERT INTO knj_aut (knj_id,aut_id) VALUES(?, ?)", valueOf(kor_id),s );
                               }

                               res.put("res", "ok");
                           }
                        } else if (params.equals("autori")) {
                           String id = req.getParameter("id");
                            String naziv = req.getParameter("naziv");
                            List<Integer> qo = Main.db.execute("UPDATE autori SET aut_naziv='" + naziv + "' WHERE aut_id='" + id + "'");
                            res.put("res", "ok");

                        } else if (params.equals("zanrovi")) {
                           String id = req.getParameter("id");
                            String naziv = req.getParameter("naziv");
                            List<Integer> qo = Main.db.execute("UPDATE zanrovi SET znr_naziv='" + naziv + "' WHERE znr_id='" + id + "'");
                            res.put("res", "ok");

                        } else if (params.equals("izdavaci")) {
                            String id=req.getParameter("id");
                            String naziv = req.getParameter("naziv");
                            List<Integer> qo = Main.db.execute("UPDATE izdavaci SET izd_naziv='" + naziv + "' WHERE izd_id='" + id + "'");
                            res.put("res", "ok");

                        } else if (params.equals("jezici")) {
                           String id=req.getParameter("id");
                            String naziv = req.getParameter("naziv");
                            List<Integer> qo = Main.db.execute("UPDATE jezici SET jez_naziv='" + naziv + "' WHERE jez_id='" + id + "'");
                            res.put("res", "ok");

                        } else if (params.equals("komentari")) {
                            String id=req.getParameter("id");
                            String komentar = req.getParameter("komentar");
                            String ocena = req.getParameter("ocena");
                            String IDkorisnika = req.getParameter("IDkorisnika");
                            String IDknjige = req.getParameter("IDknjige");
                            List<Integer> qo = Main.db.execute("UPDATE komentari SET kom_komentar='" + komentar + "',kom_ocena='" + ocena + "',kor_id='" + IDkorisnika + "',knj_id='" + IDknjige + "' WHERE kom_id='" + id + "'");
                            res.put("res", "ok");

                        } else if (params.equals("korisnici")) {
                            String id = req.getParameter("id");
                            String user = req.getParameter("user");
                            String password = req.getParameter("password");
                            String ime = req.getParameter("ime");
                            String prezime = req.getParameter("prezime");
                            String email = req.getParameter("email");
                            String slika = req.getParameter("slika");
                            String tip = req.getParameter("tip");
                            List<Integer> qo = Main.db.execute("UPDATE korisnici SET kor_username='" + user + "',kor_password='" + password + "',kor_ime='" + ime + "',kor_prezime='" + prezime + "',kor_admin='" + tip + "',kor_email='" + email + "',kor_slika='" + slika + "' WHERE kor_id='" + id + "'");
                            res.put("res", "ok");
                        }

                    }
                    try (PrintWriter out = resp.getWriter()) {
                        res.write(out);
                    }
                    return;

                }
            }
                res.put("res", "ok");
                res.put("pocetnaaa", "Sve ucitano");

                q = Main.db.query("SELECT * FROM jezici");
                res.put("jezici", lista_json_string(q));

                q = Main.db.query("SELECT * FROM autori");
                res.put("autori", lista_json_string(q));

                q = Main.db.query("SELECT * FROM zanrovi");
                res.put("zanrovi", lista_json_string(q));

                q = Main.db.query("SELECT kom_id,kom_komentar,kom_ocena,kor_id,knj_id FROM komentari \n" +
                        "LEFT JOIN korisnici USING (kor_id) \n" +
                        "LEFT JOIN knjige USING (knj_id)");
                res.put("komentari", lista_json_string(q));

                q = Main.db.query("SELECT * FROM izdavaci");
                res.put("izdavaci", lista_json_string(q));

                q = Main.db.query("SELECT kor_id,kor_username,kor_password,kor_ime,kor_prezime,kor_email,kor_admin FROM korisnici");
                res.put("korisnici", lista_json_string(q));

                q = Main.db.query("SELECT knj_id,knj_naziv,knj_isbn,knj_cena,knj_opis,aut_naziv,izd_naziv,jez_naziv FROM knjige \n" +
                        "INNER JOIN knj_aut USING (knj_id)\n" +
                        "INNER JOIN autori USING (aut_id)\n" +
                        "LEFT JOIN izdavaci USING (izd_id)\n" +
                        "LEFT JOIN jezici USING (jez_id)\n" +
                        "LEFT JOIN knj_znr USING (knj_id)\n" +
                        "INNER JOIN zanrovi USING (znr_id)\n" +
                        "GROUP BY knj_id");
                res.put("knjige", lista_json_string(q));

                q = Main.db.query("SELECT * FROM omiljene");
                 res.put("omiljene", lista_json_string(q));

            } catch (Exception e) {
         res.put("res","Erorrr");
         res.put("Pocetna","Greska u API-ju" + e.getMessage());
      }
    }
        System.out.println("finale" + res);
    try(PrintWriter out=resp.getWriter()){
        res.write(out);
    }

    }
            }
