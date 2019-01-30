package com.gams.utilities;

import java.util.Calendar;
import java.util.Date;

public class ProcessCalender {
	private int curDate, curYear;
	private String curMonth, curTime;
	private static ProcessCalender instance = null;
	private Calendar now;
	private Date time;
	
	private ProcessCalender() {
		now = Calendar.getInstance();
		time=new Date();
		curYear = now.get(Calendar.YEAR);
		curDate = now.get(Calendar.DAY_OF_MONTH);
		int monthIndex = now.get(Calendar.MONTH);
		curMonth = getMonthNameOf(monthIndex);
	}
	
	public String getCurTime() {
		time=now.getTime();
		curTime = time.toString();
		curTime=curTime.substring(10, 16);
		return curTime;
	}
	
	private String getMonthNameOf(int monthIndex) {
		String monthName;
		switch (monthIndex) {
		case 0:
			monthName="January";
			break;
		case 1:
			monthName="February";
			break;
		case 2:
			monthName="March";
			break;
		case 3:
			monthName="April";
			break;
		case 4:
			monthName="May";
			break;
		case 5:
			monthName="June";
			break;
		case 6:
			monthName="July";
			break;
		case 7:
			monthName="August";
			break;
		case 8:
			monthName="September";
			break;
		case 9:
			monthName="October";
			break;
		case 10:
			monthName="November";
			break;
		case 11:
			monthName="December";
			break;
		default:
			monthName="";
			break;
		}
		return monthName;
	}


	public static ProcessCalender getInstance() {
		if(instance==null){
			return new ProcessCalender();
		}else{
			return instance;
		}
	}
	
	public int getCurDate() {
		return curDate;
	}
	
	public String getCurMonth() {
		return curMonth;
	}
	
	public int getCurYear() {
		return curYear;
	}
}
