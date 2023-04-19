package poc.oneracao.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import poc.oneracao.model.RiscoAnalitico;
import poc.oneracao.repository.RiscoAnaliticoSyncService;
import poc.oneracao.utils.JsonUtils;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/v1/riscos")
public class RiscosAnaliticosController {

    @Inject
    RiscoAnaliticoSyncService riscoAnaliticoSyncService;


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String cadastraRisco(RiscoAnalitico riscoAnalitico) throws JsonProcessingException {
        riscoAnaliticoSyncService.add(riscoAnalitico);
        return JsonUtils.objectToJson(riscoAnalitico);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{idRisco}")
    public String buscaRisco(@PathParam("idRisco") String idRisco) throws JsonProcessingException {
        RiscoAnalitico risco = riscoAnaliticoSyncService.get(idRisco);
        return JsonUtils.objectToJson(risco);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String buscaTodosOsRiscos() throws JsonProcessingException {
        System.out.println(">>> buscaTodosOsRiscos");
        List<RiscoAnalitico> riscos = riscoAnaliticoSyncService.findAll();
        return JsonUtils.objectToJson(riscos);
    }
}
