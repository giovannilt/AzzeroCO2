package it.agilis.mens.azzeroCO2.server.services;

import it.agilis.mens.azzeroCO2.core.register.impl.AzzeroCO2Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Giovanni La Torre
 * Date: 22/09/11
 * Time: 15.02
 * To change this template use File | Settings | File Templates.
 */
public class DownloadCertificato extends HttpServlet {
    @Autowired
    @Qualifier("azzeroCO2Register")
    private AzzeroCO2Register azzeroCO2Register;

    public AzzeroCO2Register getAzzeroCO2Register() {
        return azzeroCO2Register;
    }

    public void setAzzeroCO2Register(AzzeroCO2Register azzeroCO2Register) {
        this.azzeroCO2Register = azzeroCO2Register;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "attachment; filename=Certificato.pdf");

        String certificato = request.getParameter("Certificato");
    }
}
