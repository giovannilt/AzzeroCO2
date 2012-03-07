package it.agilis.mens.azzeroCO2.server.services;

import it.agilis.mens.azzeroCO2.core.entity.Ordine;
import it.agilis.mens.azzeroCO2.core.register.impl.AzzeroCO2Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Giovanni La Torre
 * Date: 22/09/11
 * Time: 15.02
 * To change this template use File | Settings | File Templates.
 */
public class AzzeroCo2ITPutInfoServiceOK extends HttpServlet {
    @Autowired
    @Qualifier("azzeroCO2Register")
    private AzzeroCO2Register azzeroCO2Register;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        WebApplicationContext ctx = WebApplicationContextUtils
                .getRequiredWebApplicationContext(config.getServletContext());
        AutowireCapableBeanFactory beanFactory = ctx
                .getAutowireCapableBeanFactory();
        beanFactory.autowireBean(this);
    }

    private static final String PAGE_TOP = ""
            + "<html>"
            + "<head>"
            + "<title>AzzeroCO2</title>"
            + "</head>"
            + "<body onLoad='document.calcdata.submit()'>";
    /*           + "<h3>AzzeroCO2</h3>"  ;*/

    private static final String PAGE_BOTTOM =
            /*    "< a href='javascript:window.opener='x';window.close();'>Close< /a> + "*/
            "</body>"
                    + "</html>";


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

        String idOrdine = request.getParameter("idOrdine");          //(id dell'ordine)
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        if (idOrdine != null && idOrdine.length() > 0) {
            Long oid = new Long(idOrdine);
            try {
                Ordine ordine = azzeroCO2Register.getOrdineById(oid);

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                NumberFormat formatter = new DecimalFormat("#0.00");
                String tonnellate = "" + formatter.format(ordine.getRicevutaDiPagamento().getKgCO2() / 1000);
                String importoinEuro = ordine.getRicevutaDiPagamento().getIMPORTO().replace(",", ".");

                out.println(PAGE_TOP +
                        "<form name='calcdata' id='calcdata' method='post' action='http://www.azzeroco2.it/onlinereg/agents/calc_receiver.php'>\n" +
                        "<input type='hidden' name='nome'       id='nome'       value='" + ordine.getUtente().getNome() + "' />\n" +
                        "<input type='hidden' name='cognome'    id='cognome'    value='" + ordine.getUtente().getCognome() + "' />\n" +
                        "<input type='hidden' name='societa'    id='societa'    value='" + ordine.getUtente().getRagSociale() + "' />\n" +
                        "<input type='hidden' name='email'      id='email'      value='" + ordine.getUtente().getEmail() + "' />\n" +
                        "<input type='hidden' name='indirizzo'  id='indirizzo'  value='" + ordine.getUtente().getIndirizzo() + "|" + ordine.getUtente().getCap() + "' />\n" +
                        "<input type='hidden' name='luogo'      id='luogo'      value='" + ordine.getUtente().getCitta() + "|" + ordine.getUtente().getProvincia() + "' />\n" +
                        "<input type='hidden' name='cfisc'      id='cfisc'      value='" + ordine.getUtente().getpIvaCF() + "' />\n" +
                        "<input type='hidden' name='piva'       id='piva'       value='" + ordine.getUtente().getpIvaCF() + "' />\n" +
                        "<input type='hidden' name='data'       id='data'       value='" + format.format(new Date()) + "' />\n" +
                        "<input type='hidden' name='tonnellate' id='tonnellate' value='" + tonnellate + "' />\n" +      //TODO non deve mettere i punti che separano le migliaia
                        "<input type='hidden' name='crediti'    id='crediti'    value='" + tonnellate + "' />\n" +                             //TODO ma che cos'erano i crediti?
                        "<input type='hidden' name='euro'       id='euro'       value='" + importoinEuro + "' />\n" +          //TODO importo deve avere il formato 234533.22 quindi niente separatore delle migliaia e . per i decimali al posto della virgola
                        "<input type='hidden' name='idprogetto' id='idprogetto' value='" + ordine.getProgettoCompensazione().getId() + "' />\n" +
                        "</form>" +
                        PAGE_BOTTOM
                );


            } catch (Exception e) {
                e.printStackTrace();
                out.println(PAGE_TOP + "<table><tr><td>Errore nella ricezione dei dati.+1</td></tr></table>" + PAGE_BOTTOM);
            }
        } else {
            out.println(PAGE_TOP + "<tr><td>Errore nella ricezione dei dati.+2</td></tr>" + PAGE_BOTTOM);
        }

    }
}
