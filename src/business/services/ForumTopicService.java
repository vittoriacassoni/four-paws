package business.services;

import comuns.access.ForumTopic;
import dao.access.ForumTopicSqlServerDAO;

import java.util.ArrayList;

public class ForumTopicService {

    ForumTopicSqlServerDAO dao = new ForumTopicSqlServerDAO();

    //TODO - CHAMADA DOS METODOS DA DAO E VALIDAÇÕES

    public ArrayList<ForumTopic> listTopics() {
        return dao.listTopics();
    }
}
