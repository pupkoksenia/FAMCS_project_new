package by.d0mpi.JavaServlets;

import com.google.gson.Gson;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/listPost")
public class ListPost extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF8");
        resp.setContentType("application/json;charset=UTF-8");

        Integer fkUser =0;
        if(req.getSession().getAttribute("id")!=null){
            fkUser=  Integer.parseInt(req.getSession().getAttribute("id").toString());
        }
        List<JSONObject> listJS = new ArrayList<>();
        try {

            int start = 0;
            start = Integer.parseInt(req.getParameter("start"));

            String url = "jdbc:mysql://localhost:3306/ships?serverTimezone=Europe/Moscow&useSSL=false";
            String username = "WWW";
            String password = "Tutzizn18";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                String query = "";

                if (req.getParameter("name").length() > 0)
                    query += "users.name like '%" + req.getParameter("name") + "%' and ";
                if (req.getParameter("model").length() > 0)
                    query += "model like '%" + req.getParameter("model") + "%' and ";
                if (req.getParameter("country").length() > 0)
                    query += "country like '%" + req.getParameter("country") + "%' and ";
                if (req.getParameter("displacement").length() > 0)
                    query += "displacement <= " + req.getParameter("displacement") +" and ";
                if (req.getParameter("price").length() > 0)
                    query += "price <= " + req.getParameter("price") +" and ";


                PreparedStatement statement = conn.prepareStatement(

                        "SELECT ships.*, (SELECT count(fkShip) from ships_likes WHERE fkUser="+fkUser+" and fkShip=ships.id) as lk," +
                                " users.name FROM ships inner join users on users.id=fkuser " +
                                (query.length() > 0 ? " where " + query.substring(0, query.length() - 5) : " ")
                                + " limit 10 offset " + start);


                ResultSet resultSet = statement.executeQuery();


                while (resultSet.next()) {
                    JSONObject obj = new JSONObject();
                    listJS.add(obj);
                    obj.put("model", resultSet.getString("model"));
                    obj.put("country", resultSet.getString("country"));
                    obj.put("displacement", resultSet.getString("displacement"));
                    obj.put("price", resultSet.getString("price"));
                    obj.put("yearOfCreation", resultSet.getString("yearOfCreation"));
                    obj.put("description", resultSet.getString("description"));
                    obj.put("name", resultSet.getString("name"));
                    obj.put("photoLink", resultSet.getString("photoLink"));
                    obj.put("createdAt", resultSet.getString("createdAt"));
                    obj.put("isAlive", true);
                    obj.put("fkuser", resultSet.getString("fkuser"));
                    obj.put("id", resultSet.getString("id"));
                    obj.put("lk", resultSet.getString("lk"));
                    //send to req
                }
            }

        } catch (Exception ex) {
            JSONObject obj = new JSONObject();
            obj.put("error", ex.getMessage());
            listJS.add(obj);

            // obj.put("isAlive", false);
        }
        PrintWriter printWriter = resp.getWriter();
        String json = new Gson().toJson(listJS);
        printWriter.print(json);
        printWriter.close();
    }

}
