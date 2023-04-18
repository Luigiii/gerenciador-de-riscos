package poc.oneracao.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import poc.oneracao.config.JacksonConfig;
import poc.oneracao.repository.RiscoAnaliticoSyncService;
import poc.oneracao.model.RiscoAnalitico;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/v1/riscos")
public class RiscosAnaliticosController {

    @Inject
    RiscoAnaliticoSyncService riscoAnaliticoSyncService;

    ObjectMapper mapper = new ObjectMapper();
    JacksonConfig jacksonConfig = new JacksonConfig();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String cadastraRisco(RiscoAnalitico riscoAnalitico) throws JsonProcessingException {
        riscoAnaliticoSyncService.add(riscoAnalitico);
        jacksonConfig.customize(mapper);
        return mapper.writeValueAsString(riscoAnalitico);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{idRisco}")
    public String buscaRisco(@PathParam("idRisco") String idRisco) throws JsonProcessingException {
        System.out.println(">>> buscaRisco" +idRisco);
        RiscoAnalitico risco = riscoAnaliticoSyncService.get(idRisco);
        System.out.println("<<< buscaRisco" +risco);
        System.out.println("<<< buscaRisco JSON saida" +mapper.writeValueAsString(risco));
        return mapper.writeValueAsString(risco);
    }

//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public String buscaTodosOsRiscos() {
//        System.out.println(">>> buscaTodosOsRiscos");
//        List<RiscoAnalitico> riscos = riscoAnaliticoSyncService.findAll();
//        return gson.toJson(riscos);
//    }
}
