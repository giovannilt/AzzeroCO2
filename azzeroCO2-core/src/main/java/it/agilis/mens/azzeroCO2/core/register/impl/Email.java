package it.agilis.mens.azzeroCO2.core.register.impl;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 10/30/11
 * Time: 3:21 PM
 * To change this template use File | Settings | File Templates.
 */


public class Email implements Serializable {
	private static final long serialVersionUID = 1L;

    private String fromUser;
    private String[] toUser;

    private String subject;
    private String body;


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public String getBodyWithBR() {
        return body.replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
    }

    public String getBody(boolean replaceNewLine) {
    	return replaceNewLine ? getBodyWithBR() : getBody();
    }

    public void setBody(String body) {
        this.body = body;
    }


	public String getFromUser() {
		return fromUser;
	}


	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}

	public String[] getToUser() {
		return toUser.clone();
	}

	public void setToUser(String[] toUser) {
		this.toUser = toUser.clone();
	}

}
