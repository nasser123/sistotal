package relatorios;

import Utilidades.ConnectionFactory;
import Utilidades.ReportUtils;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import model.Cliente;
import model.OrdemServico;
import net.sf.jasperreports.engine.JRException;
import view.TelaProgresso;

/**
 * Ponto de entrada do projeto.
 *
 *
 */
public class ExecutaRelatorio {

    private static TelaProgresso telaProgresso;

    String rel = "";

//    public static class ThreadFrameProgressBar extends Thread {
//
//        @Override
//        public void run() {
//            telaProgresso = new TelaProgresso();
//            telaProgresso.setVisible(true);
//        }
//    }
    public void abrirRelatorioOS(OrdemServico os, String sistemaOperacional, String antiVirus, String office) {

//        ThreadFrameProgressBar tfp = new ThreadFrameProgressBar();
//        tfp.start();
        ThreadExecutaRelatorio ter = new ThreadExecutaRelatorio();
        ter.run(os, sistemaOperacional, antiVirus, office);

    }

    public void abrirRelatorioListaOS(Cliente c, Date dataInicial, Date dataFinal, BigDecimal aberto) {
        ThreadExecutaRelatorioListaOS terlo = new ThreadExecutaRelatorioListaOS();
        terlo.run(c, dataInicial, dataFinal, aberto);

    }

    private static class ThreadExecutaRelatorio extends Thread {

        public void run(OrdemServico os, String sistemaOperacional, String antiVirus, String office) {
            InputStream inputStream = getClass().getResourceAsStream("OrdemServico2015.jasper");
            Map<String, Object> parametros = new HashMap();
            parametros.put("ordem", os.getIdordemServico());
            parametros.put("sistemaOperacional", sistemaOperacional);
            parametros.put("antiVirus", antiVirus);
            parametros.put("office", office);
            parametros.put("Imagem", "total_tech_logo.jpg");
            try {
                // abre o relatório
                ReportUtils.openReport("Ordem de Serviço", inputStream, parametros, ConnectionFactory.getConnection());
                //telaProgresso.setVisible(false);
            } catch (JRException exc) {
                exc.printStackTrace();
            }
        }

    }

    private static class ThreadExecutaRelatorioListaOS extends Thread {

        public void run(Cliente c, Date dataInicial, Date dataFinal, BigDecimal aberto) {
            InputStream inputStream = getClass().getResourceAsStream("ListaOs.jasper");
            Map<String, Object> parametros = new HashMap();
            parametros.put("cliente", c.getIdcliente());
            parametros.put("inicial", dataInicial);
            parametros.put("final", dataFinal);
            parametros.put("aberto", aberto);

            try {
                // abre o relatório
                ReportUtils.openReport("Lista de Serviços", inputStream, parametros, ConnectionFactory.getConnection());
                //telaProgresso.setVisible(false);
            } catch (JRException exc) {
                exc.printStackTrace();
            }
        }

    }

}
