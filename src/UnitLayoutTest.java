package sage;
/*
=====================================================================

	UnitLayoutTest.java

	Created by Claude Duguay
	Copyright (c) 1998

=====================================================================
*/

import java.awt.LayoutManager;

import javax.swing.JFrame;
import javax.swing.JButton;

public class UnitLayoutTest
{
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		frame.getContentPane().setLayout(
			new UnitLayout(UnitLayout.RIGHT, UnitLayout.CENTER));
		frame.getContentPane().add(new JButton("Component"));
		frame.setBounds(100, 100, 400, 400);
		frame.show();
	}
}
