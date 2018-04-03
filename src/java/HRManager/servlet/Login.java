/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package HRManager.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import HRManager.entities.User;
import HRManager.bol.UserBO;
import javax.servlet.RequestDispatcher;

public class Login extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = request.getParameter("txtUserName");
        String userPassword = request.getParameter("txtPassword");
        HRManager.ValidData valid = new HRManager.ValidData();
        if (userName.equals("")) {
            request.setAttribute("errorUname", "Username is required you fool");
            goPage(request, response, "login.jsp");
        } else if (userPassword.equals("")) {
            request.setAttribute("errorPasword", "Password is required you fool");
            goPage(request, response, "login.jsp");
        } else {
            User u = new User();
            UserBO ubo = new UserBO();
            u.setUserName(userName);
            u.setUserPassword(userPassword);
            if (ubo.authorization(u)) {
                HttpSession session = request.getSession();
                session.setAttribute("username", u.getUserName());
                goPage(request, response, "EmployeeManager");
            } else {
                goPage(request, response, "login.jsp");
            }
        }
    }

    private void goPage(HttpServletRequest request, HttpServletResponse response, String link)
            throws ServletException, IOException {
        RequestDispatcher reqdis = request.getRequestDispatcher(link);
        reqdis.forward(request, response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
