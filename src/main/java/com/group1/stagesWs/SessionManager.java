package com.group1.stagesWs;

import com.group1.stagesWs.enums.Session;

import java.util.List;

public interface SessionManager<T> {

    public final static Session CURRENT_SESSION = Session.AUTOMNE_2021;

    List<T>getListForCurrentSession(List<T> list);
}
