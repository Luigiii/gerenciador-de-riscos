package poc.oneracao.controller;

import com.google.gson.Gson;
import poc.oneracao.repository.RiscoAnaliticoSyncService;
import poc.oneracao.model.RiscoAnalitico;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/v1/riscos")
public class RiscosAnaliticosController {

    @Inject
    RiscoAnaliticoSyncService riscoAnaliticoSyncService;

    Gson gson = new Gson();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String cadastraRisco(RiscoAnalitico riscoAnalitico){
        riscoAnaliticoSyncService.add(riscoAnalitico);
        return new Gson().toJson(riscoAnalitico);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{idRisco}")
    public String buscaRisco(@PathParam("idRisco") String idRisco) {
        System.out.println(">>> buscaRisco" +idRisco);
        RiscoAnalitico risco = riscoAnaliticoSyncService.get(idRisco);
        System.out.println("<<< buscaRisco" +risco);
        System.out.println("<<< buscaRisco JSON saida" +gson.toJson(risco));
        return gson.toJson(risco);
    }

//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public String buscaTodosOsRiscos() {
//        System.out.println(">>> buscaTodosOsRiscos");
//        List<RiscoAnalitico> riscos = riscoAnaliticoSyncService.findAll();
//        return gson.toJson(riscos);
//    }
}
