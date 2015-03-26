package sage;

import javax.swing.UIManager;

public class PLAF
{
	public static void setNativeLookAndFeel(boolean nativeLAF)
	{
		try
		{
			String plaf;
			if (nativeLAF)
			{
				plaf = UIManager.getSystemLookAndFeelClassName();
			}
			else
			{
				plaf = UIManager.getCrossPlatformLookAndFeelClassName();
			}
			UIManager.setLookAndFeel(plaf);
		}
		catch (Exception e)
		{
			System.out.println("Error loading Look and Feel");
		}
	}
}
