package top.lcmatrix.util.codegenerator;

import javax.swing.SwingUtilities;

import top.lcmatrix.util.codegenerator.gui.MainWindow;

public class App{
	
	public static void main( String[] args ) throws Exception
    {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				MainWindow.getInstance().setVisible(true);
			}
		});
    }
}
