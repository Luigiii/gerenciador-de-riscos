package poc.oneracao.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.jboss.logging.Logger;
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

    private final Logger LOGGER = Logger.getLogger(RiscosAnaliticosController.class);

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String cadastraRisco(RiscoAnalitico riscoAnalitico) throws JsonProcessingException {
        LOGGER.info("Cadastrando risco com id: " + riscoAnalitico.getId());
        try {
            boolean b = 10 / 0 == 1;
            emitter.send(riscoAnalitico);
        } catch (Exception e) {
            LOGGER.error("Erro ao enviar risco" + riscoAnalitico.getId() + " para o tópico!", e);
            throw e;
        }
        LOGGER.info("Risco " + riscoAnalitico.getId() + " cadastrado com sucesso!");
        return JsonUtils.objectToJson(riscoAnalitico);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{idRisco}")
    public String buscaRisco(@PathParam("idRisco") String idRisco) throws JsonProcessingException {
        LOGGER.info("Buscando risco com id: " + idRisco);
        RiscoAnalitico risco = riscoAnaliticoSyncService.get(idRisco);
        LOGGER.info("Risco encontrado com sucesso: " + risco.getId());
        return JsonUtils.objectToJson(risco);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String buscaTodosOsRiscos() throws JsonProcessingException {
        LOGGER.info("Buscando todos os riscos cadastrados na base de dados...");
        List<RiscoAnalitico> riscos = riscoAnaliticoSyncService.findAll();
        LOGGER.info("Riscos encontrados com sucesso!");
        return JsonUtils.objectToJson(riscos);
    }
}
