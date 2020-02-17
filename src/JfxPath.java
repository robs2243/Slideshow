//import javafx.scene.canvas.GraphicsContext;
//import javafx.scene.shape.ClosePath;
//import javafx.scene.shape.CubicCurveTo;
//import javafx.scene.shape.LineTo;
//import javafx.scene.shape.MoveTo;
//import javafx.scene.shape.Path;
//import javafx.scene.shape.PathElement;
//
//
///**
// * Draws a {@link Path} on a JavaFX {@link GraphicsContext}.
// * 
// * @author Andreas Wenger
// */
//public class JfxPath {
//	
//	public static void drawPath(Path path, GraphicsContext context) {
//		context.beginPath();
//		for (PathElement e : path.getElements()) {
//			switch (e.getType()) {
//				case ClosePath:
//					context.closePath();
//					break;
//				case CubicCurveTo:
//					CubicCurveTo c = (CubicCurveTo) e;
//					context.bezierCurveTo(c.cp1.x, c.cp1.y, c.cp2.x, c.cp2.y, c.p.x, c.p.y);
//					break;
//				case LineTo:
//					LineTo l = (LineTo) e;
//					context.lineTo(l.p.x, l.p.y);
//					break;
//				case MoveTo:
//					MoveTo m = (MoveTo) e;
//					context.moveTo(m.p.x, m.p.y);
//					break;
//				
//			}
//		}
//	}
//
//}