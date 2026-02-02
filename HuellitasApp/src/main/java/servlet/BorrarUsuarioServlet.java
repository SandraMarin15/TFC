package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import servicio.UsuarioServicio;

@WebServlet("/BorrarUsuarioServlet")
public class BorrarUsuarioServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UsuarioServicio servicio = new UsuarioServicio();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            int id = Integer.parseInt(request.getParameter("id"));

            servicio.borrarUsuario(id);

            response.sendRedirect("ListarUsuariosServlet");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("ListarUsuariosServlet");
        }
    }
}
