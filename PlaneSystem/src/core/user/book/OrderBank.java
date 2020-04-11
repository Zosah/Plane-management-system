package core.user.book;


//订单信息类
public class OrderBank {
	private String orderNum;
	private String startCity;
	private String reachCity;
	private String startAirport;
	private String reachAirport;
	private String flightNum;
	private String date;
	private String startTime;
	private String reachTime;
	private String usedTime;
	private String phone;
	private String identity;
	private String name;
	private String jb_back;
	private String jb_alter;
	private String price;
	
	public OrderBank(String orderNum, String startCity, String reachCity, String startAirport, String reachAirport,
			String flightNum, String date, String startTime, String reachTime, String usedTime, String phone,
			String identity, String name, String jb_back, String jb_alter, String price) {
		super();
		this.orderNum = orderNum;
		this.startCity = startCity;
		this.reachCity = reachCity;
		this.startAirport = startAirport;
		this.reachAirport = reachAirport;
		this.flightNum = flightNum;
		this.date = date;
		this.startTime = startTime;
		this.reachTime = reachTime;
		this.usedTime = usedTime;
		this.phone = phone;
		this.identity = identity;
		this.name = name;
		this.jb_back = jb_back;
		this.jb_alter = jb_alter;
		this.price = price;
	}
	
	public OrderBank() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}

	
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getStartCity() {
		return startCity;
	}
	public void setStartCity(String startCity) {
		this.startCity = startCity;
	}
	public String getReachCity() {
		return reachCity;
	}
	public void setReachCity(String reachCity) {
		this.reachCity = reachCity;
	}
	public String getStartAirport() {
		return startAirport;
	}
	public void setStartAirport(String startAirport) {
		this.startAirport = startAirport;
	}
	public String getReachAirport() {
		return reachAirport;
	}
	public void setReachAirport(String reachAirport) {
		this.reachAirport = reachAirport;
	}
	public String getFlightNum() {
		return flightNum;
	}
	public void setFlightNum(String flightNum) {
		this.flightNum = flightNum;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getReachTime() {
		return reachTime;
	}
	public void setReachTime(String reachTime) {
		this.reachTime = reachTime;
	}
	public String getUsedTime() {
		return usedTime;
	}
	public void setUsedTime(String usedTime) {
		this.usedTime = usedTime;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJb_back() {
		return jb_back;
	}
	public void setJb_back(String jb_back) {
		this.jb_back = jb_back;
	}
	public String getJb_alter() {
		return jb_alter;
	}
	public void setJb_alter(String jb_alter) {
		this.jb_alter = jb_alter;
	}
	
}
