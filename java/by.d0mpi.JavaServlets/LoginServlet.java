package by.d0mpi.JavaServlets;


import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;


interface HashAlgorithm {

    public static String getHash(String password) {
        return String.valueOf(password.hashCode());
    }



    public static boolean isHashEqual(String password, String passwordHash) {
        return passwordHash.equals(String.valueOf(password.hashCode()));
    }

}

@WebServlet("/users")


public class LoginServlet extends HttpServlet {
//Реализовать LogoutServlet для очистки HttpSession.

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter printWriter = resp.getWriter();
        Map<Integer, String> mm = new HashMap<>();
        try {
            String url = "jdbc:mysql://localhost:3306/ships?serverTimezone=Europe/Moscow&useSSL=false";
            String username = "WWW";
            String password = "Tutzizn18";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            Connection conn = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM users");

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                mm.put(resultSet.getInt("id"), HashAlgorithm.getHash(resultSet.getString("login")));
            }
            resultSet.close();

            for (Map.Entry<Integer, String> entry : mm.entrySet()) {
                PreparedStatement st = conn.prepareStatement(
                        "UPDATE users SET pwd=?  where id=?");
                st.setString(1, entry.getValue());
                st.setInt(2, entry.getKey());
                st.executeUpdate();

            }

        } catch (Exception ex) {
            printWriter.print(ex.getMessage());

        }

        printWriter.print("OK");
        //printWriter.print(HashAlgorithm.getHash("Hello"));


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter printWriter = resp.getWriter();
        String login = req.getParameter("login");
        String pwd = req.getParameter("password");

        try {
            String url = "jdbc:mysql://localhost:3306/ships?serverTimezone=Europe/Moscow&useSSL=false";
            String username = "WWW";
            String password = "Tutzizn18";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

            try (Connection conn = DriverManager.getConnection(url, username, password)) {

                PreparedStatement statement = conn.prepareStatement("SELECT * FROM users WHERE login=?");
                statement.setString(1, login);

                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    if (HashAlgorithm.isHashEqual(pwd, resultSet.getString("pwd"))) {

                        HttpSession session = req.getSession();
                        session.setAttribute("name",resultSet.getString("name"));
                        session.setAttribute("id",resultSet.getString("id"));
                    } else {
                        resp.setStatus(401);
                        return;
                    }

                } else {
                    resp.setStatus(404);
                    return;
                }
                resultSet.close();

            }
        }catch (Exception ex) {
            printWriter.print(ex.getMessage());

        }
    }
}


class SecurityFilter implements Filter {

     private FilterConfig config = null;


     public void init(FilterConfig config) throws ServletException {
         this.config = config;
     }

     @Override
     public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
         HttpServletRequest request = (HttpServletRequest) servletRequest;
         HttpServletResponse response = (HttpServletResponse) servletResponse;
         HttpSession session = request.getSession();

         if (session == null || session.getAttribute("name") == null) {
             response.sendRedirect("/signIn.jsp");
         } else {
             filterChain.doFilter(servletRequest,servletResponse);
         }

     }
         public void destroy ()
         {
             config = null;
         }
     }


