package falleight.isft;

public class StockData {
	private int id;
	private String email;
	private String password;
	private String name;
	private String status;
	private String roomNumber;
	private String type;
	private String word;

	public int getId() {
		return this.id;
	}

	public void setId(int newId) {
		this.id = newId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String newEmail) {
		this.email = newEmail;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String newPassword) {
		this.password = newPassword;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String newName) {
		this.name = newName;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String newStatus) {
		this.status = newStatus;
	}

	public String getRoomNumber() {
		return this.roomNumber;
	}

	public void setRoomNumber(String newRoomNumber) {
		this.roomNumber = newRoomNumber;
	}

	public String getType() { return this.type; }

	public void setType(String newType) {this.type = newType; }

	public String getWord() { return this.word; }

	public void setWord(String newWord) {this.word = newWord; }
}