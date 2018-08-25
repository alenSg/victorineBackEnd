package com.victorine.victorine.controllers;

import com.google.gson.Gson;
import com.victorine.victorine.functions.functions;
import com.victorine.victorine.models.askJSON;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/logic")
public class logicController {
    private static int qcounter;
    functions logd = new functions();
    public logicController() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
    }

    @RequestMapping(value = "/qList", method = RequestMethod.GET)
    public List getqList() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        return logd.getQuestion();
    }
    @RequestMapping(value = "/aList", method = RequestMethod.GET)
    public List getAlist() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        return logd.getAnswers();

    }
    @PostMapping("ask")
    public String ask(@RequestBody String JSONParam) throws SQLException {
        Gson gson = new Gson();
        askJSON value = gson.fromJson(JSONParam, askJSON.class);
        return logd.ask(value.user, value.answer);
    }

    @RequestMapping(value = "/getDate", method = RequestMethod.GET)
    public String getdate() throws SQLException {
        return logd.getStreamTime();
    }

    @RequestMapping(value = "/getendDate", method = RequestMethod.GET)
    public String getenddate() throws SQLException {
        return logd.getendTime();
    }
}