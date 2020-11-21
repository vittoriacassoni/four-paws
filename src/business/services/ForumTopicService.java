package business.services;

import business.Validates;
import comuns.access.ForumTopic;
import comuns.bases.Entity;
import dao.access.ForumTopicSqlServerDAO;

import java.util.ArrayList;

public class ForumTopicService {
    ForumTopicSqlServerDAO dao = new ForumTopicSqlServerDAO();

    //TODO - CHAMADA DOS METODOS DA DAO E VALIDAÇÕES

    public ArrayList<ForumTopic> listTopics(String titleFilter) {
        return dao.listTopics(titleFilter);
    }

    public boolean insert(ForumTopic topic) throws Exception {
        if (Validates.validateRequiredField(topic.getTitle()) || Validates.validateRequiredField(topic.getDiscussion())) {
            throw new Exception("Preencha todos os campos!");
        }
        return dao.insert(topic);
    }
}
