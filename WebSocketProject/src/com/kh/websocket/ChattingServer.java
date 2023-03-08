package com.kh.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.websocket.EndpointConfig;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

// 일반 클래스를 웹소켓 서버로 등록하기 위한 방법
@ServerEndpoint(value="/chatting.do") // 클래서 선언부에 선언 시 웹소켓 서버로 등록이 됨
public class ChattingServer {

	// 내부에 클라이언트가 요청하는 내용을 처리하는 메서드 등록
	private static Map<String, Session> clients = new HashMap();
	
	@OnOpen
	public void open(Session session, EndpointConfig config) {
		// 접속을 요청한 클라이언트와 연결이 수립되면 실행되는 메서드
		System.out.println("클라이언트 접속");
		System.out.println(session.getId());
		clients.put(session.getId(), session);
	}
	
	// 클라이언트들이 보내는 메세지를 처리하는 함수
	@OnMessage
	public void message(Session session, String msg) { // "발송자, 수신자, 내용"
		// 첫 번째 매개 변수 session 객체 안에는 메세지를 보낸 클라이언트의 session 객체가 담겨있음
		// 두 번째 매개 변수 msg에는 클라이언트가 보낸 메세지가 담겨있다.("발송자, 수신자, 내용")
		
		System.out.println(session.getId()+":::"+msg);
		
		// 클라이언트가 보낸 메세지를 파싱
		String[] data = msg.split(",");
		// 0번 인덱스 : 발송자, 1번 인덱스 : 수신자, 2번 인덱스 : 메세지
		
		// 보낸 데이터를 세션에 저장할 수 있음
		session.getUserProperties().put("msg", data);
		// 클라이언트가 보낸 데이터를 다른 접속한 클라이언트에게 전송하기
		// 1. 접속한 클라이언트 관리
		//    1) Collection 클래스를 통해 Map, List, Set을 이용해서 관리
		//    2) session 클래스에서 접속한 모든 session을 알 수 있는 메서드를 사용 -> 버전2에서 사용할 예정
		
		// 2. 접속한 session 객체를 이용해서 데이터를 전송할 수 있음
		//    session.getBasicRemote() -> 접속한 세션과 연결되는 스트림을 가져옴
		
		// 3. getBasicRemot로 가져온 객체의 sendText()라는 메서드 실행 -> 메세지를 클라이언트에게 전송
		Set<String> keys = clients.keySet();
		for (String key : keys) {
			Session s = clients.get(key);
			
			String [] clientData = (String [])s.getUserProperties().get("msg");
			
			if (clientData != null) {
				if (data[1].length() > 0) { // 수신자 정보를 제대로 입력했다면
					if (clientData[0].equals(data[1]) || clientData[0].equals(data[0])) {
						try {
							s.getBasicRemote().sendText(msg);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
}
