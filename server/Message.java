package server;

public class Message {
	private String id;
	private String msg;
	private String type;
	
	Message(){
		
	}
	
	public Message(String id, String msg, String type) {
		this.id = id;
		this.msg = msg;
		this.type = type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
