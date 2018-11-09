package com.victorine.victorine.functions;

import com.victorine.victorine.config.JDBCConnect;
import com.victorine.victorine.models.answers;
import com.victorine.victorine.models.question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class functions {
    JDBCConnect jdbcConnect = new JDBCConnect();
    Connection connection = jdbcConnect.getConnection();
    List finalRes = new ArrayList();

    public functions() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
    }

    public List getQuestion() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        JDBCConnect jdbcConnect = new JDBCConnect();
        Connection connection = jdbcConnect.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT question AS quest FROM victorine_question_list " +
                "WHERE id IN (SELECT answer_id FROM stream_history_temp)");
        ResultSet rs = preparedStatement.executeQuery();
        finalRes.clear();
        while (rs.next()) {
            question quest = new question();
            quest.setQuestion(rs.getString("quest"));
            finalRes.add(quest);
        }
        return finalRes;
    }

    public List getAnswers() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT answer FROM victorine_answers_list WHERE question_id IN (SELECT answer_id FROM stream_history_temp)"
        );
        ResultSet rs = preparedStatement.executeQuery();
        finalRes.clear();
        while (rs.next()) {
            answers answ = new answers();
            answ.setAnswer(rs.getString("answer"));
            finalRes.add(answ);
        }
        return finalRes;
    }

    public String ask(String username, String answer) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT ask ('" + username + "', '" + answer + "')");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            return String.valueOf(rs.getString("ask"));
        }
        return null;
    }

    public String getStreamTime() throws SQLException {
        ResultSet rs = connection.prepareStatement("SELECT start_date FROM stream_setting WHERE used IS FALSE").executeQuery();
        while (rs.next()) {
            return rs.getString("start_date");
        }
        return null;
    }

    public String getendTime() throws SQLException {
        ResultSet rs = connection.prepareStatement("SELECT end_date FROM stream_setting WHERE used IS FALSE").executeQuery();
        while (rs.next()) {
            return rs.getString("end_date");
        }
        return null;
    }
}