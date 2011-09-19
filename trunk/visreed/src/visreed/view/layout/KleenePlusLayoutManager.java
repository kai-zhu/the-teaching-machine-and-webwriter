/**
 * KleenePlusLayoutManager.java
 * 
 * @date: 2011-6-14
 * @author: Xiaoyu Guo
 * This file is part of the Teaching Machine project.
 */
package visreed.view.layout;

import java.awt.geom.Rectangle2D;

import visreed.view.VisreedNodeView;

/**
 * KleenePlusLayoutManager handles children layout algorithms for {@link visreed.view.KleenePlusNodeView}
 * @author Xiaoyu Guo
 */
public class KleenePlusLayoutManager extends VisreedNodeLayoutManager {

    private static final double HSPACE_PIXEL = 20;
    /** top -> entry point margin */
    private static final double VSPACE_TOP_PIXEL = 0;
    /** loop -> bottom margin*/
    private static final double VSPACE_LOOP_BOTTOM_PIXEL = 10;
    private static final double VSPACE_BOTTOM_PIXEL = 20;
    protected KleenePlusLayoutManager() {}

    /* (non-Javadoc)
     * @see visreed.view.layout.VisreedNodeLayoutManager#layoutNode(visreed.view.VisreedNodeView)
     */
    @Override
    public void layoutNode(VisreedNodeView nv, double px, double py) {
        if(nv == null){
            return;
        }

        // TODO assert(nv.getNumChildren() == 1)
        VisreedNodeView kid = nv.getVisreedChild(0);
        
        double kidWidth = 0.0;
        double kidHeight = 0.0;
        if(kid != null){
            kid.doLayout();
            kidWidth = kid.getNextShapeExtent().getWidth();
            kidHeight = kid.getNextShapeExtent().getHeight();
        }
        
        nv.placeNext(px, py);
        
        kid.placeNextHierarchy(px + HSPACE_PIXEL, py + VSPACE_TOP_PIXEL);
        
        // set the padding
        Rectangle2D myNextExtent = nv.getNextShapeExtent();
        Rectangle2D.union(
            myNextExtent,
            new Rectangle2D.Double(
                px, 
                py, 
                kidWidth + 2 * HSPACE_PIXEL, 
                kidHeight + VSPACE_TOP_PIXEL + VSPACE_BOTTOM_PIXEL
            ),
            myNextExtent
        );
        nv.setNextShape(myNextExtent);
    }
    
    private static KleenePlusLayoutManager instance = new KleenePlusLayoutManager();
    
    /**
     * Gets the globally unique instance of this class. (Singleton Pattern)
     * @return
     */
    public static VisreedNodeLayoutManager getInstance() {
        return instance;
    }

    public static double getVSpaceTop(){
        return VSPACE_TOP_PIXEL;
    }
    public static double getVSpaceBottom() {
        return VSPACE_BOTTOM_PIXEL;
    }
    public static double getVSpaceLoopBottom() {
        return VSPACE_LOOP_BOTTOM_PIXEL;
    }
}