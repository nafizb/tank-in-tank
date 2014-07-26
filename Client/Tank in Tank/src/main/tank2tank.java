package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import controls.Keyboard;
import controls.Mouse;

public class tank2tank extends JFrame implements ActionListener {

	private static final long serialVersionUID = -7848127713413509525L;

	game game = new game(); 
	public static Keyboard keyListener = new Keyboard();
	public static Mouse mouseListener = new Mouse();
	public static void main(String argv[]) {
		new tank2tank();
	}
	
	public tank2tank() {
		
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        setIgnoreRepaint(true);
		setTitle("Tankish");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800, 600);
		setLocationRelativeTo(null);
		add(game);

		setVisible(true);
		setResizable(false);
		
		addKeyListener(new keyListener());
		
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				//System.out.println(me.getX());
			}
			
		});
		addMouseMotionListener(new MouseAdapter() {
			public void mouseMoved(MouseEvent me) {
				mouseListener.mouseMoved(me);
			}
		});
		
		new Thread(game).start();
		
	}
	private class keyListener extends KeyAdapter {

        public void keyReleased(KeyEvent e) {
            keyListener.keyReleased(e);
        }

        public void keyPressed(KeyEvent e) {
        	keyListener.keyPressed(e);
        }
       
    }
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
