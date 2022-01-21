package org.halloumi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.NamedEvent;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.animation.Animation;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import metier.beans.Position;
import metier.beans.SmartPhone;
import metier.beans.User;
import metier.positionEjb.PositionLocal;
import metier.smartphoneejb.SmartPhoneLocal;
import metier.userejb.UserLocal;

@ManagedBean(name = "Main", eager = true)
@SessionScoped
@NamedEvent
public class Main implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public String message;
	private MapModel simpleModel;
	private String userEmail;
	private List<String> usersEmail;
	private List<String> mounths;
	private String mounth;
	private BarChartModel barModel;
	private BarChartModel barModel1;
	public List<User> allUsers;
	public List<SmartPhone> allPhones;
	public List<Position> allPositions;
	public User selectedUser;
	@EJB
	public UserLocal userLocal;
	@EJB
	public SmartPhoneLocal smartPhoneLocal;
	@EJB
	public PositionLocal positionLocal;

	public String getMessage() {
		String allmails = "";
		for (User u : userLocal.findAll()) {
			allmails = allmails + u.getEmail();
		}
		return allmails;
	}


	public void onUserChange () {
		this.simpleModel = new DefaultMapModel();
		for (User u : allUsers) {
			if (u.getEmail().equals(userEmail)) {
				for (SmartPhone sp : u.getSmartPhones()) {
					for (Position p : sp.getPosition()) {
						this.simpleModel.addOverlay(new Marker(new LatLng(p.getLatitude(), p.getLongitude()), ""));
					}
				}
			}
		}
	}

	public void onSubmit1 () {

	}

	public String getUserEmail() {
		return userEmail;
	}




	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}




	public List<String> getUsersEmail() {
		return usersEmail;
	}




	public void setUsersEmail(List<String> usersEmail) {
		this.usersEmail = usersEmail;
	}




	public MapModel getSimpleModel() {
		return simpleModel;
	}



	public void setSimpleModel(MapModel simpleModel) {
		this.simpleModel = simpleModel;
	}



	public BarChartModel getBarModel1() {
		return barModel1;
	}

	public void setBarModel1(BarChartModel barModel1) {
		this.barModel1 = barModel1;
	}

	public List<User> getAllUsers() {
		return allUsers;
	}

	public void setAllUsers(List<User> allUsers) {
		this.allUsers = allUsers;
	}

	public User getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}

	public UserLocal getUserLocal() {
		return userLocal;
	}

	public void setUserLocal(UserLocal userLocal) {
		this.userLocal = userLocal;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public SmartPhoneLocal getSmartPhoneLocal() {
		return smartPhoneLocal;
	}

	public void setSmartPhoneLocal(SmartPhoneLocal smartPhoneLocal) {
		this.smartPhoneLocal = smartPhoneLocal;
	}

	public List<SmartPhone> getAllPhones() {
		return allPhones;
	}

	public void setAllPhones(List<SmartPhone> allPhones) {
		this.allPhones = allPhones;
	}

	public List<Position> getAllPositions() {
		return allPositions;
	}

	public void setAllPositions(List<Position> allPositions) {
		this.allPositions = allPositions;
	}

	public PositionLocal getPositionLocal() {
		return positionLocal;
	}

	public void setPositionLocal(PositionLocal positionLocal) {
		this.positionLocal = positionLocal;
	}

	public BarChartModel getBarModel() {
		return barModel;
	}

	public void setBarModel(BarChartModel barModel) {
		this.barModel = barModel;
	}

	public List<String> getMounths() {
		return mounths;
	}

	public void setMounths(List<String> mounths) {
		this.mounths = mounths;
	}

	public String getMounth() {
		return mounth;
	}

	public void setMounth(String mounth) {
		this.mounth = mounth;
	}

	public void createBarModel() {
		barModel = new BarChartModel();
		ChartData data = new ChartData();

		BarChartDataSet barDataSet = new BarChartDataSet();
		barDataSet.setLabel("Nombre de Position par Smart Phone");

		List<Number> values = new ArrayList<>();
		for (SmartPhone sp : allPhones) {
			values.add(sp.getPosition().size());
		}
		barDataSet.setData(values);
		List<String> bgColor = new ArrayList<>();
		bgColor.add("rgba(255, 99, 132, 0.2)");
		bgColor.add("rgba(255, 159, 64, 0.2)");
		bgColor.add("rgba(255, 205, 86, 0.2)");
		bgColor.add("rgba(75, 192, 192, 0.2)");
		bgColor.add("rgba(54, 162, 235, 0.2)");
		bgColor.add("rgba(153, 102, 255, 0.2)");
		bgColor.add("rgba(201, 203, 207, 0.2)");
		barDataSet.setBackgroundColor(bgColor);

		List<String> borderColor = new ArrayList<>();
		borderColor.add("rgb(255, 99, 132)");
		borderColor.add("rgb(255, 159, 64)");
		borderColor.add("rgb(255, 205, 86)");
		borderColor.add("rgb(75, 192, 192)");
		borderColor.add("rgb(54, 162, 235)");
		borderColor.add("rgb(153, 102, 255)");
		borderColor.add("rgb(201, 203, 207)");
		barDataSet.setBorderColor(borderColor);
		barDataSet.setBorderWidth(1);

		data.addChartDataSet(barDataSet);

		List<String> labels = new ArrayList<>();
		for (SmartPhone sp1 : allPhones) {
			labels.add(sp1.getName());
		}
		data.setLabels(labels);
		barModel.setData(data);

		// Options
		BarChartOptions options = new BarChartOptions();
		CartesianScales cScales = new CartesianScales();
		CartesianLinearAxes linearAxes = new CartesianLinearAxes();
		linearAxes.setOffset(true);
		CartesianLinearTicks ticks = new CartesianLinearTicks();
		ticks.setBeginAtZero(true);
		linearAxes.setTicks(ticks);
		cScales.addYAxesData(linearAxes);
		options.setScales(cScales);

		Title title = new Title();
		title.setDisplay(true);
		title.setText("Bar Chart");
		options.setTitle(title);

		Legend legend = new Legend();
		legend.setDisplay(true);
		legend.setPosition("top");
		LegendLabel legendLabels = new LegendLabel();
		legendLabels.setFontStyle("bold");
		legendLabels.setFontColor("#2980B9");
		legendLabels.setFontSize(24);
		legend.setLabels(legendLabels);
		options.setLegend(legend);

		// disable animation
		Animation animation = new Animation();
		animation.setDuration(0);
		options.setAnimation(animation);

		barModel.setOptions(options);
	}

	public void createBarModel1() {
		barModel1 = new BarChartModel();
		ChartData data = new ChartData();

		BarChartDataSet barDataSet = new BarChartDataSet();
		barDataSet.setLabel("Nombre de Position par Smart Phone par Mois");

		List<Number> values = new ArrayList<>();
		barDataSet.setData(values);
		List<String> bgColor = new ArrayList<>();
		bgColor.add("rgba(255, 99, 132, 0.2)");
		bgColor.add("rgba(255, 159, 64, 0.2)");
		bgColor.add("rgba(255, 205, 86, 0.2)");
		bgColor.add("rgba(75, 192, 192, 0.2)");
		bgColor.add("rgba(54, 162, 235, 0.2)");
		bgColor.add("rgba(153, 102, 255, 0.2)");
		bgColor.add("rgba(201, 203, 207, 0.2)");
		barDataSet.setBackgroundColor(bgColor);

		List<String> borderColor = new ArrayList<>();
		borderColor.add("rgb(255, 99, 132)");
		borderColor.add("rgb(255, 159, 64)");
		borderColor.add("rgb(255, 205, 86)");
		borderColor.add("rgb(75, 192, 192)");
		borderColor.add("rgb(54, 162, 235)");
		borderColor.add("rgb(153, 102, 255)");
		borderColor.add("rgb(201, 203, 207)");
		barDataSet.setBorderColor(borderColor);
		barDataSet.setBorderWidth(1);

		data.addChartDataSet(barDataSet);

		List<String> labels = new ArrayList<>();
		for (SmartPhone sp1 : allPhones) {
			labels.add(sp1.getName());
		}
		data.setLabels(labels);
		barModel1.setData(data);

		// Options
		BarChartOptions options = new BarChartOptions();
		CartesianScales cScales = new CartesianScales();
		CartesianLinearAxes linearAxes = new CartesianLinearAxes();
		linearAxes.setOffset(true);
		CartesianLinearTicks ticks = new CartesianLinearTicks();
		ticks.setBeginAtZero(true);
		linearAxes.setTicks(ticks);
		cScales.addYAxesData(linearAxes);
		options.setScales(cScales);

		Title title = new Title();
		title.setDisplay(true);
		title.setText("Bar Chart");
		options.setTitle(title);

		Legend legend = new Legend();
		legend.setDisplay(true);
		legend.setPosition("top");
		LegendLabel legendLabels = new LegendLabel();
		legendLabels.setFontStyle("bold");
		legendLabels.setFontColor("#2980B9");
		legendLabels.setFontSize(24);
		legend.setLabels(legendLabels);
		options.setLegend(legend);

		// disable animation
		Animation animation = new Animation();
		animation.setDuration(0);
		options.setAnimation(animation);

		barModel1.setOptions(options);
	}

	@SuppressWarnings("deprecation")
	public void onSubmit() {

	}

	public void onMounthChange() {
		if (mounth != null) {
			List<Number> values = new ArrayList<>();
			for (SmartPhone sp : allPhones) {
				int i = 0;
				for (Position p : sp.getPosition()) {
					if (p.getDate().getMonth() == getValueOfMounth(mounth)) {
						i++;
					}
				}
				values.add(i);
			}
			ChartData data = new ChartData();
			List<String> labels = new ArrayList<>();
			for (SmartPhone sp1 : allPhones) {
				labels.add(sp1.getName());
			}
			data.setLabels(labels);
			BarChartDataSet barDataSet = new BarChartDataSet();
			barDataSet.setLabel("Nombre de Position par Smart Phone au mois " + mounth);
			barDataSet.setData(values);
			data.addChartDataSet(barDataSet);
			barModel1.setData(data);
		}
	}

	public int getValueOfMounth(String mounth) {
		switch (mounth) {
		case "January":
			return 0;
		case "February":
			return 1;
		case "March":
			return 2;
		case "April":
			return 3;
		case "May":
			return 4;
		case "June":
			return 5;
		case "July":
			return 6;
		case "August":
			return 7;
		case "September":
			return 8;
		case "October":
			return 9;
		case "November":
			return 10;
		case "December":
			return 11;
		default:
			return -1;
		}
	}

	public void initMap() {
		simpleModel = new DefaultMapModel();

	}

	public MobMain() {
		super();
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void ngOnInit() {
		this.allUsers = userLocal.findAll();
		this.allPhones = smartPhoneLocal.findAll();
		this.allPositions = positionLocal.findAll();
		this.mounths = new ArrayList<String>();
		mounths.add("January");
		mounths.add("February");
		mounths.add("March");
		mounths.add("April");
		mounths.add("May");
		mounths.add("June");
		mounths.add("July");
		mounths.add("August");
		mounths.add("September");
		mounths.add("October");
		mounths.add("November");
		mounths.add("December");
		this.usersEmail = new ArrayList<String>();
		for (User u : this.allUsers) {
			this.usersEmail.add(u.getEmail());
		}
		createBarModel();
		createBarModel1();
		initMap();
	}

}