package org.usfirst.frc.team766.lib;

import java.util.HashMap;

public class logFactory {
	private static HashMap<String, logData> logs = new HashMap<String, logData>();
	
	public static logData getInstance(String key){
		return logs.get(key);
	}
	
	public static void createInstance(String key){
		logs.put(key, new logData(key));
	}
	
	public static void closeFile(String key){
		logs.get(key).closeFile();
		logs.remove(key);
	}
	
	public static void closeFiles(){
		for(String log : logs.keySet()){
			closeFile(log);
		}
	}
	
	public static HashMap<String, logData> getLogs(){
		return logs;
	}
}
