/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DB.Notificaciones;
import DB.Usuarios;
import Modelo.Notificacion;
import Modelo.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author willy
 */
@WebServlet(name = "ServletSolucitudAmistad", urlPatterns = {"/ServletSolucitudAmistad"})
public class ServletSolicitudAmistad extends HttpServlet {

    private Notificaciones not;

    public ServletSolicitudAmistad() {
        not = new Notificaciones();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //añadir solicitud amistad 
        String r = "";
        String id = request.getParameter("IdUsuario").trim();
        HttpSession respuesta = request.getSession();
        String email = (String) respuesta.getAttribute("sessionEmail");
        Usuarios usu = new Usuarios();
        Usuario u = usu.buscar2(email), u2 = usu.FindById(id);
        if (u2 != null) {
            Notificacion n = new Notificacion("" + (this.not.findAll().size() + 1), id, null, null, null, u.getNickname());
            if (not.insert(n)) {
                r = "se a enviado la solicitud";
            } else {
                r = "error al enviar notificacion";
            }
        } else {
            r = "el usuario con id \"" + id + "\" no existe";
        }
        response.setContentType("html/text");
        response.getWriter().write(r);
    }
}
