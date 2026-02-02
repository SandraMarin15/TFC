package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


/*HomeServlet.
 Es un controlador de seguridad básico,para evitar que un usuario no autenticado 
 pueda acceder a páginas internas escribiendo la URL manualmente.
 Comprueba si existe usa sesión y comprueba si hay un usuario guardado en sesión.
  Si no existe -> redirige al login
  Si existe -> redirige al index.
 */

@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("login.html");
            return;
        } 
        
        request.getRequestDispatcher("home.jsp").forward(request, response);
        
      
    }
}
