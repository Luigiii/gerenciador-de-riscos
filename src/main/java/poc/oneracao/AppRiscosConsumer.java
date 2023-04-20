package poc.oneracao;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import poc.oneracao.model.RiscoAnalitico;
import poc.oneracao.repository.RiscoAnaliticoSyncService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class AppRiscosConsumer {

    @Inject
    RiscoAnaliticoSyncService riscoAnaliticoSyncService;

    @Incoming("riscos-contratados-in")
    public void salvaRisco(RiscoAnalitico riscoAnalitico) {
        riscoAnaliticoSyncService.add(riscoAnalitico);
    }

}
