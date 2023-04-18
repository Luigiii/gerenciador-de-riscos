package poc.oneracao.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import poc.oneracao.model.RiscoAnalitico;
import poc.oneracao.repository.RiscoAnaliticoSyncService;
import poc.oneracao.utils.JsonUtils;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

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
        System.out.println(">>> buscaRisco" + idRisco);
        RiscoAnalitico risco = riscoAnaliticoSyncService.get(idRisco);
        System.out.println("<<< buscaRisco" + risco);
        System.out.println("<<< buscaRisco JSON saida" + JsonUtils.objectToJson(risco));
        return JsonUtils.objectToJson(risco);
    }

//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public String buscaTodosOsRiscos() {
//        System.out.println(">>> buscaTodosOsRiscos");
//        List<RiscoAnalitico> riscos = riscoAnaliticoSyncService.findAll();
//        return gson.toJson(riscos);
//    }
}
