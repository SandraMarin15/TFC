package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import modelo.Usuario;
import modelo.RolUsuario;
import servicio.UsuarioServicio;

@WebServlet("/ActualizarUsuarioServlet")
public class ActualizarUsuarioServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UsuarioServicio servicio = new UsuarioServicio();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            int userId = Integer.parseInt(request.getParameter("id"));
            int rolId  = Integer.parseInt(request.getParameter("rolId"));

            Usuario u = new Usuario();
            u.setUsuarioId(userId);

            RolUsuario r = new RolUsuario();
            r.setRolUsuarioId(rolId);

            u.setRolUsuario(r);

            servicio.modificarUsuario(u);

            response.sendRedirect("ListarUsuariosServlet");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("ListarUsuariosServlet");
        }
    }
}
