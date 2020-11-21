package business.services;

import business.Validates;
import comuns.access.ForumComment;
import comuns.access.ForumTopic;
import dao.access.ForumCommentSqlServerDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ForumCommentService {
    ForumCommentSqlServerDAO dao = new ForumCommentSqlServerDAO();

    //TODO - CHAMADA DOS METODOS DA DAO E VALIDAÇÕES

    public ArrayList<ForumComment> listComments(Integer topicId) throws SQLException {
        return dao.listComments(topicId);
    }

    public boolean insert(ForumComment comment) throws Exception {
        if (Validates.validateRequiredField(comment.getDiscussion())) {
            throw new Exception("Preencha todos os campos!");
        }

        return dao.insert(comment);
    }

}
