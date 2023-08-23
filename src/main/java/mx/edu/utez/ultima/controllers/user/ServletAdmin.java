package mx.edu.utez.ultima.controllers.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.ultima.models.incidencias.DaoIncidencias;
import mx.edu.utez.ultima.models.user.DaoUser;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "ServletAdmin", urlPatterns = {"/admin/main", "/admin/delete", "/admin/logout", "/admin/activa", "/admin/inactiva"})
public class ServletAdmin extends HttpServlet {
    private Long id;
    private String action;
    private String redirect = "/admin/main";

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        action = req.getServletPath();
        switch (action) {
            case "/admin/main":
                req.setAttribute("incidencias", new DaoUser().findAll2());
                redirect = "/views/admin.jsp";
                break;
        }
        resp.sendRedirect(req.getContextPath() + redirect);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        action = req.getServletPath();
        switch (action) {
            case "/admin/activa":
                try {
                    id = Long.parseLong(req.getParameter("id"));
                    System.out.println(id);
                    if (new DaoIncidencias().active2(id)) {
                        redirect = "/api/user/admin?result=" + true
                                + "&message=" + URLEncoder.encode("Usuario activado correctamente", StandardCharsets.UTF_8);
                    } else {
                        redirect = "/api/user/admin?result=" + false
                                + "&message=" + URLEncoder.encode("Accion no realizada correctamente", StandardCharsets.UTF_8);
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                    redirect = "/api/user/admin?result=" + false
                            + "&message=" + URLEncoder.encode("Accion no realizada correctamente", StandardCharsets.UTF_8);
                }
                break;
            case "/admin/inactiva":
                try {
                    id = Long.parseLong(req.getParameter("id"));
                    System.out.println(id);
                    if (new DaoIncidencias().inactive2(id)) {
                        redirect = "/api/user/admin?result=" + true
                                + "&message=" + URLEncoder.encode("Usuario inactivo correctamente", StandardCharsets.UTF_8);
                    } else {
                        redirect = "/api/user/admin?result=" + false
                                + "&message=" + URLEncoder.encode("Accion no realizada correctamente", StandardCharsets.UTF_8);
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                    redirect = "/api/user/admin?result=" + false
                            + "&message=" + URLEncoder.encode("Accion no realizada correctamente", StandardCharsets.UTF_8);
                }
                break;
        }
        resp.sendRedirect(req.getContextPath() + redirect);
    }

}
