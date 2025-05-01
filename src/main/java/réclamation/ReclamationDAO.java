package réclamation;
import java.util.ArrayList;  
import java.util.List;  

public interface ReclamationDAO {
    void createReclamation(ReclamationBean reclamation);
    List<ReclamationBean> getAllReclamations();
    void respondToReclamation(int idReclamation, int idAgent, String responseText);
    List<ResponseBean> getResponsesByClientId(int idClient);
    List<ResponseBean> getResponsesByAgent(int idAgent);
}

