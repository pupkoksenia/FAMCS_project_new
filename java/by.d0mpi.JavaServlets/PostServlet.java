package by.d0mpi.JavaServlets;

import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


@WebServlet("/tweets")
public class PostServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF8");
        resp.setContentType("application/json;charset=UTF-8");


        JSONObject obj = new JSONObject();


        try {
            int idShip = Integer.parseInt(req.getParameter("id"));
            if (idShip == 0) {
                obj.put("isAlive", false);

            } else {

                String url = "jdbc:mysql://localhost:3306/ships?serverTimezone=Europe/Moscow&useSSL=false";
                String username = "WWW";
                String password = "Tutzizn18";
                Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
                try (Connection conn = DriverManager.getConnection(url, username, password)) {
                    PreparedStatement statement = conn.prepareStatement(
                            "SELECT ships.*, users.name FROM ships inner join users on users.id=fkuser where ships.id=?");

                    statement.setInt(1, idShip);
                    ResultSet resultSet = statement.executeQuery();


                    if (resultSet.next()) {
                        obj.put("model", resultSet.getString("model"));
                        obj.put("country", resultSet.getString("country"));
                        obj.put("displacement", resultSet.getString("displacement"));
                        obj.put("price", resultSet.getString("price"));
                        obj.put("yearOfCreation", resultSet.getString("yearOfCreation"));
                        obj.put("description", resultSet.getString("description"));
                        obj.put("name", resultSet.getString("name"));
                        obj.put("photoLink", resultSet.getString("photoLink"));
                        obj.put("createdAt", resultSet.getString("createdAt"));
                        obj.put("fkuser", resultSet.getString("fkuser"));
                        obj.put("id", resultSet.getString("id"));
                        obj.put("isAlive", true);


                        //send to req
                    } else {
                        obj.put("isAlive", false);
                    }
                }
            }
        } catch (Exception ex) {
            obj.put("isAlive", false);
        }
        PrintWriter printWriter = resp.getWriter();
        printWriter.print(obj.toJSONString());
        printWriter.close();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idShip = Integer.parseInt(req.getParameter("id"));

        JSONObject obj = new JSONObject();

        if (idShip == 0) {
            obj.put("isAlive", false);

        } else {
            try {
                String url = "jdbc:mysql://localhost:3306/ships?serverTimezone=Europe/Moscow&useSSL=false";
                String username = "WWW";
                String password = "Tutzizn18";
                Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
                try (Connection conn = DriverManager.getConnection(url, username, password)) {
                    Statement statement = conn.createStatement();
                    statement.executeUpdate("DELETE from ships where id=" + idShip);
                }
            } catch (Exception ex) {
                obj.put("isAlive", false);
            }
        }

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter printWriter = res.getWriter();

        int idShip = Integer.parseInt(req.getParameter("id"));
        String modelShip = req.getParameter("model");
        String country = req.getParameter("country");
        int displacement = Integer.parseInt(req.getParameter("displacement"));
        int price = Integer.parseInt(req.getParameter("price"));
        int yearOfCreation = Integer.parseInt(req.getParameter("yearOfCreation"));
        String description = req.getParameter("description");
        String photoLink = req.getParameter("photoLink");

        try {

            String url = "jdbc:mysql://localhost:3306/ships?serverTimezone=Europe/Moscow&useSSL=false";
            String username = "WWW";
            String password = "Tutzizn18";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                if (idShip == 0) {
                    PreparedStatement ins = conn.prepareStatement(
                            "INSERT into ships(model,country,displacement,price,yearOfCreation,description,photoLink,fkuser,createdAt)  "
                                    + " values(?,?,?,?,?,?,?,?,CURDATE()); ");

                    ins.setString(1, modelShip);
                    ins.setString(2, country);
                    ins.setInt(3, displacement);
                    ins.setInt(4, price);
                    ins.setInt(5, yearOfCreation);
                    ins.setString(6, description);
                    ins.setString(7, photoLink);
                    ins.setInt(8, Integer.parseInt(req.getSession().getAttribute("id").toString()));
                    ins.executeUpdate();
                }
                else {

                    PreparedStatement statement = conn.prepareStatement(
                            "UPDATE ships SET model=?,country=?,displacement=?,price=?,yearOfCreation=?,description=?,photoLink=?,fkuser=?  where id=?");
                    statement.setString(1, modelShip);
                    statement.setString(2, country);
                    statement.setInt(3, displacement);
                    statement.setInt(4, price);
                    statement.setInt(5, yearOfCreation);
                    statement.setString(6, description);
                    statement.setString(7, photoLink);
                    statement.setInt(8, Integer.parseInt(req.getSession().getAttribute("id").toString()));
                    statement.setInt(9, idShip);

                    statement.executeUpdate();
                }

            }
        } catch (Exception ex) {
            printWriter.println("isBad " + ex.getMessage());
        }
       // printWriter.println("OKKKAY");

        printWriter.close();
    }
}
