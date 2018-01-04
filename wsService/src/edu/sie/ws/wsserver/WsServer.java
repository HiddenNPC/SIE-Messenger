package edu.sie.ws.wsserver;

import java.io.IOException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import edu.sie.ws.frame.Pad;

@ServerEndpoint("/websocket/{nickName}")
public class WsServer {

	private static Pad serverPad=new Pad();
	private String usernamestr;

	@OnMessage
	public void onMessage(String message, Session session) throws IOException, InterruptedException {
		// 打印从客户端获取到的信息

	}

	@OnOpen
	public void onOpen(Session session,@PathParam(value = "nickName") String nickname) throws IOException {
		serverPad.addConnection(nickname, session);
	}

	@OnClose
	public void onClose(Session session) {
		serverPad.delConnection(usernamestr, session);
	}

}