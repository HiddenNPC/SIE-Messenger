package edu.sie.ws.wsserver;

import java.io.IOException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import edu.sie.ws.frame.Pad;
import org.eclipse.jdt.internal.compiler.lookup.SyntheticArgumentBinding;

@ServerEndpoint("/websocket/{nickName}")
public class WsServer {

	private static Pad serverPad=new Pad();
	private String usernamestr;

	@OnMessage
	public void onMessage(String message, Session session) throws IOException, InterruptedException {
		// 处理客户端获取到的信息
		System.out.println(message);
		if(message.contains("发货问题")){
			session.getBasicRemote().sendText("辽宁省内默认发ems生鲜速递或特急送，12点前付款订单当天下午发货。（大连同城订单早8点前付款当天发同城快递，当天到货）\n" +
					"辽宁省外默认发圆通快递。\n" +
					"中午12点统一截单，12点后订单第二天发货.");
		}
		else if(message.contains("包邮问题")){
			session.getBasicRemote().sendText("辽宁省内满88元包邮！省外不包邮，下单自动计算运费。");
		}
		else if(message.contains("店铺地址")){
			session.getBasicRemote().sendText("辽宁省大连市沙河口区五一路台山冷库门前100米 久航批发超市\n" +
					"联系电话：15542693139\n" +
					"营业时间：8:00--17:00");
		}else{
			session.getBasicRemote().sendText("试试这些：发货问题，包邮问题，店铺地址");
		}
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