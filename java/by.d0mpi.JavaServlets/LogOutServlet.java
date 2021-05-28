package by.d0mpi.JavaServlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet("/logout")
public class LogOutServlet extends HttpServlet  {


        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            HttpSession session = req.getSession();
            session.removeAttribute("name");
            session.removeAttribute("id");
            //session.invalidate();
            PrintWriter printWriter = resp.getWriter();

            printWriter.println("isBad ");
        }
    }

