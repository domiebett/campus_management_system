package campus.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import campus.ui.layout.HeaderPanel;
import campus.ui.layout.SidePanel;

public class LayoutUI extends JPanel {
	
	private static HeaderPanel headerPanel;
	private static SidePanel sidePanel;
	private static JPanel contentPanel;
	
	private Integer maxWidth = Integer.MAX_VALUE;
	private Integer headerHeight = 50;
	private Integer bodyHeight = Integer.MAX_VALUE - headerHeight;
	private Integer sideWidth = 300;
	private Integer contentWidth = Integer.MAX_VALUE - sideWidth;
	
	
	public LayoutUI() {
		setLayout(new BorderLayout());
		
		headerPanel = new HeaderPanel();
		headerPanel.setPreferredSize(new Dimension(maxWidth, headerHeight));
		headerPanel.setBackground(Color.CYAN);
		
		sidePanel = new SidePanel();
		sidePanel.setPreferredSize(new Dimension(sideWidth, bodyHeight));
		sidePanel.setBackground(Color.BLUE);
		
		contentPanel = new ContentPanel();
		contentPanel.setPreferredSize(new Dimension(contentWidth, bodyHeight));
		contentPanel.setBackground(Color.YELLOW);
		
		add(sidePanel, BorderLayout.LINE_START);
		add(headerPanel, BorderLayout.NORTH);
		add(contentPanel, BorderLayout.CENTER);
	}
	
}
