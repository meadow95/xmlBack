package postservice.requestmodel;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Request")
public class Request {
    @Id
    private String id;
    private String sender;
    private String receiver;

    public Request(String sender, String receiver) {
        this.sender = sender;
        this.receiver = receiver;
    }

    public Request() {

    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}


}
