package poc.oneracao.controller;

import poc.oneracao.repository.RiscoAnaliticoSyncService;
import poc.oneracao.model.RiscoAnalitico;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//FIXME: Classe temporária utilizada somente para testar o dynamo localmente
@Path("/v1/riscos")
public class RiscosAnaliticosController {

    @Inject
    RiscoAnaliticoSyncService riscoAnaliticoSyncService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String cadastraRisco(RiscoAnalitico riscoAnalitico){
        System.out.println("Cadastrando risco" +riscoAnalitico.getId()+ " no banco de dados...");
        System.out.println("Cadastrando risco" +riscoAnalitico.getData()+ " no banco de dados...");
        System.out.println("Cadastrando risco" +riscoAnalitico.getValor()+ " no banco de dados...");
        System.err.println("chamando service");
        riscoAnaliticoSyncService.add(riscoAnalitico);
        return riscoAnalitico.toString();
    }

}
