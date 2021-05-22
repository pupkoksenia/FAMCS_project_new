package by.d0mpi.JavaServlets;

import com.google.gson.Gson;

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

@WebServlet("/like")
public class LikeServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF8");
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter printWriter = resp.getWriter();
        boolean isLogin = req.getSession().getAttribute("name") != null;
        if (isLogin == false) {
            printWriter.println("unlogged");
            return;
        }

        boolean isLike = false;
        try {
            Integer fkShip = Integer.parseInt(req.getParameter("id"));
            Integer fkUser = Integer.parseInt(req.getSession().getAttribute("id").toString());

            String url = "jdbc:mysql://localhost:3306/ships?serverTimezone=Europe/Moscow&useSSL=false";
            String username = "WWW";
            String password = "Tutzizn18";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

            try (Connection conn = DriverManager.getConnection(url, username, password)) {


                PreparedStatement statement = conn.prepareStatement("SELECT fkUser FROM ships_likes where fkShip=" + fkShip +
                        " and fkUser=" + fkUser);
                ResultSet res = statement.executeQuery();



                if (res.next()) {

                    isLike=true;
                    PreparedStatement s = conn.prepareStatement(
                            "DELETE FROM  ships_likes WHERE fkShip=" + fkShip + " and fkUser=" + fkUser);
                    s.executeUpdate();


                } else {

                    PreparedStatement s = conn.prepareStatement(
                            "INSERT into ships_likes(fkUser,fkShip) values(?,?)");
                    s.setInt(2, fkShip);
                    s.setInt(1, fkUser);
                    s.executeUpdate();
                }


            }

        } catch (Exception ex) {
            printWriter.println(ex.getMessage());

        }
        printWriter.println(isLike?0:1);



        printWriter.close();
    }
}
