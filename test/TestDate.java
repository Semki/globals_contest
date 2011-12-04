import java.util.ArrayList;

import com.intersys.globals.NodeReference;

import models.ClickStreamEvent;


import core.ConnectionManager;
import core.DataFinder;
import core.DataWorker;
import core.DateHelper;


public class TestDate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DataFinder finder = new DataFinder(ClickStreamEvent.class);
		finder.getAddedIdsSinceId(27l);
		
	}
	


}
