package mx.edu.utez.ultima.controllers.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.ultima.models.incidencias.DaoIncidencias;
import mx.edu.utez.ultima.models.incidencias.Incidencias;
import mx.edu.utez.ultima.models.user.User;


import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet(name = "incidencia",
        urlPatterns = {
                "/incidencia/incidencias",
                "/incidencia/incidencia",
                "/incidencia/save",
                "/incidencia/accept",
                "/incidencia/decline",
                "/incidencia/update",
                "/incidencia/view",
                "/incidencia/view-create",
                "/incidencia/enable",
        })
public class ServletIncidencias extends HttpServlet {
    private String action;
    private String redirect = "/incidencia/incidencias";
    private String id, title,description,tipo, status, incidencia_id;
    private Incidencias incidencias;
    private User user;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        action = req.getServletPath();
        switch (action) {
            case "/incidencia/incidencias":
                List<Incidencias> incidencias = new DaoIncidencias().findAll();
                req.setAttribute("incidencias", incidencias);
                redirect = "/views/user.jsp";
                break;
            case "/incidencia/view-create":
                redirect = "/views/create.jsp";
                break;

            default:
                System.out.println(action);
        }
        req.getRequestDispatcher(redirect).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        action = req.getServletPath();
        switch (action) {
            case "/incidencia/save":
                title = req.getParameter("titulo");
                description = req.getParameter("descripcion");
                tipo = req.getParameter("tipo");
                incidencias = new Incidencias(0L, title, description, tipo, "PENDIENTE");
                boolean result = new DaoIncidencias().save(incidencias);
                if (result)
                    redirect = "/incidencia/incidencias?result= " + true + "&message=" + URLEncoder.encode("¡Éxito! registrado correctamente.", StandardCharsets.UTF_8);
                else
                    redirect = "/incidencia/incidencias?result= " + false + "&message=" + URLEncoder.encode("¡Error! Acción no realizada correctamente.", StandardCharsets.UTF_8);
                break;

            case "/incidencia/delete":
                id = req.getParameter("id");
                if (new DaoIncidencias().delete(Long.parseLong(id)))
                    redirect = "/incidencia/incidencia?result= " + true + "&message="
                            + URLEncoder.encode("¡Éxito! eliminado correctamente.",
                            StandardCharsets.UTF_8);
                else
                    redirect = "/incidencia/incidencia?result= " + false + "&message="
                            + URLEncoder.encode("¡Error! Acción no realizada correctamente.",
                            StandardCharsets.UTF_8);
                break;

            default:
                redirect = "/incidencia/incidencia";
        }
        resp.sendRedirect(req.getContextPath() + redirect);
    }
}
