package facilities;

import java.util.ArrayList;

public class Schedule {

	private ArrayList<Integer> available;
	
	public Schedule(ArrayList<Integer> newAvailable)
	{
		available = newAvailable;
	}

	public Schedule(Schedule schedule)
	{
		available = schedule.getAvailable();
	}
	
	public ArrayList<Integer> getAvailable()
	{
		return new ArrayList<>(available);
	}


	public void bookDays(Integer quantity, int itemsPerDay, Integer startDay)
	{
		if(startDay == null || quantity == null)
		{
			throw new NullPointerException();
		}

		int index = startDay;

			while(quantity > 0)
			{
				if(index>=available.size())
				{
					for(int i= 0; i<=(index-available.size()); i++)
					{
						available.add(itemsPerDay);
					}
				}

				if(!available.get(index).equals(0))
				{

					if(available.get(index) > quantity)
					{
						quantity = 0;
						available.set(index, available.get(index)-quantity);
					}

					else
					{
						quantity = quantity - available.get(index);
						available.set(index, 0);
					}

				}

				index++;

			}
	}



	public int expectedShipDate(Integer quantity, int itemsPerDay, Integer startDay)
	{
		if(quantity == null|| startDay==null)
		{
			throw new NullPointerException();
		}

		int dayProcessed = 0;
		int index = startDay;


		while(quantity >= 0)
		{
			if(index>=available.size())
			{
					available.add(itemsPerDay);
			}

			if(!available.get(index).equals(0))
			{
				quantity = quantity - available.get(index);
			}

			dayProcessed = index;
			index++;

		}


		return dayProcessed;

	}



	public int daysToProcess(Integer quantity, int itemsPerDay, Integer startDay)
	{

		if(quantity == null|| startDay ==null)
		{
			throw new NullPointerException();
		}

		int index = startDay;

		int daysToProcess = 0;

		while(quantity>=0)
		{

			if(index>=available.size())
			{
					available.add(itemsPerDay);
			}

			if(!(available.get(index)).equals(0))
			{
				quantity = quantity - available.get(index);

				daysToProcess++;
			}

			index++;

		}

		return daysToProcess;

	}

	public int scheduleLength()
	{
		return available.size();
	}


}
