import java.util.ArrayList;
import java.util.List;


import com.intersys.globals.NodeReference;

import models.ClickStreamEvent;
import models.ElementCount;


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
		
		for (ElementCount elem : finder.getIndexValueCounts("elementIdIndex", 10))
		{
			System.out.println(elem);
			System.out.println(elem.count);
		}
		
	}
	


}
