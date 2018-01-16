package com.lmf.holidays.easter.main;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JOptionPane;

import com.lmf.holidays.Calendars;
import com.lmf.holidays.easter.util.Easter;
import com.lmf.holidays.exceptions.InvalidCalendarsException;


public class MainClass {
	private static boolean useJOption = false;

	public static void main(String[] args) {
		
		String year = null;
		boolean isGregorian = true;
		boolean inError = false;
		
		if(args.length>0 && (args[0].equalsIgnoreCase("-year") || args[0].equalsIgnoreCase("-y"))){
			if(args.length>1){
				year = args[1];
				if(args.length>2 && (args[2].equalsIgnoreCase("-calendar") || args[2].equalsIgnoreCase("-c"))){
					if(args.length>3 && args[3].equalsIgnoreCase("giuliano")){
						isGregorian = false;
					}
				}
			}else{
				showMessage("Non è possibile calcolare la Pasqua senza specificare l'anno!");
				inError = true;
			}
		}else{
			useJOption = true;
			year = JOptionPane.showInputDialog("Inserisci l'anno:");
		}
		
		if(!inError){
			int intYear = -1;
			try{
				intYear = Integer.parseInt(year);
				if(intYear<1){
					showMessage("Non è possibile calcolare la Pasqua per l'anno " + year);
				}else{
					Calendar easter = null;
					if(isGregorian){
						easter = Easter.calcola(intYear, Calendars.GREGORIAN);
					}else{
						easter = Easter.calcola(intYear, Calendars.GIULIAN);
					}
					if(easter!=null){
						SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy hh:mm:ss:sss");
						showMessage("Il giorno di Pasqua cadrà il " + sdf.format(easter.getTime()));
					}
				}
			}catch(NumberFormatException e){
				showMessage("L'anno inserito non è corretto: " + year);			
			} catch (InvalidCalendarsException e) {
				showMessage("InvalidCalendarsException");
			}
		}
	}
	
	public static void showMessage(String message){
		if(useJOption){
			JOptionPane.showMessageDialog(null, message);
		}else{
			System.out.println(message);
		}
	}

}
