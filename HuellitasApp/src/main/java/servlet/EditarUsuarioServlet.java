package servlet;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import modelo.Usuario;
import servicio.UsuarioServicio;

@WebServlet("/EditarUsuarioServlet")
public class EditarUsuarioServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UsuarioServicio servicio = new UsuarioServicio();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            int id = Integer.parseInt(request.getParameter("id"));

            Usuario u = servicio.buscarUsuarioPorId(id);

            request.setAttribute("usuario", u);
            request.getRequestDispatcher("editarUsuario.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("ListarUsuariosServlet");
        }
    }
}
