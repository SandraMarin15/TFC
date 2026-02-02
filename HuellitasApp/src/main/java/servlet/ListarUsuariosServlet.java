package servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import modelo.Usuario;
import servicio.UsuarioServicio;


@WebServlet("/ListarUsuariosServlet")
public class ListarUsuariosServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UsuarioServicio servicio = new UsuarioServicio();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            List<Usuario> lista = servicio.listarUsuarios();

            request.setAttribute("listaUsuarios", lista);
            request.getRequestDispatcher("usuarios.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("index.jsp");
        }
    }
}


