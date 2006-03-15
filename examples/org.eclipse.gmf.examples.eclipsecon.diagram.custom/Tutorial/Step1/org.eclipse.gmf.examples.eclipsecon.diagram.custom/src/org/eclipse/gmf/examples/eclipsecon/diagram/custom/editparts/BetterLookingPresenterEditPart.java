package org.eclipse.gmf.examples.eclipsecon.diagram.custom.editparts;

import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.gmf.examples.eclipsecon.diagram.custom.Activator;
import org.eclipse.gmf.examples.eclipsecon.diagram.edit.parts.PresenterEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.draw2d.ui.render.RenderedImage;
import org.eclipse.gmf.runtime.draw2d.ui.render.factory.RenderedImageFactory;
import org.eclipse.gmf.runtime.draw2d.ui.render.figures.ScalableImageFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.SlidableImageAnchor;
import org.eclipse.gmf.runtime.gef.ui.figures.WrapperNodeFigure;
import org.eclipse.gmf.runtime.notation.View;


public class BetterLookingPresenterEditPart extends PresenterEditPart {
    
    
    public BetterLookingPresenterEditPart(View view) {
        super(view);
        // TODO Auto-generated constructor stub
    }

    private static final String TRANSLATE_PATH_ARGUMENT = "$nl$"; //$NON-NLS-1$
 
    /* 
     * (non-Javadoc)
     * @see org.eclipse.gmf.examples.eclipsecon.diagram.edit.parts.PresenterEditPart#createNodeFigure()
     */
    protected NodeFigure createNodeFigure() {    
        
        // assume default;
        IPath path =
             new Path(TRANSLATE_PATH_ARGUMENT).append(
                 "images" + IPath.SEPARATOR + "presenter.svg"); //$NON-NLS-1$ //$NON-NLS-2$
        URL presenterURL = FileLocator.find(Activator.getDefault().getBundle(), path, null);
        
        RenderedImage rndImg = RenderedImageFactory.getInstance(presenterURL);
        final ScalableImageFigure sif = new ScalableImageFigure(rndImg, false, true, true);
        NodeFigure nf = new WrapperNodeFigure(sif) {
            /* (non-Javadoc)
             * @see org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure#createDefaultAnchor()
             */
            protected ConnectionAnchor createDefaultAnchor() {
                return new SlidableImageAnchor(this, sif);
            }
            
            /* (non-Javadoc)
             * @see org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure#createAnchor(org.eclipse.draw2d.geometry.PrecisionPoint)
             */
            protected ConnectionAnchor createAnchor(PrecisionPoint p) {
                if (p==null)
                    // If the old terminal for the connection anchor cannot be resolved (by SlidableAnchor) a null
                    // PrecisionPoint will passed in - this is handled here
                    return createDefaultAnchor();
                return new SlidableImageAnchor(this, sif, p);
            }
        };
        
        ConstrainedToolbarLayout myGenLayoutManager = new ConstrainedToolbarLayout();
        myGenLayoutManager.setStretchMinorAxis(false);
        myGenLayoutManager
                .setMinorAlignment(org.eclipse.draw2d.ToolbarLayout.ALIGN_TOPLEFT);
        myGenLayoutManager.setSpacing(5);
        myGenLayoutManager.setVertical(true);
        nf.setLayoutManager(myGenLayoutManager);
        
        IFigure pane = new Figure();
        pane.setOpaque(false);
        pane.setLayoutManager(new BorderLayout());
        nf.add(pane);
        addContentPane(pane);
        
        return nf;
    }    
    
}
