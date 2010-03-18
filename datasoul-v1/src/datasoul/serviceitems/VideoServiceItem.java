/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datasoul.serviceitems;

import datasoul.render.ContentManager;
import datasoul.render.gstreamer.GstManagerServer;
import datasoul.render.gstreamer.commands.GstDisplayCmdVideoItem;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author samuellucas
 */
public class VideoServiceItem extends GenericAttachmentServiceItem {

    public VideoServiceItem(){
        super();
    }

    public VideoServiceItem(String filename, InputStream is) throws IOException{
        super(filename, is);
    }

    @Override
    public boolean getShowMediaControls(){
        return true;
    }

    @Override
    public void showItem(){
        super.showItem();
        ContentManager.getInstance().setMainShowBackground(false);
        ContentManager.getInstance().setMainShowTemplate(false);
        GstManagerServer.getInstance().sendCommand(new GstDisplayCmdVideoItem(file.getAbsolutePath()));
    }
}
