/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datasoul.render.gstreamer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.gstreamer.Caps;
import org.gstreamer.Element;
import org.gstreamer.ElementFactory;
import org.gstreamer.Pad;
import org.gstreamer.Structure;
import org.gstreamer.elements.DecodeBin;
import org.gstreamer.io.InputStreamSrc;

/**
 *
 * @author samuel
 */
public class GstManagerVideoGenericPipeline extends GstManagerPipeline {

    protected String filename;

    protected Element src;
    protected DecodeBin decodeBin;
    protected Element decodeQueue;
    protected FileInputStream fis;

    public GstManagerVideoGenericPipeline(String filename){
        super();
        this.filename = filename;
    }

    @Override
    public void prepareForStart(){

        super.prepareForStart();
        try {
            fis = new FileInputStream(this.filename);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        }
        src = new InputStreamSrc(fis, "Input File");
        decodeBin = new DecodeBin("Decode Bin");
        decodeQueue = ElementFactory.make("queue", "Decode Queue");
        pipe.addMany(src, decodeQueue, decodeBin);
        Element.linkMany(src, decodeQueue,  decodeBin);

        decodeBin.connect(new DecodeBin.NEW_DECODED_PAD() {
            @Override
            public void newDecodedPad(Element elem, Pad pad, boolean last) {

                /* only link once */
                if (pad.isLinked()) {
                    return;
                }
                /* check media type */
                Caps caps = pad.getCaps();
                Structure struct = caps.getStructure(0);
                if (struct.getName().startsWith("video/")) {
                    pad.link(tee.getStaticPad("sink"));
                }
            }
        });

    }

    @Override
    public void stop(){
        super.stop();
        if (pipe != null){
            Element.unlinkMany(src, decodeQueue,  decodeBin);
            pipe.removeMany(src, decodeQueue, decodeBin);
        }
    }

    @Override
    public void dispose(){
        super.dispose();
        if (src != null) src.dispose();
        if (decodeBin != null) decodeBin.dispose();
        if (decodeQueue != null) decodeQueue.dispose();
        if (fis != null) try {
            fis.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
