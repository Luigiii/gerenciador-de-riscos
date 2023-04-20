package poc.oneracao.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
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

    @Inject
    @Channel("riscos-contratados-out")
    Emitter<RiscoAnalitico> emitter;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String cadastraRisco(RiscoAnalitico riscoAnalitico) throws JsonProcessingException {
        emitter.send(riscoAnalitico);
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
        List<RiscoAnalitico> riscos = riscoAnaliticoSyncService.findAll();
        return JsonUtils.objectToJson(riscos);
    }
}
