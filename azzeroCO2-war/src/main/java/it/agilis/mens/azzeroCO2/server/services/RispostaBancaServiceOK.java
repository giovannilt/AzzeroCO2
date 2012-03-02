package it.agilis.mens.azzeroCO2.server.services;

import it.agilis.mens.azzeroCO2.client.services.AzzerroCO2UtilsClientHelper;
import it.agilis.mens.azzeroCO2.core.criteria.OrdineCriteria;
import it.agilis.mens.azzeroCO2.core.criteria.SellaRicevutaDiPagamentoCriteria;
import it.agilis.mens.azzeroCO2.core.entity.Esito;
import it.agilis.mens.azzeroCO2.core.entity.Ordine;
import it.agilis.mens.azzeroCO2.core.entity.SellaRicevutaDiPagamento;
import it.agilis.mens.azzeroCO2.core.register.impl.AzzeroCO2Register;
import it.agilis.mens.azzeroCO2.shared.model.pagamento.PagamentoModel;
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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Giovanni La Torre
 * Date: 22/09/11
 * Time: 15.02
 * To change this template use File | Settings | File Templates.
 */
public class RispostaBancaServiceOK extends HttpServlet {
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
            + "<head>"
            + "<title>AzzeroCO2</title>"
            + "</head>"
            + "<body>";// onLoad='document.calcdata.submit()'>"
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

        String TRANSACTION_ID = request.getParameter("TRANSACTION_ID");//(id della transazione fornito dalla banca)
        String MERCHANT_ID = request.getParameter("MERCHANT_ID");     //(id del merchant, dove il merchant  AzzeroCO2 e l'id  sempre 396870600001
        String ORDER_ID = request.getParameter("ORDER_ID");          //(id dell'ordine)
        String COD_AUT = request.getParameter("COD_AUT");           //(codice di autorizzazione fornito dalla banca)
        String IMPORTO = request.getParameter("IMPORTO");          //
        String DIVISA = request.getParameter("DIVISA");           //(nel nostro caso "EUR")
        String MAC = request.getParameter("MAC");                //(codice di controllo da usare tra poco)
        //   String PROG_ID = request.getParameter("PROG_ID");       //(il codice dell'oggetto, nel nostro esempio mi pare "pagamentoCalcolatore"
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        if (ORDER_ID != null && ORDER_ID.length() > 0) {
            SellaRicevutaDiPagamentoCriteria criteria = new SellaRicevutaDiPagamentoCriteria();
            criteria.setOrderId(ORDER_ID);

            SellaRicevutaDiPagamento ricevuta = azzeroCO2Register.getSellaRicevutaDiPagamento(criteria);
            if (ricevuta != null) {
                ricevuta.setTRANSACTION_ID(TRANSACTION_ID);
                ricevuta.setCOD_AUT(COD_AUT);
                ricevuta.setUpdateFromBanca(new Date());
                try {
                    MessageDigest algorithm = MessageDigest.getInstance("MD5");
                    algorithm.reset();

                    String theMd5 = AzzerroCO2UtilsClientHelper.getMAC_MD5((TRANSACTION_ID + MERCHANT_ID + ORDER_ID + COD_AUT + IMPORTO + DIVISA + PagamentoModel.key).toUpperCase());
                    //  if (theMd5.equalsIgnoreCase(MAC)) {
                    ricevuta.setEsito(Esito.PAGATO);
                    azzeroCO2Register.saveRicevuta(ricevuta);

                    OrdineCriteria ordineCriteria= new OrdineCriteria();
                    ordineCriteria.setRicevuta(ricevuta);
                    Ordine ordine= azzeroCO2Register.getOrdine(ordineCriteria);


                    SimpleDateFormat format = new SimpleDateFormat("dd_MM_yyyy");

                    out.println( PAGE_TOP+
                            "<form name='calcdata' id='calcdata' method='post' action='http://www.azzeroco2.it/onlinereg/agents/calc_receiver.php'>\n" +
                                    "<input type='hidden' name='nome'       id='nome'       value='"+ordine.getUtente().getNome()+"' />\n" +
                                    "<input type='hidden' name='cognome'    id='cognome'    value='"+ordine.getUtente().getCognome()+"' />\n" +
                                    "<input type='hidden' name='societa'    id='societa'    value='"+ordine.getUtente().getRagSociale()+"' />\n" +
                                    "<input type='hidden' name='email'      id='email'      value='"+ordine.getUtente().getEmail()+"' />\n" +
                                    "<input type='hidden' name='indirizzo'  id='indirizzo'  value='"+ordine.getUtente().getIndirizzo()+"' />\n" +
                                    "<input type='hidden' name='luogo'      id='luogo'      value='"+ordine.getUtente().getCitta()+"' />\n" +
                                    "<input type='hidden' name='cfisc'      id='cfisc'      value='"+ordine.getUtente().getpIvaCF()+"' />\n" +
                                    "<input type='hidden' name='piva'       id='piva'       value='"+ordine.getUtente().getpIvaCF()+"' />\n" +
                                    "<input type='hidden' name='data'       id='data'       value='"+format.format(ricevuta.getUpdateFromBanca())+"' />\n" +
                                    "<input type='hidden' name='tonnellate' id='tonnellate' value='"+ricevuta.getKgCO2()+"' />\n" +
                                    "<input type='hidden' name='crediti'    id='crediti'    value='"+ricevuta.getKgCO2()+"' />\n" +
                                    "<input type='hidden' name='euro'       id='euro'       value='"+ricevuta.getIMPORTO()+"' />\n" +
                                    "<input type='hidden' name='idprogetto' id='idprogetto' value='"+ordine.getProgettoCompensazione().getId()+"' />\n" +
                                    "</form>"    +
                            PAGE_BOTTOM
                    );


                    //  } else {
                    //      out.println(PAGE_TOP + "<table><tr><td>MD5 non corrispondente.+3</td></tr></table>" + PAGE_BOTTOM);
                    //  }
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                    out.println(PAGE_TOP + "<table><tr><td>Errore nella ricezione dei dati.+0</td></tr></table>" + PAGE_BOTTOM);
                } catch (Exception e) {
                    e.printStackTrace();
                    out.println(PAGE_TOP + "<table><tr><td>Errore nella ricezione dei dati.+1</td></tr></table>" + PAGE_BOTTOM);
                }
            }
        } else {
            out.println(PAGE_TOP + "<tr><td>Errore nella ricezione dei dati.+2</td></tr>" + PAGE_BOTTOM);

        }
    }
}
