package falleight.isft;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ConnectionISFT {
	static final String URL = "jdbc:mysql://160.16.119.180/users?useUnicode=true&characterEncoding=utf8";
//	static final String URL = "jdbc:mysql://localhost:3306/test";
	static final String USERNAME = "4j";
	static final String PASSWORD = "4jpass";

	public Connection connection;
	public Statement statement;
	public StockData data;

	public ConnectionISFT() {
		this.connection = null;
	}

	public void connectDatabase() {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			this.statement = this.connection.createStatement();
			System.out.println("success");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("end of program.");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	} 

	//入力したメールアドレス、パスワードがあっていればtrueを返す
	public boolean loginISFT(String email, String password, String type) throws SQLException {
		String str = String.format("SELECT * FROM isft where email = '%s' and password = '%s' and type = '%s';", email, password, type);
		ResultSet result = this.statement.executeQuery(str);
		//List stockList = new ArrayList();
		this.data = new StockData();

		result.last();
		int numberOfRow = result.getRow();
		if (numberOfRow == 1) {	//メールアドレス、パスワードが一致するものが1つなら	
			result.beforeFirst();
			while (result.next()) {
				this.data.setId(result.getInt(1));
				this.data.setName(result.getString(2));
				this.data.setRoomNumber(result.getString(3));
				this.data.setStatus(result.getString(4));
				this.data.setWord(result.getString(5));
				this.data.setEmail(result.getString(6));
				this.data.setPassword(result.getString(7));
				this.data.setType(result.getString(8));
				//stockList.add(data);
			}

			if (this.data.getEmail().equals(email) && this.data.getPassword().equals(password)) {
				return true;
			}
		} else {
			System.out.println("error");
		}
		
		/*StockData data2;
		for (int i = 0; i < stockList.size(); i++) {
			data2 = (StockData) stockList.get(i);
			System.out.print(data2.getId() + ":");
			System.out.print(data2.getEmail() + ":");
			System.out.print(data2.getPassword() + ":");
			System.out.print(data2.getName() + ":");
			System.out.println(data2.getStatus());
		}*/

		return false;
	}

	//在室状況を更新
	public void updateStatus(String nextStatus) throws SQLException {
		int num = 0;
		String str = String.format("UPDATE isft SET status = '%s' WHERE email = '%s' and type = 'teacher';", nextStatus, this.data.getEmail());

		num = this.statement.executeUpdate(str);
		
		if (num > 0) {
			String str2 = String.format("SELECT * FROM isft where email = '%s' and password = '%s' and type = 'teacher';", this.data.getEmail(), this.data.getPassword());
			ResultSet result = this.statement.executeQuery(str2);
			//List stockList = new ArrayList();
			this.data = new StockData();

			result.last();
			int numberOfRow = result.getRow();
			if (numberOfRow == 1) {	//メールアドレス、パスワードが一致するものが1つなら	
				result.beforeFirst();
				while (result.next()) {
					this.data.setId(result.getInt(1));
					this.data.setName(result.getString(2));
					this.data.setRoomNumber(result.getString(3));
					this.data.setStatus(result.getString(4));
					this.data.setWord(result.getString(5));
					this.data.setEmail(result.getString(6));
					this.data.setPassword(result.getString(7));
					this.data.setType(result.getString(8));
					//stockList.add(data);
				}
			}

			/*StockData data2;
			for (int i = 0; i < stockList.size(); i++) {
				data2 = (StockData) stockList.get(i);
				System.out.print(data2.getId() + ":");
				System.out.print(data2.getEmail() + ":");
				System.out.print(data2.getPassword() + ":");
				System.out.print(data2.getName() + ":");
				System.out.println(data2.getStatus());
			}*/

			System.out.println("updating is success.");
		}
	}

	public void updateWord(String nextWord) throws SQLException {
		int num = 0;
		String str = String.format("UPDATE isft SET word = '%s' WHERE email = '%s' and type = 'teacher';", nextWord, this.data.getEmail());

		num = this.statement.executeUpdate(str);

		if (num > 0) {
			String str2 = String.format("SELECT * FROM isft where email = '%s' and password = '%s' and type = 'teacher';", this.data.getEmail(), this.data.getPassword());
			ResultSet result = this.statement.executeQuery(str2);
			//List stockList = new ArrayList();
			this.data = new StockData();

			result.last();
			int numberOfRow = result.getRow();
			if (numberOfRow == 1) {	//メールアドレス、パスワードが一致するものが1つなら
				result.beforeFirst();
				while (result.next()) {
					this.data.setId(result.getInt(1));
					this.data.setName(result.getString(2));
					this.data.setRoomNumber(result.getString(3));
					this.data.setStatus(result.getString(4));
					this.data.setWord(result.getString(5));
					this.data.setEmail(result.getString(6));
					this.data.setPassword(result.getString(7));
					this.data.setType(result.getString(8));
					//stockList.add(data);
				}
			}

			/*StockData data2;
			for (int i = 0; i < stockList.size(); i++) {
				data2 = (StockData) stockList.get(i);
				System.out.print(data2.getId() + ":");
				System.out.print(data2.getEmail() + ":");
				System.out.print(data2.getPassword() + ":");
				System.out.print(data2.getName() + ":");
				System.out.println(data2.getStatus());
			}*/

			System.out.println("updating is success.");
		}
	}

	//データベースのISFT教員データを全て取得
	public List getAllTeachersData() throws SQLException {
		String str = String.format("SELECT * FROM isft WHERE type = 'teacher';");
		ResultSet result = this.statement.executeQuery(str);
		List stockList = new ArrayList();

		while (result.next()) {
			this.data = new StockData();
			this.data.setId(result.getInt(1));
			this.data.setName(result.getString(2));
			this.data.setRoomNumber(result.getString(3));
			this.data.setStatus(result.getString(4));
			this.data.setWord(result.getString(5));
			this.data.setEmail(result.getString(6));
			this.data.setPassword(result.getString(7));
			this.data.setType(result.getString(8));
			stockList.add(data);
		}

		return stockList;

		/*StockData data2;
		for (int i = 0; i < stockList.size(); i++) {
			data2 = (StockData) stockList.get(i);
			System.out.print(data2.getId() + ":");
			System.out.print(data2.getName() + ":");
			System.out.print(data2.getRoomNumber() + ":");
			System.out.print(data2.getStatus() + ":");
			System.out.print(data2.getEmail() + ":");
			System.out.print(data2.getPassword() + ":");
			System.out.println(data2.getType());
		}*/
	}

	public String getStatusFromDatabase(String email, String password) throws SQLException {
		String str = String.format("SELECT * FROM isft WHERE email = '%s' and password = '%s' and type = 'teacher';", email, password);
		ResultSet result = this.statement.executeQuery(str);
		result.last();
		return result.getString(4);
	}

	public String getWordFromDatabase(String email, String password) throws SQLException {
		String str = String.format("SELECT * FROM isft WHERE email = '%s' and password = '%s' and type = 'teacher';", email, password);
		ResultSet result = this.statement.executeQuery(str);
		result.last();
		return result.getString(5);
	}
}