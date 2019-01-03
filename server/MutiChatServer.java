package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Logger;

import com.google.gson.Gson;

public class MutiChatServer {
	//server socket and client link socket
	private ServerSocket ss = null;
	private Socket s = null;
	
	ArrayList<ChatThread> chatThreads = new ArrayList<ChatThread>();
	
	Logger logger;
	
	public void start() {
		logger = Logger.getLogger(this.getClass().getName());
		
		try {
			ss = new ServerSocket(8888);
			logger.info("MultiChatServer start");
		
			while(true) {
				s = ss.accept();
				
				ChatThread chat = new ChatThread();
				
				chatThreads.add(chat);
				chat.start();
			}
		} catch(Exception e) {
			logger.info("[MultiChatServer]start() Exception 발생!!");
			e.printStackTrace();
		}
	}
	
	public class ChatThread extends Thread{
		String msg;
		
		Message m = new Message();
		
		Gson gson = new Gson();
		
		private BufferedReader inMsg = null;
		private PrintWriter outMsg = null;
		
		boolean status = true;
		
		public void run(){
			
			try {
				inMsg = new BufferedReader(new InputStreamReader(s.getInputStream()));
				outMsg = new PrintWriter(s.getOutputStream(), true);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			while(status) {
				try {
						msg = inMsg.readLine();
				} catch (IOException e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				m = gson.fromJson(msg, Message.class);
			
				if(m.getType().equals("msg")){
					msgSendAll(msg);
				}
				
				else{
					System.out.println(m.getId() + ", " +m.getType());
					msgSend(msg);
					
				}
			}
			this.interrupt();
			logger.info(this.getName() + " 종료됨!!");
		}
		
		void msgSendAll(String msg) {
			for(ChatThread ct : chatThreads) {
				if(ct != null)
				ct.outMsg.println(msg);
			}
		}
		void msgSend(String msg) {
			for(ChatThread ct : chatThreads) {
				if(ct != null)
					if(ct.m.getId().equals(m.getType()) || ct.m.getId() == m.getId()) {
					System.out.println(ct.m.getId() + ", " +m.getType());
					ct.outMsg.println(msg);
					}
			}
		}
	}
}
