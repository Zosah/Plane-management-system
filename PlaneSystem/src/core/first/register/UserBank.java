package core.first.register;

/**
 * -用户信息类
 * @author 陌生人
 *
 */
public class UserBank {
	
	private String username;
	private String password1;
	private String password2;
	private String name;
	private String id;
	private String phone;
	

	public UserBank(String username, String password1, String password2, String name, String id, String phone) {
		super();
		this.username = username;
		this.password1 = password1;
		this.password2 = password2;
		this.name = name;
		this.id = id;
		this.phone = phone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
