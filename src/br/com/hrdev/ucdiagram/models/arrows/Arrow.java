package br.com.hrdev.ucdiagram.models.arrows;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import br.com.hrdev.ucdiagram.libraries.ConnectLine;
import br.com.hrdev.ucdiagram.models.Element;
import br.com.hrdev.ucdiagram.models.figures.Figure;

public abstract class Arrow extends Element {
	
	private static final long serialVersionUID = 1L;
	private ConnectLine line = null;
	private int lineType;
	private int lineArrow;
	
	protected Figure start = null;
	protected Figure end = null;
	protected String lineNome = null;
	
	public Arrow(String lineNome, int lineType, int lineArrow){
		super();
		this.lineNome = lineNome;
		this.lineType = lineType;
		this.lineArrow = lineArrow;
	}
	
	public void configure(Figure start, Figure end){
		this.start = start;
		this.end = end;
		this.update();
	}
	
	public void update(){
		Rectangle startBounds = start.getRectangle();
		Rectangle endBounds = end.getRectangle();
		
		if(startBounds.intersects(endBounds)){
			line = null;
			return;
		}
		
		boolean xIntersect = xIntersect(startBounds,endBounds);
		boolean yIntersect = yIntersect(startBounds,endBounds);
		
		if(xIntersect){
			line = xIntersectLine(startBounds,endBounds);
		} else if(yIntersect){
			line = yIntersectLine(startBounds,endBounds);
		} else {
			line = intersectLine(startBounds,endBounds);
		}
	}
	
	private boolean xIntersect(Rectangle startBounds, Rectangle endBounds){
		return (startBounds.x <= endBounds.x && startBounds.x + startBounds.width >= endBounds.x) || (endBounds.x <= startBounds.x && endBounds.x + endBounds.width >= startBounds.x);
	}
	
	private boolean yIntersect(Rectangle startBounds, Rectangle endBounds){
		return (startBounds.y <= endBounds.y) && (startBounds.y + startBounds.height >= endBounds.y) || (endBounds.y <= startBounds.y && endBounds.y + endBounds.height >= startBounds.y);
	}
	
	private ConnectLine xIntersectLine(Rectangle startBounds, Rectangle endBounds){
		int y1, y2;
	    int x1 = startBounds.x + startBounds.width / 2;
	    int x2 = endBounds.x + endBounds.width / 2;
	    
	    if (startBounds.y + startBounds.height <= endBounds.y){
	        y1 = startBounds.y + startBounds.height;
	        y2 = endBounds.y;
	    } else {
	        y1 = startBounds.y;
	        y2 = endBounds.y + endBounds.height;
	    }
	    
	    ConnectLine line = new ConnectLine(new Point(x1, y1), new Point(x2, y2), ConnectLine.LINE_TYPE_RECT_2BREAK, ConnectLine.LINE_START_VERTICAL, lineArrow);
	    
	    if (lineType == ConnectLine.CONNECT_LINE_TYPE_SIMPLE) {
	        line.setLineType(ConnectLine.LINE_TYPE_SIMPLE);
	    }
	   
	    return line;
	}
	
	private ConnectLine yIntersectLine(Rectangle startBounds, Rectangle endBounds){
		 int y1 = startBounds.y + startBounds.height / 2;
         int y2 = endBounds.y + endBounds.height / 2;
         int x1, x2;
         
         if (startBounds.x + startBounds.width <= endBounds.x) {
             x1 = startBounds.x + startBounds.width;
             x2 = endBounds.x;
         } else {
             x1 = startBounds.x;
             x2 = endBounds.x + endBounds.width;
         }
         
         ConnectLine line = new ConnectLine(new Point(x1, y1), new Point(x2, y2), ConnectLine.LINE_TYPE_RECT_2BREAK, ConnectLine.LINE_START_HORIZONTAL, lineArrow);
         
         if (lineType == ConnectLine.CONNECT_LINE_TYPE_SIMPLE) {
        	 line.setLineType(ConnectLine.LINE_TYPE_SIMPLE);
         }
         
         return line;
	}
	
	private ConnectLine intersectLine(Rectangle startBounds, Rectangle endBounds){
		int y1, y2, x1, x2;
		
        if (startBounds.y + startBounds.height <= endBounds.y) {
            y1 = startBounds.y + startBounds.height / 2;
            y2 = endBounds.y;
            if (startBounds.x + startBounds.width <= endBounds.x) {
                x1 = startBounds.x + startBounds.width;
            } else {
                x1 = startBounds.x;
            }
            x2 = endBounds.x + endBounds.width / 2;
        } else {
            y1 = startBounds.y + startBounds.height / 2;
            y2 = endBounds.y + endBounds.height;
            if (startBounds.x + startBounds.width <= endBounds.x) {
                x1 = startBounds.x + startBounds.width;
            } else {
                x1 = startBounds.x;
            }
            x2 = endBounds.x + endBounds.width / 2;
        }
        
        ConnectLine line = new ConnectLine(new Point(x1, y1), new Point(x2, y2), ConnectLine.LINE_TYPE_RECT_1BREAK, ConnectLine.LINE_START_HORIZONTAL, lineArrow);
        
        if (lineType == ConnectLine.CONNECT_LINE_TYPE_SIMPLE) {
            line.setLineType(ConnectLine.LINE_TYPE_SIMPLE);
        }
		
        return line;
	}
	
	public void paint(Graphics2D g){
		line.paint(g);
	}
	
	@Override
	public String toString(){
		return "Arrow " + lineNome + "(" + start + "," + end + ")";
	}
}
