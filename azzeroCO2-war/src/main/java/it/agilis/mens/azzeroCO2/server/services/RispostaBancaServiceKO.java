package it.agilis.mens.azzeroCO2.server.services;

import it.agilis.mens.azzeroCO2.client.services.AzzerroCO2UtilsClientHelper;
import it.agilis.mens.azzeroCO2.core.criteria.SellaRicevutaDiPagamentoCriteria;
import it.agilis.mens.azzeroCO2.core.entity.Esito;
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

/**
 * Created by IntelliJ IDEA.
 * User: Giovanni La Torre
 * Date: 22/09/11
 * Time: 15.02
 * To change this template use File | Settings | File Templates.
 */
public class RispostaBancaServiceKO extends HttpServlet {
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

//http://62.149.166.148/azzeroCO2/azzeroCO2/rispostaBancaOK?
    // TRANSACTION_ID=84D9357D362F49CF86FFCADFDE9757AE&
    // MERCHANT_ID=396870600001&
    // ORDER_ID=AZZEROCO2.1323719666948&
    // COD_AUT=T04396&
    // IMPORTO=0,19&
    // DIVISA=EUR&
    // MAC=7363B5815A7F7282496781ED0E4821AB


    private static final String PAGE_TOP = ""
            + "<html>"
            + "<head>"
            + "<title>AzzeroCO2</title>"
            + "</head>"
            + "<body>"
            + "<h3>AzzeroCO2</h3>"
            + "<table>";

    private static final String PAGE_BOTTOM = ""
            + "</table>" +
            "< a href=\"javascript:window.opener='x';window.close();\">Close< /a>"
            + "</body>"
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
        // String PROG_ID = request.getParameter("PROG_ID");       //(il codice dell'oggetto, nel nostro esempio mi pare "pagamentoCalcolatore"

        SellaRicevutaDiPagamentoCriteria criteria = new SellaRicevutaDiPagamentoCriteria();
        criteria.setOrderId(ORDER_ID);

        PrintWriter out = response.getWriter();
        SellaRicevutaDiPagamento ricevuta = azzeroCO2Register.getSellaRicevutaDiPagamento(criteria);
        if (ricevuta != null) {
            ricevuta.setTRANSACTION_ID(TRANSACTION_ID);
            ricevuta.setCOD_AUT(COD_AUT);
            try {
                MessageDigest algorithm = MessageDigest.getInstance("MD5");
                algorithm.reset();

                /*String controllo = TRANSACTION_ID + MERCHANT_ID + ORDER_ID + COD_AUT + IMPORTO + DIVISA + PagamentoModel.key;
                algorithm.update(controllo.toUpperCase().getBytes());

                if (new String(algorithm.digest(), "UTF-8").toLowerCase().equalsIgnoreCase(MAC.toUpperCase())) {
               */
                String theMd5 = AzzerroCO2UtilsClientHelper.getMAC_MD5((TRANSACTION_ID + MERCHANT_ID + ORDER_ID + COD_AUT + IMPORTO + DIVISA + PagamentoModel.key).toUpperCase());

                if (theMd5.equalsIgnoreCase(MAC)) {
                    ricevuta.setEsito(Esito.PAGAMENTO_NON_AVVENUTO);
                    azzeroCO2Register.saveRicevuta(ricevuta);
                } else {
                    out.println(PAGE_TOP + "<tr><td>MD5 non corrispondente.+3</td></tr>" + PAGE_BOTTOM);
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                out.println(PAGE_TOP + "<tr><td>Errore nella ricezione dei dati. -0</td></tr>" + PAGE_BOTTOM);
            } catch (Exception e) {
                e.printStackTrace();
                out.println(PAGE_TOP + "<tr><td>Errore nella ricezione dei dati. -1</td></tr>" + PAGE_BOTTOM);
            }
        } else {
            out.println(PAGE_TOP + "<tr><td>Errore nella ricezione dei dati. -2</td></tr>" + PAGE_BOTTOM);
        }
    }
}
