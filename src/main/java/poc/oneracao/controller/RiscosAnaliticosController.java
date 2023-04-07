package poc.oneracao.controller;

import poc.oneracao.repository.RiscoAnaliticoSyncService;
import poc.oneracao.model.RiscoAnalitico;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

//FIXME: Classe temporária utilizada somente para testar o dynamo localmente
@Path("/v1/riscos")
public class RiscosAnaliticosController {

    @Inject
    RiscoAnaliticoSyncService riscoAnaliticoSyncService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String cadastraRisco(RiscoAnalitico riscoAnalitico){
        riscoAnaliticoSyncService.add(riscoAnalitico);
        return riscoAnalitico.toString();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String buscaRisco(@QueryParam("idRisco") String idRisco) {
        RiscoAnalitico risco = riscoAnaliticoSyncService.get(idRisco);
        return risco.toString();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String buscaTodosOsRiscos() {
        List<RiscoAnalitico> riscos = riscoAnaliticoSyncService.findAll();
        return riscos.toString();
    }
}
